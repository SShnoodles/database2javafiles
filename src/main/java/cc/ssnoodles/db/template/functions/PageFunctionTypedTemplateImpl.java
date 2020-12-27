package cc.ssnoodles.db.template.functions;

import cc.ssnoodles.db.constant.ColumnType;
import cc.ssnoodles.db.entity.Column;
import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.util.StringUtil;

import java.util.List;

/**
 * 多条
 * @author ssnoodles
 * @version 1.0
 * Create at 2020-12-27 13:26
 */
public class PageFunctionTypedTemplateImpl implements FunctionTemplate {
    @Override
    public String getTemplate(Table table, String newClassName) {
        String tableNameUpperCase = StringUtil.isEmpty(newClassName) ? StringUtil.underlineToHumpTopUpperCase(table.getName()) : newClassName;
        String tableName = StringUtil.isEmpty(newClassName) ? StringUtil.underlineToHump(table.getName()) : StringUtil.topLowerCase(newClassName);

        StringBuilder sb = new StringBuilder();
        sb.append(SPACE).append("@GetMapping").append(LINE)
                .append(SPACE).append("@Operation(operationId = \"get").append(tableNameUpperCase).append("s\", summary = \"查询列表\")").append(LINE)
                .append(SPACE).append("public List<").append(tableNameUpperCase).append(INFO).append("> get(").append(tableNameUpperCase).append(CRITERIA).append(" criteria) {").append(LINE)
                .append(SPACE).append(SPACE).append("return repository.findAll(criteria).map(dataMapper::of);").append(LINE)
                .append(SPACE).append("}").append(LINE).append(LINE);
        return sb.toString();
    }
}
