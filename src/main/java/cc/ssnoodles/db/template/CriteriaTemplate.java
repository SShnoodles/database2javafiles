package cc.ssnoodles.db.template;

import cc.ssnoodles.db.entity.Column;
import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.template.classes.*;
import cc.ssnoodles.db.template.fields.*;
import cc.ssnoodles.db.template.imports.ImportSimpleTemplateImpl;

import java.util.List;

/**
 * criteria template for GET
 * @author ssnoodles
 * @version 1.0
 * Create at 2019-03-13 09:21
 */
public class CriteriaTemplate implements Template {
    @Override
    public String tableDataToString(Table table, String newClassName) {
        StringBuilder sb = new StringBuilder();
        sb.append(new ImportSimpleTemplateImpl().getTemplate());
        sb.append(LINE);
        sb.append(new ClassNoteTemplateImpl().getTemplate(table, newClassName));
        sb.append(new ClassDtoAnnotationTemplateImpl().getTemplate(table, newClassName));
        sb.append(new ClassCriteriaTemplateImpl().getTemplate(table, newClassName));
        List<Column> columns = table.getColumns();
        for (Column column : columns) {
            if (column.isPrimaryKey()) continue;
            sb.append(LINE);
            sb.append(new FieldNoteTemplateImpl().getTemplate(column));
            sb.append(new FieldDtoAnnotationTemplateImpl().getTemplate(column));
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
