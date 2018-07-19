package cc.ssnoodles.db.handler;

import cc.ssnoodles.db.constant.ColumnType;
import cc.ssnoodles.db.entity.Column;
import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.util.ConnUtil;
import cc.ssnoodles.db.util.DbCharsetTypeUtil;
import cc.ssnoodles.db.util.FileUtil;
import cc.ssnoodles.db.util.StringUtil;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/7/13 08:46
 */
public class DbHandler {

    public static final String LINE = System.getProperty("line.separator");

    public void handle(String db) throws SQLException {
        Properties properties = FileUtil.PROPERTIES;
        String outpath = properties.getProperty("outpath");
        String username = properties.getProperty("username");

        DbHandler dbHandler = new DbHandler();
        List<Table> tableList = dbHandler.getTables(ConnUtil.getConn(), db, username);
        for (Table table : tableList) {
            StringBuilder sb = dbHandler.tableDataToString(table);
            FileUtil.write2JavaFiles(outpath + StringUtil.underlineToHumpTopUpperCase(table.getName()), sb);

            StringBuilder sbDto = dbHandler.tableDataToStringDto(table);
            FileUtil.write2JavaFiles(outpath + StringUtil.underlineToHumpTopUpperCase(table.getName()) + "Dto", sbDto);
        }
    }

    public StringBuilder tableDataToStringDto(Table table) {
        StringBuilder sb = new StringBuilder();
        // import
        sb.append("import lombok.Data;").append(LINE)
                .append("import javax.persistence.*;").append(LINE)
                .append("import java.math.BigDecimal;").append(LINE)
                .append("import java.util.Date;").append(LINE);
        // head
        sb.append("/**").append(LINE)
                .append(" * ").append(table.getRemarks() == null ? "" : table.getRemarks()).append(LINE)
                .append(" */").append(LINE)
                .append("@Data").append(LINE);

        // class
        String tableName = StringUtil.underlineToHumpTopUpperCase(table.getName());
        sb.append("public class ").append(tableName).append("Dto").append(" {").append(LINE);

        List<Column> columns = table.getColumns();
        for (Column column : columns) {
            sb.append("    /**").append(LINE)
                    .append("     * ").append(column.getRemarks() == null ? "" : column.getRemarks()).append(LINE)
                    .append("     */").append(LINE);
            sb.append("    private ");
            if (!column.isDecimalDigits() && "NUMBER".equals(column.getType())) {
                sb.append("Integer");
            }else {
                sb.append(ColumnType.get(column.getType().toUpperCase()));
            }
            sb.append(" ").append(StringUtil.underlineToHump(column.getName())).append(";").append(LINE);
        }
        sb.append("}");
        return sb;
    }

