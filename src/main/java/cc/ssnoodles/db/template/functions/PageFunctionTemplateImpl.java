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
    public String getTemplate(Table table, String newClassName) {
        String tableNameUpperCase = StringUtil.isEmpty(newClassName) ? StringUtil.underlineToHumpTopUpperCase(table.getName()) : newClassName;
        String tableName = StringUtil.isEmpty(newClassName) ? StringUtil.underlineToHump(table.getName()) : StringUtil.topLowerCase(newClassName);

        StringBuilder sb = new StringBuilder();
        sb.append(SPACE).append("/**").append(LINE)
                .append(SPACE).append(" * 查询列表").append(LINE)
                .append(SPACE).append(" * @param pageable 分页参数").append(LINE)
                .append(SPACE).append(" * @param criteria 查询条件").append(LINE)
                .append(SPACE).append(" * @return 列表数据").append(LINE)
                .append(SPACE).append(" */").append(LINE)
                .append(SPACE).append("@GetMapping").append(LINE)
                .append(SPACE).append("@Operation(summary = \"查询列表\")").append(LINE)
                .append(SPACE).append("public Page<").append(tableNameUpperCase).append(RECORD).append("> get(@SortDefault(direction = Sort.Direction.DESC) Pageable pageable, ").append(tableNameUpperCase).append(CRITERIA).append(" criteria) {").append(LINE)
                .append(SPACE).append(SPACE).append("var predicate = Predicates.build(Q").append(tableNameUpperCase).append(".class, (u, b) -> {").append(LINE);
        List<Column> columns = table.getColumns();
        columns.forEach(column -> sb.append(asPredicate(column)));
        sb.append(SPACE).append(SPACE).append("});").append(LINE)
                .append(SPACE).append(SPACE).append("return ").append(tableName).append(REPOSITORY).append(".findAll(predicate, pageable).map(dataMapper::of);").append(LINE)
                .append(SPACE).append("}").append(LINE).append(LINE);
        return sb.toString();
    }

    private String asPredicate(Column column) {
        StringBuilder sb = new StringBuilder();
        String columnName = StringUtil.underlineToHump(column.getName());
        if (column.isPrimaryKey()) {
            return sb.toString();
        } else if ("Integer".equals(ColumnType.get(column.getType()))
                || "Boolean".equals(ColumnType.get(column.getType()))
                || "BigDecimal".equals(ColumnType.get(column.getType()))) {
            sb.append(SPACE).append(SPACE).append(SPACE).append(SPACE).append("b.add(u.").append(columnName).append(", ").append("criteria.").append(columnName).append(");").append(LINE);
        } else if (columnName.endsWith("Id") || columnName.endsWith("Uuid") || columnName.endsWith("Guid")) {
            sb.append(SPACE).append(SPACE).append(SPACE).append(SPACE).append("b.add(u.").append(columnName).append(", ").append("criteria.").append(columnName).append(");").append(LINE);
        } else if ("LocalDate".equals(ColumnType.get(column.getType()))
                || "LocalDateTime".equals(ColumnType.get(column.getType()))
                || "OffsetDateTime".equals(ColumnType.get(column.getType()))) {
            sb.append(SPACE).append(SPACE).append(SPACE).append(SPACE).append("b.add(u.").append(columnName).append("::goe").append(", criteria.").append(columnName).append("From").append(", LocalTime.MIN);").append(LINE);
            sb.append(SPACE).append(SPACE).append(SPACE).append(SPACE).append("b.add(u.").append(columnName).append("::loe").append(", criteria.").append(columnName).append("To").append(", LocalTime.MAX);").append(LINE);
        } else {
            sb.append(SPACE).append(SPACE).append(SPACE).append(SPACE).append("b.add(u.").append(columnName).append(", ").append("'%' + criteria.").append(columnName).append(" + '%');").append(LINE);
        }
        return sb.toString();
    }
}
