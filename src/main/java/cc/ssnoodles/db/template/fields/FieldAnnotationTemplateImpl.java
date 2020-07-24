package cc.ssnoodles.db.template.fields;

import cc.ssnoodles.db.constant.ColumnType;
import cc.ssnoodles.db.entity.Column;

/**
 * 字段注解
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/8/27 09:09
 */
public class FieldAnnotationTemplateImpl implements FieldTemplate {
    @Override
    public String getTemplate(Column column) {
        StringBuilder sb = new StringBuilder();
        if (column.isPrimaryKey()) {
            sb.append("    @Id").append(LINE);
            sb.append("    @Setter(AccessLevel.PROTECTED)").append(LINE);
            sb.append("    @NonNull").append(LINE);
        }
        sb.append("    @Column(name = \"").append(column.getName()).append("\"");
        if (!column.isNullable()) {
            sb.append(", nullable = false");
        }
        if (ColumnType.isString(column.getType().toUpperCase()) && column.getSize() != 255) {
            sb.append(", length = ").append(column.getSize());
        }
        if (ColumnType.isNumber(column.getType().toUpperCase())) {
            if (column.getSize() != 0) {
                sb.append(", precision = ").append(column.getSize());
            }
            if (column.getDecimalDigits() != 0) {
                sb.append(", scale = ").append(column.getDecimalDigits());
            }
        }
        sb.append(")");
        sb.append(LINE);
        return sb.toString();
    }
}
