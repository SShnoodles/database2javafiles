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
    public String getTemplate(Table table) {
        String tableNameUpperCase = StringUtil.underlineToHumpTopUpperCase(table.getName());
        String tableName = StringUtil.underlineToHump(table.getName());

        StringBuilder sb = new StringBuilder();
        sb.append(SPACE).append("/**").append(LINE)
                .append(SPACE).append(" * 新增").append(LINE)
                .append(SPACE).append(" */").append(LINE)
                .append(SPACE).append("@PostMapping").append(LINE)
                .append(SPACE).append("@Transactional(rollbackFor = Exception.class)").append(LINE)
                .append(SPACE).append("public ").append(tableNameUpperCase).append(REF).append(" create(@RequestBody ").append(tableNameUpperCase).append(FORM).append(" form").append(") {").append(LINE)
                .append(SPACE).append(SPACE).append("String id = UUID.randomUUID().toString();").append(LINE)
                .append(SPACE).append(SPACE).append(tableNameUpperCase).append(" ").append(tableName).append(" = new ").append(tableNameUpperCase).append("(id);").append(LINE)
                .append(SPACE).append(SPACE).append("updater.assign(").append(tableName).append(", form").append(");").append(LINE)
                .append(SPACE).append(SPACE).append(tableName).append(REPOSITORY).append(".save").append("(").append(tableName).append(");").append(LINE)
                .append(SPACE).append(SPACE).append("return ").append(tableNameUpperCase).append(REF).append(".builder().id(id).build();").append(LINE)
                .append(SPACE).append("}").append(LINE).append(LINE);
        return sb.toString();
    }
}
