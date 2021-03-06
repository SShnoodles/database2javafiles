package cc.ssnoodles.db.template.classes;

import cc.ssnoodles.db.constant.ColumnType;
import cc.ssnoodles.db.entity.*;
import cc.ssnoodles.db.util.FileUtil;
import cc.ssnoodles.db.util.StringUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 类构造器
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/8/27 09:01
 */
public class ClassConstructorTemplate implements ClassTemplate {
    @Override
    public String getTemplate(Table table, String className) {
        StringBuilder sb = new StringBuilder();
        String tableName = StringUtil.isEmpty(className) ? StringUtil.underlineToHumpTopUpperCase(table.getName()) : className;

        sb.append(LINE);
        List<Column> primaryKeyColumns = table.getColumns().stream().filter(Column::isPrimaryKey).collect(Collectors.toList());
        String columnTypeAndNames = "";
        String columnTypeEqNames = null;
        if (primaryKeyColumns.size() > 0) {
            for (Column column : primaryKeyColumns) {
                if (ColumnType.UUID.getJavaType().equals(column.getType())) {
                    columnTypeAndNames = "UUID " + StringUtil.underlineToHump(column.getName()) + ", ";
                }else {
                    columnTypeAndNames = "String " + StringUtil.underlineToHump(column.getName()) + ", ";
                }
                columnTypeEqNames = "this." + StringUtil.underlineToHump(column.getName()) + " = " + StringUtil.underlineToHump(column.getName()) + ";,";
            }
            columnTypeAndNames = columnTypeAndNames.substring(0, columnTypeAndNames.length() - 2);
            columnTypeEqNames = columnTypeEqNames.substring(0, columnTypeEqNames.length() - 1);
        }
        sb.append("    public ").append(tableName).append("(").append(columnTypeAndNames).append(") {").append(LINE);
        if (columnTypeEqNames != null) {
            String[] split = columnTypeEqNames.split(",");
            for (String s : split) {
                sb.append("        ").append(s).append(LINE);
            }
        }
        sb.append("    }").append(LINE);
        return sb.toString();
    }
}
