package cc.ssnoodles.db.handler;

import cc.ssnoodles.db.entity.Column;
import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.template.data.ClassTemplate;
import cc.ssnoodles.db.template.data.HeadNoteTemplate;
import cc.ssnoodles.db.template.data.ImportTemplate;
import cc.ssnoodles.db.util.DbCharsetTypeUtil;
import cc.ssnoodles.db.util.FileUtil;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/8/23 23:56
 */
public interface DbHandler {

    String OUTPATH = FileUtil.PROPERTIES.getProperty("outpath");
    String USERNAME = FileUtil.PROPERTIES.getProperty("username");
    String LINE = System.getProperty("line.separator");

    void execute() throws SQLException;

    default List<Table> getTables(Connection conn, String dbType, String userName) throws SQLException {
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
            tableList.add(table);
            System.out.println(tableList.size() + ". " + tableName);

            ResultSet primaryKeysRet = conn.getMetaData().getPrimaryKeys(null, userName.toUpperCase(), tableName);
            while (primaryKeysRet.next()) {
                String columnName = primaryKeysRet.getString("COLUMN_NAME");
                for (Column column : columns) {
                    if (column.getName().equals(columnName)) {
                        column.setPrimaryKey(true);
                    }
                }
            }
        }
        return tableList;
    }
}
