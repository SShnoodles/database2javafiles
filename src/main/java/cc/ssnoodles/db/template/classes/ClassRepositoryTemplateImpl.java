package cc.ssnoodles.db.template.classes;

import cc.ssnoodles.db.constant.ColumnType;
import cc.ssnoodles.db.entity.Column;
import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.util.StringUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 仓库接口
 * @author ssnoodles
 * @version 1.0
 * Create at 2019-02-12 08:52
 */
public class ClassRepositoryTemplateImpl implements ClassTemplate{

    @Override
    public String getTemplate(Table table, String newClassName) {
        String tableName = StringUtil.isEmpty(newClassName) ? StringUtil.underlineToHumpTopUpperCase(table.getName()) : newClassName;
        StringBuilder sb = new StringBuilder();
        sb.append("public interface ")
                .append(tableName).append("Repository")
                .append(" extends JpaRepository<")
                .append(tableName).append(", ");
        List<Column> primaryKeys = table.getColumns().stream().filter(Column::isPrimaryKey).collect(Collectors.toList());
        if (primaryKeys.size() > 0) {
            Column column = primaryKeys.get(0);
            sb.append(ColumnType.get(column.getType())).append(">");
        }else {
            sb.append("String>");
        }
        sb.append(", QuerydslPredicateExecutor<").append(tableName).append(">");
        return sb.toString();
    }
}
