package cc.ssnoodles.db.template;

import cc.ssnoodles.db.entity.Column;
import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.template.classes.ClassNoteTemplateImpl;
import cc.ssnoodles.db.template.classes.ClassTemplateImpl;
import cc.ssnoodles.db.template.fields.FieldNoteTemplateImpl;
import cc.ssnoodles.db.template.fields.FieldTemplateImpl;

import java.util.List;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/8/25 22:51
 */
public class CommonTemplate implements Template{
    @Override
    public String tableDataToString(Table table) {
        StringBuilder sb = new StringBuilder();
        sb.append(new ClassNoteTemplateImpl().getTemplate(table));
        sb.append(new ClassTemplateImpl().getTemplate(table));
        List<Column> columns = table.getColumns();
        for (Column column : columns) {
            sb.append(new FieldNoteTemplateImpl().getTemplate(column));
            sb.append(new FieldTemplateImpl().getTemplate(column));
        }
        sb.append(END);
        return sb.toString();
    }
}
