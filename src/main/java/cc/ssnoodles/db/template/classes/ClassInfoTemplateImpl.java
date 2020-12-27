package cc.ssnoodles.db.template.classes;

import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.template.classes.ClassTemplate;
import cc.ssnoodles.db.util.StringUtil;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2019-03-13 12:35
 */
public class ClassInfoTemplateImpl implements ClassTemplate {
    @Override
    public String getTemplate(Table table, String newClassName) {
        StringBuilder sb = new StringBuilder();
        sb.append("public class ")
                .append(StringUtil.isEmpty(newClassName) ? StringUtil.underlineToHumpTopUpperCase(table.getName()) : newClassName)
                .append(INFO)
                .append(" ").append(BEGIN).append(LINE);
        return sb.toString();
    }
}
