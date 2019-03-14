package cc.ssnoodles.db.template;

import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.template.classes.ClassDataMapperTemplateImpl;
import cc.ssnoodles.db.template.functions.DataMapperFunctionTemplateImpl;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2019-03-14 08:52
 */
public class DataMapperTemplate implements Template {
    @Override
    public String tableDataToString(Table table) {
        StringBuilder sb = new StringBuilder();
        sb.append(new ClassDataMapperTemplateImpl().getTemplate(table));
        sb.append(new DataMapperFunctionTemplateImpl().getTemplate(table));
        sb.append(END);
        return sb.toString();
    }

    @Override
    public String endsWith() {
        return "DataMapper";
    }
}
