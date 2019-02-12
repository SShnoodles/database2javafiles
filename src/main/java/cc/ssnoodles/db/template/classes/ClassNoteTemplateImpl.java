package cc.ssnoodles.db.template.classes;

import cc.ssnoodles.db.entity.Table;

/**
 * 类注释
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/8/27 09:01
 */
public class ClassNoteTemplateImpl implements ClassTemplate {

    @Override
    public String getTemplate(Table table) {
        StringBuilder sb = new StringBuilder();
        sb.append("/**").append(LINE)
                .append(" * ").append(table.getRemarks() == null ? "" : table.getRemarks()).append(LINE)
                .append(" */").append(LINE);
        return sb.toString();
    }
}
