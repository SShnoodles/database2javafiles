package cc.ssnoodles.db.template.classes;

import cc.ssnoodles.db.entity.Table;

/**
 * @author sunshun
 * @version 1.0
 * Create at 2020/4/10 13:12
 */
public class ClassDtoAnnotationTemplateImpl implements ClassTemplate {
    @Override
    public String getTemplate(Table table, String newClassName) {
        return "@Schema(description = \"" + table.getRemarks() + "\")" + LINE;
    }
}
