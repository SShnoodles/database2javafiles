package cc.ssnoodles.db.template.data;

import cc.ssnoodles.db.constant.ColumnType;
import cc.ssnoodles.db.entity.Column;
import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.util.StringUtil;

import java.util.List;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/8/24 23:08
 */
public class ClassTemplate implements Template {

    @Override
    public String tableDataToString(Table table) {
        StringBuilder sb = new StringBuilder();
        sb.append("@Data").append(LINE)
                .append("@Entity").append(LINE)
                .append("@Table(name = \"").append(table.getName()).append("\")").append(LINE)
                .append("public class ").append(StringUtil.underlineToHumpTopUpperCase(table.getName())).append(" {").append(LINE);
        List<Column> columns = table.getColumns();
        for (Column column : columns) {
            sb.append(new FieldNoteTemplate().tableDataToString(column));
            if (column.isPrimaryKey()) {
                sb.append("    @Id").append(LINE);
            }
            sb.append("    @Column(name = \"").append(column.getName()).append("\")").append(LINE)
                    .append("    private ");
            if (!column.isDecimalDigits() && "NUMBER".equals(column.getType())) {
                sb.append("Integer");
            }else {
                sb.append(ColumnType.get(column.getType().toUpperCase()));
            }
            sb.append(" ").append(StringUtil.underlineToHump(column.getName())).append(";").append(LINE);
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public String tableDataToString(Table table, Column column) {
        return null;
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
