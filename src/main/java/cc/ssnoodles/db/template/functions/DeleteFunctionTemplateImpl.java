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
    public String getTemplate(Table table) {
        String tableNameUpperCase = StringUtil.underlineToHumpTopUpperCase(table.getName());
        String tableName = StringUtil.underlineToHump(table.getName());

        StringBuilder sb = new StringBuilder();
        sb.append(SPACE).append("/**").append(LINE)
                .append(SPACE).append(" * 删除").append(LINE)
                .append(SPACE).append(" * @param id 主键").append(LINE)
                .append(SPACE).append(" */").append(LINE)
                .append(SPACE).append("@DeleteMapping(\"{id}\")").append(LINE)
                .append(SPACE).append("@Transactional(rollbackFor = Exception.class)").append(LINE)
                .append(SPACE).append("public void delete(@PathVariable String id) {").append(LINE)
                .append(SPACE).append(SPACE).append(tableNameUpperCase).append(" ").append(tableName).append(" = ").append(tableName).append(REPOSITORY).append(".findById(id).orElseThrow(NotFoundException::new);").append(LINE)
                .append(SPACE).append(SPACE).append(tableName).append(REPOSITORY).append(".delete(").append(tableName).append(");").append(LINE)
                .append(SPACE).append("}").append(LINE).append(LINE);
        return sb.toString();
    }
}
