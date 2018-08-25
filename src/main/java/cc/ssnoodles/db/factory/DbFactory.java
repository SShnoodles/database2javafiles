package cc.ssnoodles.db.factory;

import cc.ssnoodles.db.template.Template;

import java.sql.SQLException;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/8/25 21:35
 */
public interface DbFactory {
    void toJavaFiles(String dbType, Template template) throws SQLException;
}
