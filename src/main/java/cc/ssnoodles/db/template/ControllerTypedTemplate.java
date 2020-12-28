package cc.ssnoodles.db.template;

import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.template.classes.ClassControllerTypedTemplateImpl;
import cc.ssnoodles.db.template.classes.ClassNoteTemplateImpl;
import cc.ssnoodles.db.template.functions.*;
import cc.ssnoodles.db.template.imports.ImportControllerTemplateImpl;

/**
 * typed controller template
 * @author ssnoodles
 * @version 1.0
 * Create at 2020-12-27 13:26
 */
public class ControllerTypedTemplate implements Template {

    @Override
    public String tableDataToString(Table table, String newClassName) {
        StringBuilder sb = new StringBuilder();
        sb.append(new ImportControllerTemplateImpl().getTemplate());
        sb.append(LINE);
        sb.append(new ClassNoteTemplateImpl().getTemplate(table, newClassName));
        sb.append(new ClassControllerTypedTemplateImpl().getTemplate(table, newClassName));
        sb.append(new PageFunctionTypedTemplateImpl().getTemplate(table, newClassName));
        sb.append(new GetFunctionTypedTemplateImpl().getTemplate(table, newClassName));
        sb.append(new PostFunctionTypedTemplateImpl().getTemplate(table, newClassName));
        sb.append(new PutFunctionTypedTemplateImpl().getTemplate(table, newClassName));
        sb.append(new PatchFunctionTypedTemplateImpl().getTemplate(table, newClassName));
        sb.append(new DeleteFunctionTypedTemplateImpl().getTemplate(table, newClassName));
        sb.append(END);
        return sb.toString();
    }

    @Override
    public String endsWith() {
        return "Controller";
    }
}
