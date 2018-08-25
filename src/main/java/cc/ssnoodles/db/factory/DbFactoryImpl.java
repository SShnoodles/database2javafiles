package cc.ssnoodles.db.factory;

import cc.ssnoodles.db.constant.DbType;
import cc.ssnoodles.db.handler.MysqlDbHandler;
import cc.ssnoodles.db.handler.OracleDbHandler;
import cc.ssnoodles.db.handler.PostgreDbHandler;
import cc.ssnoodles.db.template.JpaTemplate;
import cc.ssnoodles.db.template.Template;

import java.sql.SQLException;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/8/25 21:38
 */
public class DbFactoryImpl implements DbFactory{

    @Override
    public void toJavaFiles(String dbType, Template template) throws SQLException {
        if (DbType.ORACLE.getType().equals(dbType.toLowerCase())) {
            new OracleDbHandler().execute(template);
        }
        if (DbType.POSTGRESQL.getType().equals(dbType.toLowerCase())) {
            new PostgreDbHandler().execute(template);
        }
        if (DbType.MYSQL.getType().equals(dbType.toLowerCase())) {
            new MysqlDbHandler().execute(template);
        }
    }
}
