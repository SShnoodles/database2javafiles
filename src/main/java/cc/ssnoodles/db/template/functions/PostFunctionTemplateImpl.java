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
        sb.append(SPACE).append("@PostMapping").append(LINE)
                .append(SPACE).append("@Transactional").append(LINE)
                .append(SPACE).append("@Operation(operationId = \"add").append(tableNameUpperCase).append("\", summary = \"新增\")").append(LINE)
                .append(SPACE).append("public ").append(tableNameUpperCase).append(INFO).append(" add(@Validated @RequestBody ").append(tableNameUpperCase).append(UPDATE).append(" data").append(") {").append(LINE)
                .append(SPACE).append(SPACE).append("var ").append(tableName).append(" = new ").append(tableNameUpperCase);
        if (table.getColumns().stream().anyMatch(column -> column.isPrimaryKey() && column.getType().equalsIgnoreCase("UUID"))) {
            sb.append("(UUID.randomUUID());");
        } else {
            sb.append("(UUID.randomUUID().toString());");
        }
        sb.append(LINE)
                .append(SPACE).append(SPACE).append("entityMapper.assign(").append(tableName).append(", data").append(");").append(LINE)
                .append(SPACE).append(SPACE).append("repository.save(").append(tableName).append(");").append(LINE)
                .append(SPACE).append(SPACE).append("return dataMapper.of(").append(tableName).append(");").append(LINE)
                .append(SPACE).append("}").append(LINE).append(LINE);
        return sb.toString();
    }
}
