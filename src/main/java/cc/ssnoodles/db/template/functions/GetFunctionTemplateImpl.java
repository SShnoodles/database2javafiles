package cc.ssnoodles.db.template.functions;

import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.util.StringUtil;

/**
 * 单条
 * @author ssnoodles
 * @version 1.0
 * Create at 2019-03-13 09:39
 */
public class GetFunctionTemplateImpl implements FunctionTemplate {
    @Override
    public String getTemplate(Table table, String newClassName) {
        String tableNameUpperCase = StringUtil.isEmpty(newClassName) ? StringUtil.underlineToHumpTopUpperCase(table.getName()) : newClassName;
        String tableName = StringUtil.isEmpty(newClassName) ? StringUtil.underlineToHump(table.getName()) : StringUtil.topLowerCase(newClassName);

        StringBuilder sb = new StringBuilder();
        sb.append(SPACE).append("/**").append(LINE)
                .append(SPACE).append(" * 查询单条").append(LINE)
                .append(SPACE).append(" * @param id 主键").append(LINE)
                .append(SPACE).append(" * @return 单条数据").append(LINE)
                .append(SPACE).append(" */").append(LINE)
                .append(SPACE).append("@GetMapping(\"{id}\")").append(LINE)
                .append(SPACE).append("@Operation(summary = \"查询单条\")").append(LINE)
                .append(SPACE).append("public ").append(tableNameUpperCase).append(RECORD).append(" get(@PathVariable String id) {").append(LINE)
                .append(SPACE).append(SPACE).append("return ").append(tableName).append(REPOSITORY).append(".findById(id).map(dataMapper::of).orElseThrow(NotFoundException::new);").append(LINE)
                .append(SPACE).append("}").append(LINE).append(LINE);
        return sb.toString();
    }
}
