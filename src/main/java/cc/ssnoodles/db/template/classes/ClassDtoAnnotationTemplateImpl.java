package cc.ssnoodles.db.template.classes;

import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.util.StringUtil;

/**
 * @author sunshun
 * @version 1.0
 * Create at 2020/4/10 13:12
 */
public class ClassDtoAnnotationTemplateImpl implements ClassTemplate {
    @Override
    public String getTemplate(Table table, String newClassName) {
        String remark = StringUtil.isEmpty(table.getRemarks()) ? "" : table.getRemarks();
        return "@Schema(title = \"" + remark + "\")" + LINE;
    }
}
