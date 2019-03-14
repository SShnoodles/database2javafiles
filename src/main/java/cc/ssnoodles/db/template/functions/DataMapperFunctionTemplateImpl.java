package cc.ssnoodles.db.template.functions;

import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.util.StringUtil;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2019-03-14 08:57
 */
public class DataMapperFunctionTemplateImpl implements FunctionTemplate {
    @Override
    public String getTemplate(Table table) {
        String tableNameUpperCase = StringUtil.underlineToHumpTopUpperCase(table.getName());
        String tableName = StringUtil.underlineToHump(table.getName());
        StringBuilder sb = new StringBuilder();
        sb.append(SPACE).append(tableNameUpperCase).append(Dto).append(" of(")
                .append(tableNameUpperCase).append(" ").append(tableName).append(");").append(LINE);
        return sb.toString();
    }
}
