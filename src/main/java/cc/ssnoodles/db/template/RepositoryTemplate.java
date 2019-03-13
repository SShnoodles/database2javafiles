package cc.ssnoodles.db.template;

import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.template.classes.*;
import cc.ssnoodles.db.template.imports.ImportRepositoryTemplateImpl;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2019-02-12 08:41
 */
public class RepositoryTemplate implements Template{

    @Override
    public String tableDataToString(Table table) {
        StringBuilder sb = new StringBuilder();
        sb.append(new ImportRepositoryTemplateImpl().getTemplate());
        sb.append(LINE);
        sb.append(new ClassRepositoryTemplateImpl().getTemplate(table));
        sb.append(" ");
        sb.append(BEGIN);
        sb.append(LINE);
        sb.append(END);
        return sb.toString();
    }

    @Override
    public String endsWith() {
        return "Repository";
    }
}
