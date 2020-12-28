package cc.ssnoodles.db.template.classes;

import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.util.EnglishUtil;
import cc.ssnoodles.db.util.StringUtil;

/**
 * 控制接口
 * @author ssnoodles
 * @version 1.0
 * Create at 2019-03-11 12:49
 */
public class ClassControllerTemplateImpl implements ClassTemplate {
    @Override
    public String getTemplate(Table table, String newClassName) {
        String tableNameUpperCase = StringUtil.isEmpty(newClassName) ? StringUtil.underlineToHumpTopUpperCase(table.getName()) : newClassName;
        String tableName = StringUtil.isEmpty(newClassName) ? StringUtil.underlineToHump(table.getName()) : StringUtil.topLowerCase(newClassName);
        String tableUrl = StringUtil.isEmpty(newClassName) ? StringUtil.underlineUrl(table.getName()) : StringUtil.toUrl(newClassName);
        String tag = StringUtil.isEmpty(table.getRemarks()) ? "" : table.getRemarks();
        StringBuilder sb = new StringBuilder();
        // 依赖
        sb.append("@RestController").append(LINE)
                .append("@RequestMapping(\"").append(EnglishUtil.simplePlural(tableUrl)).append("\")").append(LINE)
                .append("@Tag(name = \"").append(tag).append("\")").append(LINE)
                .append("public class ").append(tableNameUpperCase).append(CONTROLLER).append(" {").append(LINE)
                .append(LINE)
                .append(SPACE).append("private final ").append(tableNameUpperCase).append(REPOSITORY).append(" repository;").append(LINE)
                .append(LINE)
                .append(SPACE).append("private final EntityMapper entityMapper;").append(LINE)
                .append(LINE)
                .append(SPACE).append("private final DataMapper dataMapper;").append(LINE)
                .append(LINE)
                .append(SPACE).append("public ").append(tableNameUpperCase).append(CONTROLLER).append("(")
                .append(tableNameUpperCase).append(REPOSITORY).append(" repository, ").append("EntityMapper entityMapper").append(", ").append("DataMapper dataMapper").append(") {").append(LINE)
                .append(SPACE).append(SPACE).append("this.repository = repository;").append(LINE)
                .append(SPACE).append(SPACE).append("this.entityMapper = entityMapper;").append(LINE)
                .append(SPACE).append(SPACE).append("this.dataMapper = dataMapper;").append(LINE)
                .append(SPACE).append("}")
                .append(LINE).append(LINE);
        return sb.toString();
    }


}
