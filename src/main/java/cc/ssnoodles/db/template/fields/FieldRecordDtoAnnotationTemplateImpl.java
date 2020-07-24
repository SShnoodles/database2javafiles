package cc.ssnoodles.db.template.fields;

import cc.ssnoodles.db.entity.Column;
import cc.ssnoodles.db.util.StringUtil;

/**
 * @author sunshun
 * @version 1.0
 * Create at 2020/4/10 13:30
 */
public class FieldRecordDtoAnnotationTemplateImpl implements FieldTemplate {
    @Override
    public String getTemplate(Column column) {
        StringBuilder sb = new StringBuilder();
        String remark = StringUtil.isEmpty(column.getRemarks()) ? "" : column.getRemarks();
        sb.append("    @Schema(title = \"").append(remark).append("\")");
        sb.append(LINE);
        return sb.toString();
    }
}
