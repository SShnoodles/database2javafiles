package cc.ssnoodles.db.template;

import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.template.classes.ClassControllerTemplateImpl;
import cc.ssnoodles.db.template.classes.ClassNoteTemplateImpl;
import cc.ssnoodles.db.template.functions.*;
import cc.ssnoodles.db.template.imports.ImportControllerTemplateImpl;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2019-03-11 12:37
 */
public class ControllerTemplate implements Template {

    @Override
    public String tableDataToString(Table table, String newClassName) {
        StringBuilder sb = new StringBuilder();
        sb.append(new ImportControllerTemplateImpl().getTemplate());
        sb.append(LINE);
        sb.append(new ClassNoteTemplateImpl().getTemplate(table, newClassName));
        sb.append(new ClassControllerTemplateImpl().getTemplate(table, newClassName));
        sb.append(new PageFunctionTemplateImpl().getTemplate(table, newClassName));
        sb.append(new GetFunctionTemplateImpl().getTemplate(table, newClassName));
        sb.append(new PostFunctionTemplateImpl().getTemplate(table, newClassName));
        sb.append(new PutFunctionTemplateImpl().getTemplate(table, newClassName));
        sb.append(new DeleteFunctionTemplateImpl().getTemplate(table, newClassName));
        sb.append(END);
        return sb.toString();
    }

    @Override
    public String endsWith() {
        return "Controller";
    }
}
