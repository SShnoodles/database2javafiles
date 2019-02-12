package cc.ssnoodles.db.factory;

import cc.ssnoodles.db.constant.DbType;
import cc.ssnoodles.db.constant.TemplateType;
import cc.ssnoodles.db.handler.*;
import cc.ssnoodles.db.template.*;

import java.sql.SQLException;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/8/27 08:36
 */
public class DbFactoryImpl implements DbFactory {

    @Override
    public void create(String dbType, Template template) throws SQLException {
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

    @Override
    public Template getTemplate(String templateType) {
        if (TemplateType.JPA.getType().equals(templateType)) {
            return new JpaTemplate();
        }
        else if (TemplateType.DTO.getType().equals(templateType)) {
            return new DtoTemplate();
        }
        else {
            return new CommonTemplate();
        }
    }
}
