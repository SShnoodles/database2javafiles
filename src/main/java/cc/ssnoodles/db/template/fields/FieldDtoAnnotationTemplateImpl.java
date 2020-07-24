package cc.ssnoodles.db.template.fields;

import cc.ssnoodles.db.constant.ColumnType;
import cc.ssnoodles.db.entity.Column;
import cc.ssnoodles.db.util.StringUtil;

/**
 * @author sunshun
 * @version 1.0
 * Create at 2020/4/10 13:30
 */
public class FieldDtoAnnotationTemplateImpl implements FieldTemplate {
    @Override
    public String getTemplate(Column column) {
        StringBuilder sb = new StringBuilder();
        String remark = StringUtil.isEmpty(column.getRemarks()) ? "" : column.getRemarks();
        sb.append("    @Schema(title = \"").append(remark).append("\"");
        if (!column.isNullable()) {
            sb.append(", required = true");
        }
        sb.append(")");
        if (!column.isNullable()) {
            sb.append(LINE);
            if (ColumnType.isString(column.getType().toUpperCase())) {
                sb.append("    @NotBlank(message = \"").append(remark).append(" 必填\")");
            } else {
                sb.append("    @NotNull(message = \"").append(remark).append(" 必填\")");
            }
        }
        if (ColumnType.isString(column.getType())) {
            sb.append(LINE);
            sb.append("    @Size(max = ").append(column.getSize()).append(")");
        }
        sb.append(LINE);
        return sb.toString();
    }
}
