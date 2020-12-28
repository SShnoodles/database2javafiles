package cc.ssnoodles.db.template.functions;

import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.util.StringUtil;

/**
 * patch method controller function
 * @author ssnoodles
 * @version 1.0
 * Create at 2020-12-27 13:38
 */
public class PatchFunctionTemplateImpl implements FunctionTemplate {
    @Override
    public String getTemplate(Table table, String newClassName) {
        String tableNameUpperCase = StringUtil.isEmpty(newClassName) ? StringUtil.underlineToHumpTopUpperCase(table.getName()) : newClassName;
        String tableName = StringUtil.isEmpty(newClassName) ? StringUtil.underlineToHump(table.getName()) : StringUtil.topLowerCase(newClassName);

        StringBuilder sb = new StringBuilder();
        sb.append(SPACE).append("@PatchMapping(\"{id}\")").append(LINE)
                .append(SPACE).append("@ResponseStatus(HttpStatus.NO_CONTENT)").append(LINE)
                .append(SPACE).append("@Transactional").append(LINE)
                .append(SPACE).append("@Operation(operationId = \"update").append(tableNameUpperCase).append("\", summary = \"局部更新\")").append(LINE);
        if (table.getColumns().stream().anyMatch(column -> column.isPrimaryKey() && column.getType().equalsIgnoreCase("UUID"))) {
            sb.append(SPACE).append("public void update(@PathVariable UUID id, @Validated @RequestBody ");
        } else {
            sb.append(SPACE).append("public void update(@PathVariable String id, @Validated @RequestBody ");
        }
        sb.append("MergePatch<").append(tableNameUpperCase).append(UPDATE).append("> patch) {").append(LINE)
                .append(SPACE).append(SPACE).append("var ").append(tableName).append(" = repository.findById(id).orElseThrow(ResourceNotFoundException::new);").append(LINE)
                .append(SPACE).append(SPACE).append("var info = dataMapper.toUpdate(").append(tableName).append(");").append(LINE)
                .append(SPACE).append(SPACE).append("var patchInfo = patch.apply(info);").append(LINE)
                .append(SPACE).append(SPACE).append("entityMapper.assign(").append(tableName).append(", patchInfo);").append(LINE)
                .append(SPACE).append("}").append(LINE).append(LINE);
        return sb.toString();
    }
}
