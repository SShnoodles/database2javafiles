package cc.ssnoodles.db.template.functions;

import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.util.StringUtil;

/**
 * 新增
 * @author ssnoodles
 * @version 1.0
 * Create at 2019-03-13 09:40
 */
public class PostFunctionTemplateImpl implements FunctionTemplate {
    @Override
    public String getTemplate(Table table, String newClassName) {
        String tableNameUpperCase = StringUtil.isEmpty(newClassName) ? StringUtil.underlineToHumpTopUpperCase(table.getName()) : newClassName;
        String tableName = StringUtil.isEmpty(newClassName) ? StringUtil.underlineToHump(table.getName()) : StringUtil.topLowerCase(newClassName);

        StringBuilder sb = new StringBuilder();
        sb.append(SPACE).append("/**").append(LINE)
                .append(SPACE).append(" * 新增").append(LINE)
                .append(SPACE).append(" * @param data 新增数据").append(LINE)
                .append(SPACE).append(" * @return 主键").append(LINE)
                .append(SPACE).append(" */").append(LINE)
                .append(SPACE).append("@PostMapping").append(LINE)
                .append(SPACE).append("@Transactional").append(LINE)
                .append(SPACE).append("@Operation(summary = \"新增\")").append(LINE)
                .append(SPACE).append("public ").append(tableNameUpperCase).append(RECORD).append(" create(@RequestBody ").append(tableNameUpperCase).append(NEW).append(" data").append(") {").append(LINE)
                .append(SPACE).append(SPACE).append(tableNameUpperCase).append(" ").append(tableName).append(" = new ").append(tableNameUpperCase).append("(UUID.randomUUID().toString());").append(LINE)
                .append(SPACE).append(SPACE).append("updater.assign(").append(tableName).append(", data").append(");").append(LINE)
                .append(SPACE).append(SPACE).append(tableName).append(REPOSITORY).append(".save").append("(").append(tableName).append(");").append(LINE)
                .append(SPACE).append(SPACE).append("return dataMapper.of(").append(tableName).append(");").append(LINE)
                .append(SPACE).append("}").append(LINE).append(LINE);
        return sb.toString();
    }
}
