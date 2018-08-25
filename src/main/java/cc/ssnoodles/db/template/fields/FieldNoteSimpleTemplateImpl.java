package cc.ssnoodles.db.template.fields;

import cc.ssnoodles.db.entity.Column;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/8/25 23:47
 */
public class FieldNoteSimpleTemplateImpl implements FieldTemplate{

    @Override
    public String getTemplate(Column column) {
        StringBuilder sb = new StringBuilder();
        sb.append("    // ")
                .append(column.getRemarks() == null ? "" : column.getRemarks()).append(LINE);
        return sb.toString();
    }
}
