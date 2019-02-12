package cc.ssnoodles.db.template.fields;

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
        }
        sb.append("    @Column(name = \"").append(column.getName()).append("\")").append(LINE);
        return sb.toString();
    }
}
