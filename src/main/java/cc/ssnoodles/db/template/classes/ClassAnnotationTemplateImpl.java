package cc.ssnoodles.db.template.classes;

import cc.ssnoodles.db.entity.Table;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/8/25 22:09
 */
public class ClassAnnotationTemplateImpl implements ClassTemplate {
    @Override
    public String getTemplate(Table table) {
        StringBuilder sb = new StringBuilder();
        sb.append("@Data").append(LINE)
                .append("@Entity").append(LINE)
                .append("@Table(name = \"").append(table.getName()).append("\")").append(LINE);
        return sb.toString();
    }
}
