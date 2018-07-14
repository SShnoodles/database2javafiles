package cc.ssnoodles.db;

import cc.ssnoodles.db.constant.DbType;
import cc.ssnoodles.db.handler.DbHandler;
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
            DbHandler dbHandler = new DbHandler();
            dbHandler.handle(DbType.ORACLE.getType());
        }
        if (DbType.POSTGRESQL.getType().equals(db.toLowerCase())) {
            DbHandler dbHandler = new DbHandler();
            dbHandler.handle(DbType.POSTGRESQL.getType());
        }
        if (DbType.MYSQL.getType().equals(db.toLowerCase())) {
            DbHandler dbHandler = new DbHandler();
            dbHandler.handle(DbType.MYSQL.getType());
        }
    }

}