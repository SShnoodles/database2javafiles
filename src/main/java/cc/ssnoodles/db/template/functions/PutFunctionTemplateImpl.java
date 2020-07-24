package cc.ssnoodles.db.template.functions;

import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.util.StringUtil;

/**
 * 修改
 * @author ssnoodles
 * @version 1.0
 * Create at 2019-03-13 09:40
 */
public class PutFunctionTemplateImpl implements FunctionTemplate {
    @Override
    public String getTemplate(Table table, String newClassName) {
        String tableNameUpperCase = StringUtil.isEmpty(newClassName) ? StringUtil.underlineToHumpTopUpperCase(table.getName()) : newClassName;
        String tableName = StringUtil.isEmpty(newClassName) ? StringUtil.underlineToHump(table.getName()) : StringUtil.topLowerCase(newClassName);

        StringBuilder sb = new StringBuilder();
        sb.append(SPACE).append("@PutMapping(\"{id}\")").append(LINE)
                .append(SPACE).append("@Transactional").append(LINE)
                .append(SPACE).append("@Operation(summary = \"修改\")").append(LINE);
        if (table.getColumns().stream().anyMatch(column -> column.isPrimaryKey() && column.getType().equalsIgnoreCase("UUID"))) {
            sb.append(SPACE).append("public void update(@PathVariable UUID id, @Validated @RequestBody ");
        } else {
            sb.append(SPACE).append("public void update(@PathVariable String id, @Validated @RequestBody ");
        }
        sb.append(tableNameUpperCase).append(UPDATE).append(" data").append(") {").append(LINE)
                .append(SPACE).append(SPACE).append(tableNameUpperCase).append(" ").append(tableName).append(" = ").append(tableName).append(REPOSITORY).append(".findById(id).orElseThrow(NotFoundException::new);").append(LINE)
                .append(SPACE).append(SPACE).append("entityMapper.assign(").append(tableName).append(", data").append(");").append(LINE)
                .append(SPACE).append(SPACE).append(tableName).append(REPOSITORY).append(".save(").append(tableName).append(");").append(LINE)
                .append(SPACE).append("}").append(LINE).append(LINE);
        return sb.toString();
    }
}
