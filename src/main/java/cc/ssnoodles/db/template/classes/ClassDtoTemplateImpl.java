package cc.ssnoodles.db.template.classes;

import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.util.StringUtil;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2019-03-13 12:35
 */
public class ClassDtoTemplateImpl implements ClassTemplate {
    @Override
    public String getTemplate(Table table) {
        StringBuilder sb = new StringBuilder();
        sb.append("public class ")
                .append(StringUtil.underlineToHumpTopUpperCase(table.getName()))
                .append(Dto)
                .append(" ").append(BEGIN).append(LINE);
        return sb.toString();
    }
}
