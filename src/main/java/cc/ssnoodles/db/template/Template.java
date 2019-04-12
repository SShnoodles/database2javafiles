package cc.ssnoodles.db.template;

import cc.ssnoodles.db.entity.Table;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/8/27 09:18
 */
public interface Template {
    String LINE = System.getProperty("line.separator");

    String BEGIN = "{";

    String END = "}";

    String tableDataToString(Table table, String newClassName);

    default String startsWith() {
        return "";
    }

    default String endsWith() {
        return "";
    }
}
