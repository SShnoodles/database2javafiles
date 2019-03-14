package cc.ssnoodles.db.template.functions;

import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.util.StringUtil;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2019-03-14 08:58
 */
public class UpdaterFunctionTemplateImpl implements FunctionTemplate {
    @Override
    public String getTemplate(Table table) {
        String tableNameUpperCase = StringUtil.underlineToHumpTopUpperCase(table.getName());
        String tableName = StringUtil.underlineToHump(table.getName());
        StringBuilder sb = new StringBuilder();
        sb.append(SPACE).append("void assign(@MappingTarget ")
                .append(tableNameUpperCase).append(" ").append(tableName).append(", ")
                .append(tableNameUpperCase).append(FORM).append(" ").append("form").append(");")
                .append(LINE);
        return sb.toString();
    }
}
