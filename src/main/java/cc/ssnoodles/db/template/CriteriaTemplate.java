package cc.ssnoodles.db.template;

import cc.ssnoodles.db.entity.Column;
import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.template.classes.ClassCriteriaTemplateImpl;
import cc.ssnoodles.db.template.classes.ClassNoteTemplateImpl;
import cc.ssnoodles.db.template.fields.FieldCriteriaTemplateImpl;
import cc.ssnoodles.db.template.fields.FieldNoteTemplateImpl;

import java.util.List;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2019-03-13 09:21
 */
public class CriteriaTemplate implements Template {
    @Override
    public String tableDataToString(Table table) {
        StringBuilder sb = new StringBuilder();
        sb.append(new ClassNoteTemplateImpl().getTemplate(table));
        sb.append(new ClassCriteriaTemplateImpl().getTemplate(table));
        List<Column> columns = table.getColumns();
        for (Column column : columns) {
            if (column.isPrimaryKey()) continue;
            sb.append(LINE);
            sb.append(new FieldNoteTemplateImpl().getTemplate(column));
            sb.append(new FieldCriteriaTemplateImpl().getTemplate(column));
        }
        sb.append(END);
        return sb.toString();
    }

    @Override
    public String endsWith() {
        return "Criteria";
    }
}