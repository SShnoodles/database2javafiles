package cc.ssnoodles.db.template.functions;

import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.util.StringUtil;

/**
 * 单条
 * @author ssnoodles
 * @version 1.0
 * Create at 2020-12-27 13:26
 */
public class GetFunctionTypedTemplateImpl implements FunctionTemplate {
    @Override
    public String getTemplate(Table table, String newClassName) {
        String tableNameUpperCase = StringUtil.isEmpty(newClassName) ? StringUtil.underlineToHumpTopUpperCase(table.getName()) : newClassName;

        StringBuilder sb = new StringBuilder();
        sb.append(SPACE).append("@GetMapping(\"{id}\")").append(LINE)
                .append(SPACE).append("@Operation(operationId = \"get").append(tableNameUpperCase).append("\", summary = \"查询单条\")").append(LINE)
                .append(SPACE).append("public ").append(tableNameUpperCase).append(INFO);
        if (table.getColumns().stream().anyMatch(column -> column.isPrimaryKey() && column.getType().equalsIgnoreCase("UUID"))) {
            sb.append(" get(@PathVariable UUID id) {");
        } else {
            sb.append(" get(@PathVariable String id) {");
        }
        sb.append(LINE)
                .append(SPACE).append(SPACE).append("return repository.load").append(tableNameUpperCase).append("(id).map(dataMapper::of).orElseThrow(ResourceNotFoundException::new);").append(LINE)
                .append(SPACE).append("}").append(LINE).append(LINE);
        return sb.toString();
    }
}
