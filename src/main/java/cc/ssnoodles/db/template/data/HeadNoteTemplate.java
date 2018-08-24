package cc.ssnoodles.db.template.data;

import cc.ssnoodles.db.entity.Column;
import cc.ssnoodles.db.entity.Table;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/8/24 23:00
 */
public class HeadNoteTemplate implements Template{

    @Override
    public String tableDataToString(Table table, Column column) {
        return null;
    }

    @Override
    public String tableDataToString(Table table) {
        StringBuilder sb = new StringBuilder();
        sb.append("/**").append(LINE)
                .append(" * ").append(table.getRemarks() == null ? "" : table.getRemarks()).append(LINE)
                .append(" */").append(LINE);
        return sb.toString();
    }

    @Override
    public String tableDataToString(Column column) {
        return null;
    }

    @Override
    public String tableDataToString() {
        return null;
    }
}
