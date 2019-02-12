package cc.ssnoodles.db.factory;

import cc.ssnoodles.db.template.Template;

import java.sql.SQLException;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/8/27 08:35
 */
public interface DbFactory {
    void create(String dbType, Template template) throws SQLException;

    Template getTemplate(String templateType);
}
