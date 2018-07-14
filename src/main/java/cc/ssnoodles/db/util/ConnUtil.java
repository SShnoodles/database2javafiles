package cc.ssnoodles.db.util;

import cc.ssnoodles.db.constant.DbType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/7/13 08:36
 */
public class ConnUtil {

    public static Connection getConn() {
        Properties properties = FileUtil.PROPERTIES;

        String url = getUrl();
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        Properties props = new Properties();
        props.put("user", username);
        props.put("password", password);
        props.put("remarksReporting","true");

        Connection conn = null;
        try {
            Class.forName(getDriver());
            conn = DriverManager.getConnection(url, props);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    private static String getDriver() {
        Properties properties = FileUtil.PROPERTIES;
        String db = properties.getProperty("db");
        return DbType.get(db);
    }

    private static String getUrl() {
        Properties properties = FileUtil.PROPERTIES;
        String db = properties.getProperty("db");
        String host = properties.getProperty("host");
        String port = properties.getProperty("port");
        String servername = properties.getProperty("servername");

        if (DbType.ORACLE.getType().equals(db.toLowerCase())) {
            return "jdbc:oracle:thin:@" + host + ":" + port + ":" + servername;
        }
        if (DbType.POSTGRESQL.getType().equals(db.toLowerCase())) {
            return "jdbc:postgresql://" + host + ":" + port + "/" + servername;
        }
        if (DbType.MYSQL.getType().equals(db.toLowerCase())) {
            return "jdbc:mysql://" + host + ":" + port + "/" + servername;
        }
        throw new RuntimeException("No database was found to be supported");
    }
}
