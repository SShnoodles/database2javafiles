package cc.ssnoodles.db.template;

import cc.ssnoodles.db.entity.Column;
import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.template.classes.ClassFormTemplateImpl;
import cc.ssnoodles.db.template.classes.ClassNoteTemplateImpl;
import cc.ssnoodles.db.template.fields.FieldNoteTemplateImpl;
import cc.ssnoodles.db.template.fields.FieldPublicTemplateImpl;

import java.util.List;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2019-03-13 09:20
 */
public class FormTemplate implements Template {
    @Override
    public String tableDataToString(Table table) {
        StringBuilder sb = new StringBuilder();
        sb.append(new ClassNoteTemplateImpl().getTemplate(table));
        sb.append(new ClassFormTemplateImpl().getTemplate(table));
        List<Column> columns = table.getColumns();
        for (Column column : columns) {
            if (column.isPrimaryKey()) continue;
            sb.append(LINE);
            sb.append(new FieldNoteTemplateImpl().getTemplate(column));
            sb.append(new FieldPublicTemplateImpl().getTemplate(column));
        }
        sb.append(END);
        return sb.toString();
    }

    @Override
    public String endsWith() {
        return "Form";
    }
}
