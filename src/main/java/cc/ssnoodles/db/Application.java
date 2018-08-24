package cc.ssnoodles.db;

import cc.ssnoodles.db.constant.DbType;
import cc.ssnoodles.db.handler.MysqlDbHandler;
import cc.ssnoodles.db.handler.OracleDbHandler;
import cc.ssnoodles.db.handler.PostgreDbHandler;
import cc.ssnoodles.db.util.FileUtil;

import java.util.Properties;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/7/12 18:39
 */
public class Application {

    public static void main(String[] args) throws Exception {
        Properties properties = FileUtil.PROPERTIES;
        String db = properties.getProperty("db");

        if (DbType.ORACLE.getType().equals(db.toLowerCase())) {
            new OracleDbHandler().execute();
        }
        if (DbType.POSTGRESQL.getType().equals(db.toLowerCase())) {
            new PostgreDbHandler().execute();
        }
        if (DbType.MYSQL.getType().equals(db.toLowerCase())) {
            new MysqlDbHandler().execute();
        }
    }

}