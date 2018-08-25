package cc.ssnoodles.db.template.classes;

import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.util.StringUtil;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/8/25 22:18
 */
public class ClassTemplateImpl implements ClassTemplate {
    @Override
    public String getTemplate(Table table) {
        StringBuilder sb = new StringBuilder();
        sb.append("public class ")
                .append(StringUtil.underlineToHumpTopUpperCase(table.getName()))
                .append(" ").append(BEGIN).append(LINE);
        return sb.toString();
    }
}
