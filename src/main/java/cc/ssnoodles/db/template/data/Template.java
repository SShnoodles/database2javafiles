package cc.ssnoodles.db.template.data;

import cc.ssnoodles.db.entity.Column;
import cc.ssnoodles.db.entity.Table;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/8/24 22:31
 */
public interface Template {
    String LINE = System.getProperty("line.separator");

    String tableDataToString(Table table, Column column);

    String tableDataToString(Table table);

    String tableDataToString(Column column);

    String tableDataToString();
}
