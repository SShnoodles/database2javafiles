package cc.ssnoodles.db.template;

import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.template.functions.DataMapperFunctionTemplateImpl;

/**
 * mapping template
 * @author ssnoodles
 * @version 1.0
 * Create at 2019-03-14 09:10
 */
public class DataMapperSimpleTemplate implements Template {
    @Override
    public String tableDataToString(Table table, String newClassName) {
        return new DataMapperFunctionTemplateImpl().getTemplate(table, newClassName) + END;
    }

    @Override
    public String endsWith() {
        return "DataMapper";
    }
}
