package cc.ssnoodles.db.template.fields;

import cc.ssnoodles.db.entity.Column;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/8/27 09:09
 */
public interface FieldTemplate {
    String LINE = System.getProperty("line.separator");

    String getTemplate(Column column);
}
