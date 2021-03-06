package cc.ssnoodles.db.template.fields;

import cc.ssnoodles.db.entity.Column;

/**
 * 字段注释 /**
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/8/27 09:09
 */
public class FieldNoteTemplateImpl implements FieldTemplate {

    @Override
    public String getTemplate(Column column) {
        StringBuilder sb = new StringBuilder();
        sb.append("    /**").append(LINE)
                .append("     * ").append(column.getRemarks() == null ? "" : column.getRemarks()).append(LINE)
                .append("     */").append(LINE);
        return sb.toString();
    }
}
