package cc.ssnoodles.db.template.classes;

import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.util.StringUtil;

/**
 * 控制接口
 * @author ssnoodles
 * @version 1.0
 * Create at 2019-03-11 12:49
 */
public class ClassControllerTemplateImpl implements ClassTemplate {
    @Override
    public String getTemplate(Table table) {
        String tableNameUpperCase = StringUtil.underlineToHumpTopUpperCase(table.getName());
        String tableName = StringUtil.underlineToHump(table.getName());
        String tableUrl = StringUtil.underlineUrl(table.getName());

        StringBuilder sb = new StringBuilder();
        // 依赖
        sb.append("@RestController").append(LINE)
                .append("@RequestMapping(\"").append(tableUrl).append("\")").append(LINE)
                .append("public class ").append(tableNameUpperCase).append(CONTROLLER).append(" {").append(LINE)
                .append(LINE)
                .append(SPACE).append("@Autowired").append(LINE)
                .append(SPACE).append(tableNameUpperCase).append(REPOSITORY).append(" ").append(tableName).append(REPOSITORY).append(";").append(LINE)
                .append(LINE)
                .append(SPACE).append("@Autowired").append(LINE)
                .append(SPACE).append("Updater updater;").append(LINE)
                .append(LINE)
                .append(SPACE).append("@Autowired").append(LINE)
                .append(SPACE).append("DataMapper dataMapper;").append(LINE)
                .append(LINE);
        return sb.toString();
    }


}
