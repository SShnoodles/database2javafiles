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
        }
    }

    public StringBuilder tableDataToString(Table table) {
        StringBuilder sb = new StringBuilder();
        sb.append("import lombok.Data;").append(LINE)
                .append("import javax.persistence.*;").append(LINE)
                .append("import java.math.BigDecimal;").append(LINE)
                .append("import java.util.Date;").append(LINE)
                .append("/**").append(LINE)
                .append(" * ").append(table.getRemarks() == null ? "" : table.getRemarks()).append(LINE)
                .append(" */").append(LINE)
                .append("@Data").append(LINE)
                .append("@Entity").append(LINE)
                .append("@Table(name = \"").append(table.getName()).append("\")").append(LINE)
                .append("public class ").append(StringUtil.underlineToHumpTopUpperCase(table.getName())).append(" {").append(LINE);
        List<Column> columns = table.getColumns();
        for (Column column : columns) {
            sb.append("    /**").append(LINE)
                    .append("     * ").append(column.getRemarks() == null ? "" : column.getRemarks()).append(LINE)
                    .append("     */").append(LINE)
                    .append("    @Column(name = \"").append(column.getName()).append("\")").append(LINE)
                    .append("    private ").append(ColumnType.get(column.getType().toUpperCase())).append(" ").append(StringUtil.underlineToHump(column.getName())).append(";").append(LINE);
        }
        sb.append("}");
        return sb;
    }

    public List<Table> getTables(Connection conn, String dbType, String userName) throws SQLException {
        DatabaseMetaData dbMetData = conn.getMetaData();
        ResultSet rs = dbMetData.getTables(null, DbCharsetTypeUtil.convertDatabaseCharsetType(userName.toUpperCase(), dbType),
                null, new String[]{"TABLE"});
        List<Table> tableList = new ArrayList<>();
        while (rs.next()) {
            String tableName = rs.getString("TABLE_NAME");
            String tableRemarks = rs.getString("REMARKS");
            Table table = new Table();
            List<Column> columns = new ArrayList<>();
            ResultSet colRet = dbMetData.getColumns(null, "%", tableName, "%");

            while (colRet.next()) {
                String columnName = colRet.getString("COLUMN_NAME");
                String columnType = colRet.getString("TYPE_NAME");
                String columnRemarks = colRet.getString("REMARKS");
                Column column = new Column();
                column.setName(columnName);
                column.setType(columnType);
                column.setRemarks(columnRemarks);
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
            tableList.add(table);
            System.out.println(tableList.size() + ". " + tableName);
        }
        return tableList;
    }
}
