package cc.ssnoodles.db.template;


import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.template.classes.ClassNoteTemplateImpl;
import cc.ssnoodles.db.template.classes.ClassRefTemplateImpl;
import cc.ssnoodles.db.template.imports.ImportSimpleTemplateImpl;

/**
 * dto template for GET
 * @author ssnoodles
 * @version 1.0
 * Create at 2019-03-13 09:20
 */
public class RefTemplate implements Template {
//    /**
//     * @author ssnoodles
//     * Create at 2019-03-12 13:10
//     */
//    @Builder
//    public class DemoRef {
//        public String id;
//    }
    @Override
    public String tableDataToString(Table table, String newClassName) {
        StringBuilder sb = new StringBuilder();
        sb.append(new ImportSimpleTemplateImpl().getTemplate());
        sb.append(LINE);
        sb.append(new ClassNoteTemplateImpl().getTemplate(table, newClassName));
        sb.append(new ClassRefTemplateImpl().getTemplate(table, newClassName));
        sb.append("    public String id;").append(LINE);
        sb.append(END);
        return sb.toString();
    }

    @Override
    public String endsWith() {
        return "Ref";
    }
}
