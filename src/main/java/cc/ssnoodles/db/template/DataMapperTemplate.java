package cc.ssnoodles.db.template;

import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.template.classes.ClassDataMapperTemplateImpl;
import cc.ssnoodles.db.template.functions.DataMapperFunctionTemplateImpl;
import cc.ssnoodles.db.template.imports.ImportMapperTemplateImpl;

/**
 * mapping template
 * @author ssnoodles
 * @version 1.0
 * Create at 2019-03-14 08:52
 */
public class DataMapperTemplate implements Template {
    @Override
    public String tableDataToString(Table table, String newClassName) {
        StringBuilder sb = new StringBuilder();
        sb.append(new ImportMapperTemplateImpl().getTemplate());
        sb.append(LINE);
        sb.append(new ClassDataMapperTemplateImpl().getTemplate(table, newClassName));
        sb.append(new DataMapperFunctionTemplateImpl().getTemplate(table, newClassName));
        sb.append(END);
        return sb.toString();
    }

    @Override
    public String endsWith() {
        return "DataMapper";
    }
}
