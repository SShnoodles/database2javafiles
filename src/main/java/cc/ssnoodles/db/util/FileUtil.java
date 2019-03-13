package cc.ssnoodles.db.util;

import cc.ssnoodles.db.entity.Config;

import java.io.*;
import java.util.Properties;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/7/13 08:35
 */
public class FileUtil {

    public static final String DEFAULT_FILE = "app.properties";

    public static final Config PROPERTIES = readPropertiesFile(DEFAULT_FILE);

    private static final String CODE = "utf-8";

    private static final String SUFFIX = ".java";

    public static Config readPropertiesFile(String resources) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(DEFAULT_FILE));
        } catch (IOException e) {
            try {
                properties.load(FileUtil.class.getClassLoader().getResourceAsStream(resources));
            } catch (IOException e1) {
                throw new RuntimeException("Load configuration file failed");
            }
        }
        try {
            return Config.builder()
                    .db(properties.getProperty("db"))
                    .host(properties.getProperty("host"))
                    .port(properties.getProperty("port"))
                    .servername(properties.getProperty("servername"))
                    .username(properties.getProperty("username"))
                    .password(properties.getProperty("password"))
                    .outpath(properties.getProperty("outpath"))
                    .templates(properties.getProperty("templates").split(","))
                    .author(properties.getProperty("author"))
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Load configuration file failed");
        }
    }

    public static void write2JavaFiles(String path, String str) {
        File file = new File(path + SUFFIX);
        mkdirs(file);
        FileOutputStream ops = null;
        BufferedOutputStream buff = null;
        try {
            ops = new FileOutputStream(file);
            buff = new BufferedOutputStream(ops);
            buff.write(str.getBytes(CODE));
            buff.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (buff != null) {
                try {
                    buff.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ops != null) {
                try {
                    ops.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void mkdirs(File file) {
        File fileParent = file.getParentFile();
        if (!fileParent.exists()) {
            fileParent.mkdirs();
        }
    }
}
