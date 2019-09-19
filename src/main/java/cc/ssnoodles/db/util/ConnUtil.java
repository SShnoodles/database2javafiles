package cc.ssnoodles.db.util;

import cc.ssnoodles.db.constant.DbType;
import cc.ssnoodles.db.entity.Config;

import java.sql.*;
import java.util.Properties;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/7/13 08:36
 */
public class ConnUtil {
    private final static Config CONFIG = FileUtil.PROPERTIES;

    public static Connection getConn() {
        Properties props = new Properties();
        props.put("user", CONFIG.getUsername());
        props.put("password", CONFIG.getPassword());
        props.put("remarksReporting", "true");

        Connection conn = null;
        try {
            Class.forName(getDriver());
            conn = DriverManager.getConnection(CONFIG.getUrl(), props);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    private static String getDriver() {
        return DbType.get(CONFIG.getDb());
    }
}
