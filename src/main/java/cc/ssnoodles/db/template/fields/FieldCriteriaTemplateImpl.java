package cc.ssnoodles.db.template.fields;

import cc.ssnoodles.db.constant.ColumnType;
import cc.ssnoodles.db.entity.Column;
import cc.ssnoodles.db.util.StringUtil;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2019-03-13 18:19
 */
public class FieldCriteriaTemplateImpl implements FieldTemplate {
    @Override
    public String getTemplate(Column column) {
        StringBuilder sb = new StringBuilder();
        // @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        // public LocalDate from;
        if (ColumnType.isTime(column.getType())) {
            appendPublicField(column, sb, "From");
            sb.append(LINE);
            appendPublicField(column, sb, "To");
        } else {
            appendPublicField(column, sb, "");
        }
        return sb.toString();
    }

    private void appendPublicField(Column column, StringBuilder sb, String fieldSuffix) {
        if (ColumnType.isTime(column.getType())) {
            sb.append("    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)").append(LINE);
        }
        sb.append("    public ");
        if (!column.isDecimalDigits() && "NUMBER".equals(column.getType())) {
            sb.append("Integer");
        } else if ("OffsetDateTime".equals(ColumnType.get(column.getType()))) {
            sb.append("LocalDate");
        } else {
            sb.append(ColumnType.get(column.getType()));
        }
        sb.append(" ").append(StringUtil.underlineToHump(column.getName())).append(fieldSuffix).append(";").append(LINE);
    }
}
