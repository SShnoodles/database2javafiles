package cc.ssnoodles.db.template;

import cc.ssnoodles.db.entity.Column;
import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.template.classes.ClassAnnotationTemplateImpl;
import cc.ssnoodles.db.template.classes.ClassNoteTemplateImpl;
import cc.ssnoodles.db.template.classes.ClassTemplateImpl;
import cc.ssnoodles.db.template.fields.FieldAnnotationTemplateImpl;
import cc.ssnoodles.db.template.fields.FieldNoteTemplateImpl;
import cc.ssnoodles.db.template.fields.FieldTemplateImpl;
import cc.ssnoodles.db.template.imports.ImportTemplateImpl;

import java.util.List;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/8/24 23:08
 */
public class JpaTemplate implements Template {

    @Override
    public String tableDataToString(Table table) {
        StringBuilder sb = new StringBuilder();
        sb.append(new ImportTemplateImpl().getTemplate());
        sb.append(LINE);
        sb.append(new ClassNoteTemplateImpl().getTemplate(table));
        sb.append(new ClassAnnotationTemplateImpl().getTemplate(table));
        sb.append(new ClassTemplateImpl().getTemplate(table));
        List<Column> columns = table.getColumns();
        for (Column column : columns) {
            sb.append(new FieldNoteTemplateImpl().getTemplate(column));
            sb.append(new FieldAnnotationTemplateImpl().getTemplate(column));
            sb.append(new FieldTemplateImpl().getTemplate(column));
        }
        sb.append(END);
        return sb.toString();
    }
}
