package cc.ssnoodles.db.template;

import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.template.classes.ClassUpdaterTemplateImpl;
import cc.ssnoodles.db.template.functions.UpdaterFunctionTemplateImpl;
import cc.ssnoodles.db.template.imports.ImportMapperTemplateImpl;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2019-03-14 08:52
 */
public class UpdaterTemplate implements Template {
    @Override
    public String tableDataToString(Table table) {
        StringBuilder sb = new StringBuilder();
        sb.append(new ImportMapperTemplateImpl().getTemplate());
        sb.append(new ClassUpdaterTemplateImpl().getTemplate(table));
        sb.append(new UpdaterFunctionTemplateImpl().getTemplate(table));
        sb.append(END);
        return sb.toString();
    }

    @Override
    public String endsWith() {
        return "Updater";
    }
}
