package cc.ssnoodles.db.template;

import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.template.functions.UpdaterForNewFunctionTemplateImpl;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2019-03-14 09:10
 */
public class UpdaterForNewSimpleTemplate implements Template  {
    @Override
    public String tableDataToString(Table table, String newClassName) {
        return new UpdaterForNewFunctionTemplateImpl().getTemplate(table, newClassName) + END;
    }

    @Override
    public String endsWith() {
        return "Updater";
    }
}
