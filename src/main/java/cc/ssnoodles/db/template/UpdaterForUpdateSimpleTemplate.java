package cc.ssnoodles.db.template;

import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.template.functions.UpdaterForUpdateFunctionTemplateImpl;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2019-03-14 09:10
 */
public class UpdaterForUpdateSimpleTemplate implements Template  {
    @Override
    public String tableDataToString(Table table, String newClassName) {
        return new UpdaterForUpdateFunctionTemplateImpl().getTemplate(table, newClassName) + END;
    }

    @Override
    public String endsWith() {
        return "EntityMapper";
    }
}
