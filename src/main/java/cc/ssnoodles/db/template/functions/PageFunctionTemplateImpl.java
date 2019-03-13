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
 * Create at 2019-03-13 09:38
 */
public class PageFunctionTemplateImpl implements FunctionTemplate {
    @Override
    public String getTemplate(Table table) {
        String tableNameUpperCase = StringUtil.underlineToHumpTopUpperCase(table.getName());
        String tableName = StringUtil.underlineToHump(table.getName());

        StringBuilder sb = new StringBuilder();
        sb.append(SPACE).append("/**").append(LINE)
                .append(SPACE).append(" * 查询列表").append(LINE)
                .append(SPACE).append(" */").append(LINE)
                .append(SPACE).append("@GetMapping").append(LINE)
                .append(SPACE).append("public Page<").append(tableNameUpperCase).append(Dto).append("> get(@SortDefault(direction = Sort.Direction.DESC) Pageable pageable, ").append(tableNameUpperCase).append(CRITERIA).append(" criteria) {").append(LINE)
                .append(SPACE).append(SPACE).append("Q").append(tableNameUpperCase).append(" ").append(tableName).append(" = ").append("Q").append(tableNameUpperCase).append(".").append(tableName).append(";").append(LINE)
                .append(SPACE).append(SPACE).append("Predicate predicate = PredicateUtils.buildPredicate(builder -> builder").append(LINE);
        List<Column> columns = table.getColumns();
        columns.forEach(column -> sb.append(asPredicate(column, tableName)));
        sb.append(SPACE).append(SPACE).append(");").append(LINE)
                .append(SPACE).append(SPACE).append("return ").append(tableName).append(REPOSITORY).append(".findAll(predicate, pageable).map(dataMapper::of);").append(LINE)
                .append(SPACE).append("}").append(LINE).append(LINE);
        return sb.toString();
    }

    private String asPredicate(Column column, String qName) {
        StringBuilder sb = new StringBuilder();
        String columnName = StringUtil.underlineToHump(column.getName());
        if (column.isPrimaryKey()) {
            return sb.toString();
        } else if ("Integer".equals(ColumnType.get(column.getType()))
                || "Boolean".equals(ColumnType.get(column.getType()))
                || "BigDecimal".equals(ColumnType.get(column.getType()))) {
            sb.append(SPACE).append(SPACE).append(SPACE).append(SPACE).append(".bind(criteria.").append(columnName).append(", ").append(qName).append(".").append(columnName).append("::eq)").append(LINE);
        } else if (columnName.endsWith("Id") || columnName.endsWith("Uuid") || columnName.endsWith("Guid")) {
            sb.append(SPACE).append(SPACE).append(SPACE).append(SPACE).append(".bind(criteria.").append(columnName).append(", ").append(qName).append(".").append(columnName).append("::eq)").append(LINE);
        } else if ("LocalDate".equals(ColumnType.get(column.getType()))) {
            sb.append(SPACE).append(SPACE).append(SPACE).append(SPACE).append(".bind(criteria.").append(columnName).append("From").append(", ").append(qName).append(".").append(columnName).append("::goe)").append(LINE);
            sb.append(SPACE).append(SPACE).append(SPACE).append(SPACE).append(".bind(criteria.").append(columnName).append("To").append(", ").append(qName).append(".").append(columnName).append("::loe)").append(LINE);
        } else if ("OffsetDateTime".equals(ColumnType.get(column.getType()))) {
            sb.append(SPACE).append(SPACE).append(SPACE).append(SPACE).append(".bind(criteria.").append(columnName).append("From").append(", ").append("LocalTime.MIN, ").append(qName).append(".").append(columnName).append("::goe)").append(LINE);
            sb.append(SPACE).append(SPACE).append(SPACE).append(SPACE).append(".bind(criteria.").append(columnName).append("To").append(", ").append("LocalTime.MAX, ").append(qName).append(".").append(columnName).append("::loe)").append(LINE);
        } else {
            sb.append(SPACE).append(SPACE).append(SPACE).append(SPACE).append(".bind(criteria.").append(columnName).append(", v -> ").append(qName).append(".").append(columnName).append(".like('%' + v + '%'))").append(LINE);
        }
        return sb.toString();
    }
}
