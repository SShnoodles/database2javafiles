package cc.ssnoodles.db.template.classes;

import cc.ssnoodles.db.entity.Table;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/8/25 22:33
 */
public interface ClassTemplate {

    String LINE = System.getProperty("line.separator");

    String BEGIN = "{";

    String getTemplate(Table table);
}
