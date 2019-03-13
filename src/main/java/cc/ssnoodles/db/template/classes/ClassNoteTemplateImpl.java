package cc.ssnoodles.db.template.classes;

import cc.ssnoodles.db.entity.Config;
import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.util.*;

/**
 * 类注释
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/8/27 09:01
 */
public class ClassNoteTemplateImpl implements ClassTemplate {

    @Override
    public String getTemplate(Table table) {
        Config config = FileUtil.PROPERTIES;
        StringBuilder sb = new StringBuilder();
        sb.append("/**").append(LINE)
                .append(" * ").append(table.getRemarks() == null ? "" : table.getRemarks()).append(LINE)
                .append(" * @author ").append(StringUtil.isEmpty(config.getAuthor()) ? System.getProperty("user.name") : config.getAuthor()).append(LINE)
                .append(" * Create at ").append(TimeUtil.getTime()).append(LINE)
                .append(" */").append(LINE);
        return sb.toString();
    }
}
