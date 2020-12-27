package cc.ssnoodles.db.template.functions;

import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.util.StringUtil;

/**
 * 删除
 * @author ssnoodles
 * @version 1.0
 * Create at 2020-12-27 13:26
 */
public class DeleteFunctionTypedTemplateImpl implements FunctionTemplate {
    @Override
    public String getTemplate(Table table, String newClassName) {
        String tableNameUpperCase = StringUtil.isEmpty(newClassName) ? StringUtil.underlineToHumpTopUpperCase(table.getName()) : newClassName;
        String tableName = StringUtil.isEmpty(newClassName) ? StringUtil.underlineToHump(table.getName()) : StringUtil.topLowerCase(newClassName);

        StringBuilder sb = new StringBuilder();
        sb.append(SPACE).append("@DeleteMapping(\"{id}\")").append(LINE)
                .append(SPACE).append("@ResponseStatus(HttpStatus.NO_CONTENT)").append(LINE)
                .append(SPACE).append("@Transactional").append(LINE)
                .append(SPACE).append("@Operation(operationId = \"delete").append(tableNameUpperCase).append("\", summary = \"删除\")").append(LINE)
                .append(SPACE);
        if (table.getColumns().stream().anyMatch(column -> column.isPrimaryKey() && column.getType().equalsIgnoreCase("UUID"))) {
            sb.append("public void delete(@PathVariable UUID id) {");
        } else {
            sb.append("public void delete(@PathVariable String id) {");
        }
        sb.append(LINE)
                .append(SPACE).append(SPACE).append("var ").append(tableName).append(" = repository.loadUser(id).orElseThrow(ResourceNotFoundException::new);").append(LINE)
                .append(SPACE).append(SPACE).append("repository.remove(").append(tableName).append(");").append(LINE)
                .append(SPACE).append("}").append(LINE).append(LINE);
        return sb.toString();
    }
}
