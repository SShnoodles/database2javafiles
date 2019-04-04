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
                    .overwritefiles(Boolean.valueOf(properties.getProperty("overwritefiles")))
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Load configuration file failed");
        }
    }

    public static void write2JavaFiles(String path, String str, boolean isOverwriteFiles) {
        File file = new File(path + SUFFIX);
        if (file.exists() && !isOverwriteFiles) {
            return;
        }
        mkdirs(file);
        try (FileOutputStream ops = new FileOutputStream(file);
             BufferedOutputStream buff = new BufferedOutputStream(ops)) {
            buff.write(str.getBytes(CODE));
            buff.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Write java file failed, Path: " + path + SUFFIX);
        }
    }

    private static void mkdirs(File file) {
        File fileParent = file.getParentFile();
        if (!fileParent.exists()) {
            fileParent.mkdirs();
        }
    }


    public static void write2IfExistFiles(String path, String noExistStr, String existStr) {
        File file = new File(path + SUFFIX);
        if (file.exists()) {
            String separator = System.getProperty("line.separator");

            try (FileReader reader = new FileReader(file);
                 BufferedReader br = new BufferedReader(reader)) {
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.contains("}") && !line.contains("})")) {
                        break;
                    }
                    sb.append(line).append(separator);
                }
                sb.append(separator);
                sb.append(existStr);
                write2JavaFiles(path, sb.toString(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            write2JavaFiles(path, noExistStr, true);
        }
    }
}
