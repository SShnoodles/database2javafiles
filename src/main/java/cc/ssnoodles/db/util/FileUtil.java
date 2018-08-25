package cc.ssnoodles.db.util;

import java.io.*;
import java.util.Properties;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/7/13 08:35
 */
public class FileUtil {

    public static final String DEFAULT_FILE = "app.properties";

    public static final Properties PROPERTIES = readPropertiesFile(DEFAULT_FILE);

    private static final String CODE = "utf-8";

    private static final String SUFFIX = ".java";

    public static Properties readPropertiesFile(String resources) {
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
        return properties;
    }

    public static void write2JavaFiles(String path, String str) {
        File file = new File(path + SUFFIX);
        mkdirs(file);
        try {
            FileOutputStream outSTr = new FileOutputStream(file);
            BufferedOutputStream buff = new BufferedOutputStream(outSTr);
            buff.write(str.getBytes(CODE));
            buff.flush();
            buff.close();
        }catch (IOException e) {
            throw new RuntimeException("File write failure, Please check file path.");
        }
    }

    public static void mkdirs(File file) {
        File fileParent = file.getParentFile();
        if (!fileParent.exists()) {
            fileParent.mkdirs();
        }
    }
}
