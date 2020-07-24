package cc.ssnoodles.db.template.fields;

import cc.ssnoodles.db.constant.ColumnType;
import cc.ssnoodles.db.entity.Column;

/**
 * @author sunshun
 * @version 1.0
 * Create at 2020/4/10 13:30
 */
public class FieldDtoAnnotationTemplateImpl implements FieldTemplate {
    @Override
    public String getTemplate(Column column) {
        StringBuilder sb = new StringBuilder();
        sb.append("    @Schema(description = \"").append(column.getRemarks()).append("\"");
        if (!column.isNullable()) {
            sb.append(", required = true");
        }
        sb.append(")");
        if (!column.isNullable()) {
            sb.append(LINE);
            if (ColumnType.isString(column.getType().toUpperCase())) {
                sb.append("    @NotBlank(message = \"").append(column.getRemarks()).append(" 必填\")");
            } else {
                sb.append("    @NotNull(message = \"").append(column.getRemarks()).append(" 必填\")");
            }
        }
        sb.append(LINE);
        return sb.toString();
    }
}
