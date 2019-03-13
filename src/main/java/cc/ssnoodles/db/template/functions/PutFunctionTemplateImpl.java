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
    public String getTemplate(Table table) {
        String tableNameUpperCase = StringUtil.underlineToHumpTopUpperCase(table.getName());
        String tableName = StringUtil.underlineToHump(table.getName());

        StringBuilder sb = new StringBuilder();
        sb.append(SPACE).append("/**").append(LINE)
                .append(SPACE).append(" * 修改").append(LINE)
                .append(SPACE).append(" */").append(LINE)
                .append(SPACE).append("@PutMapping").append(LINE)
                .append(SPACE).append("@Transactional(rollbackFor = Exception.class)").append(LINE)
                .append(SPACE).append("public void update(@PathVariable String id, @RequestBody ").append(tableNameUpperCase).append(FORM).append(" form").append(") {").append(LINE)
                .append(SPACE).append(SPACE).append(tableNameUpperCase).append(" ").append(tableName).append(" = ").append(tableName).append(REPOSITORY).append(".findById(id).orElseThrow(NotFoundException::new);").append(LINE)
                .append(SPACE).append(SPACE).append("updater.assign(").append(tableName).append(", form").append(");").append(LINE)
                .append(SPACE).append(SPACE).append(tableName).append(REPOSITORY).append(".save(").append(tableName).append(");").append(LINE)
                .append(SPACE).append("}").append(LINE).append(LINE);
        return sb.toString();
    }
}