    public StringBuilder tableDataToString(Table table) {
        StringBuilder sb = new StringBuilder();
        // import
        sb.append("import lombok.Data;").append(LINE)
                .append("import javax.persistence.*;").append(LINE)
                .append("import java.math.BigDecimal;").append(LINE)
                .append("import java.util.Date;").append(LINE);
        // head
        sb.append("/**").append(LINE)
                .append(" * ").append(table.getRemarks() == null ? "" : table.getRemarks()).append(LINE)
                .append(" */").append(LINE)
                .append("@Entity").append(LINE)
                .append("@Table(name = \"").append(table.getName()).append("\")").append(LINE)
                .append("@Data").append(LINE);
        List<String> primaryKeys = table.getPrimaryKeys();
        String primaryKeysString = null;
        if (primaryKeys != null && primaryKeys.size() > 0) {
            if (1 == primaryKeys.size()) {
                primaryKeysString = "\"" + StringUtil.underlineToHump(primaryKeys.get(0)) + "\"";
            }else {
                primaryKeysString = "{ ";
                for (String primaryKey : primaryKeys) {
                    primaryKeysString = primaryKeysString + "\"" + StringUtil.underlineToHump(primaryKey) + "\", ";
                }
                primaryKeysString = primaryKeysString.substring(0, primaryKeysString.length() - 2);
                primaryKeysString = primaryKeysString + " }";
            }
        }
        if (primaryKeysString != null) {
            sb.append("@EqualsAndHashCode(of = ").append(primaryKeysString).append(")").append(LINE);
        }

        // class
        String tableName = StringUtil.underlineToHumpTopUpperCase(table.getName());
        sb.append("public class ").append(tableName).append(" {").append(LINE);

        List<Column> primaryKeyColumns = table.getColumns().stream().filter(Column::isPrimaryKey).collect(Collectors.toList());
        String columnTypeAndNames = "";
        String columnTypeEqNames = null;
        if (primaryKeyColumns != null && primaryKeyColumns.size() > 0) {
            for (Column column : primaryKeyColumns) {
                columnTypeAndNames = "String " + StringUtil.underlineToHump(column.getName()) + ", ";
                columnTypeEqNames = "this." + StringUtil.underlineToHump(column.getName()) + " = " + StringUtil.underlineToHump(column.getName()) + ";,";
            }
            columnTypeAndNames = columnTypeAndNames.substring(0, columnTypeAndNames.length() - 2);
            columnTypeEqNames = columnTypeEqNames.substring(0, columnTypeEqNames.length() - 1);
        }
        sb.append("    public ").append(tableName).append("(").append(columnTypeAndNames).append(") {").append(LINE);
        if (columnTypeEqNames != null) {
            String[] split = columnTypeEqNames.split(",");
            for (String s : split) {
                sb.append("        ").append(s).append(LINE);
            }
        }
        sb.append("    }").append(LINE);

        List<Column> columns = table.getColumns();
        for (Column column : columns) {
            sb.append("    /**").append(LINE)
                    .append("     * ").append(column.getRemarks() == null ? "" : column.getRemarks()).append(LINE)
                    .append("     */").append(LINE);
            if (column.isPrimaryKey()) {
                sb.append("    @Id").append(LINE);
                sb.append("    @Setter(AccessLevel.PROTECTED)").append(LINE);
            }
            sb.append("    @Column(name = \"").append(column.getName()).append("\")").append(LINE)
                    .append("    private ");
            if (!column.isDecimalDigits() && "NUMBER".equals(column.getType())) {
                sb.append("Integer");
            }else {
                sb.append(ColumnType.get(column.getType().toUpperCase()));
            }
            sb.append(" ").append(StringUtil.underlineToHump(column.getName())).append(";").append(LINE);
        }
        sb.append("}");
        return sb;
    }

    public List<Table> getTables(Connection conn, String dbType, String userName) throws SQLException {
        DatabaseMetaData dbMetData = conn.getMetaData();
        ResultSet rs = dbMetData.getTables(null, DbCharsetTypeUtil.convertDatabaseCharsetType(userName, dbType),
                null, new String[]{"TABLE"});
        List<Table> tableList = new ArrayList<>();
        while (rs.next()) {
            String tableName = rs.getString("TABLE_NAME");
            String tableRemarks = rs.getString("REMARKS");
            Table table = new Table();
            List<Column> columns = new ArrayList<>();
            ResultSet colRet = dbMetData.getColumns(null, userName.toUpperCase(), tableName, "%");


            while (colRet.next()) {
                String columnName = colRet.getString("COLUMN_NAME");
                String columnType = colRet.getString("TYPE_NAME");
                String columnRemarks = colRet.getString("REMARKS");
                int decimalDigits = colRet.getInt("DECIMAL_DIGITS");
                Column column = new Column();
                column.setName(columnName);
                column.setType(columnType);
                column.setRemarks(columnRemarks);
                column.setDecimalDigits(decimalDigits > 0);
                columns.add(column);
            }

            Set<String> set = new HashSet<>();
            List<Column> newColumns = new ArrayList<>();
            for (Column column : columns) {
                if(set.add(column.getName())){
                    newColumns.add(column);
                }
            }
            table.setName(tableName);
            table.setRemarks(tableRemarks);
            table.setColumns(newColumns);

            ResultSet primaryKeysRet = conn.getMetaData().getPrimaryKeys(null, userName.toUpperCase(), tableName);
            List<String> primaryKeys = new ArrayList<>();
            while (primaryKeysRet.next()) {
                String columnName = primaryKeysRet.getString("COLUMN_NAME");
                for (Column column : columns) {
                    if (column.getName().equals(columnName)) {
                        column.setPrimaryKey(true);
                    }
                }
                primaryKeys.add(columnName);

            }
            table.setPrimaryKeys(primaryKeys);
            tableList.add(table);
            System.out.println(tableList.size() + ". " + tableName);
        }
        return tableList;
    }
}
