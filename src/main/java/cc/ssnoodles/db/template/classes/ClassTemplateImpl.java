package cc.ssnoodles.db.template.classes;

import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.util.StringUtil;

/**
 * 类名
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/8/27 09:01
 */
public class ClassTemplateImpl implements ClassTemplate {
    @Override
    public String getTemplate(Table table, String newClassName) {
        StringBuilder sb = new StringBuilder();
        sb.append("public class ")
                .append(StringUtil.isEmpty(newClassName) ? StringUtil.underlineToHumpTopUpperCase(table.getName()) : newClassName)
                .append(" ").append(BEGIN).append(LINE);
        return sb.toString();
    }
}
