package cc.ssnoodles.db.template.functions;

import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.util.StringUtil;

/**
 * 删除
 * @author ssnoodles
 * @version 1.0
 * Create at 2019-03-13 09:40
 */
public class DeleteFunctionTemplateImpl implements FunctionTemplate {
    @Override
    public String getTemplate(Table table, String newClassName) {
        String tableNameUpperCase = StringUtil.isEmpty(newClassName) ? StringUtil.underlineToHumpTopUpperCase(table.getName()) : newClassName;
        String tableName = StringUtil.isEmpty(newClassName) ? StringUtil.underlineToHump(table.getName()) : StringUtil.topLowerCase(newClassName);

        StringBuilder sb = new StringBuilder();
        sb.append(SPACE).append("/**").append(LINE)
                .append(SPACE).append(" * 删除").append(LINE)
                .append(SPACE).append(" * @param id 主键").append(LINE)
                .append(SPACE).append(" */").append(LINE)
                .append(SPACE).append("@DeleteMapping(\"{id}\")").append(LINE)
                .append(SPACE).append("@Transactional").append(LINE)
                .append(SPACE).append("@Operation(summary = \"删除\")").append(LINE)
                .append(SPACE).append("public void delete(@PathVariable String id) {").append(LINE)
                .append(SPACE).append(SPACE).append(tableNameUpperCase).append(" ").append(tableName).append(" = ").append(tableName).append(REPOSITORY).append(".findById(id).orElseThrow(NotFoundException::new);").append(LINE)
                .append(SPACE).append(SPACE).append(tableName).append(REPOSITORY).append(".delete(").append(tableName).append(");").append(LINE)
                .append(SPACE).append("}").append(LINE).append(LINE);
        return sb.toString();
    }
}
