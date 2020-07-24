package cc.ssnoodles.db.handler;

import cc.ssnoodles.db.constant.DbType;
import cc.ssnoodles.db.entity.Column;
import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.template.Template;
import cc.ssnoodles.db.util.DbCharsetTypeUtil;
import cc.ssnoodles.db.util.FileUtil;

import java.sql.*;
import java.util.*;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/8/27 08:41
 */
public interface DbHandler {

    String OUT_PATH = FileUtil.PROPERTIES.getOutpath();
    String USERNAME = FileUtil.PROPERTIES.getUsername();
    boolean OVERWRITE_FILES = FileUtil.PROPERTIES.isOverwritefiles();

    String LINE = System.getProperty("line.separator");

    void execute(List<Template> templates) throws SQLException;

    void execute(Template template, String tableName, String className) throws SQLException;

    default List<Table> getTables(Connection conn, String dbType, String userName, String tableNamePattern) throws SQLException {
        DatabaseMetaData dbMetData = conn.getMetaData();
        ResultSet rs = dbMetData.getTables(null, DbCharsetTypeUtil.convertDatabaseCharsetType(userName, dbType),
                tableNamePattern, new String[]{"TABLE"});
        List<Table> tableList = new ArrayList<>();
        while (rs.next()) {
            String tableName = rs.getString("TABLE_NAME");
            String tableRemarks = rs.getString("REMARKS");
            Table table = new Table();
            List<Column> columns = new ArrayList<>();
            ResultSet colRet;
            // https://stackoverflow.com/questions/38557956/databasemetadatagetcolumns-returns-an-empty-resultset
            if (DbType.ORACLE.getType().equals(dbType)) {
                colRet = dbMetData.getColumns(null, userName.toUpperCase(), tableName, "%");
            } else {
                colRet = dbMetData.getColumns(userName, null, tableName, "%");
            }

            while (colRet.next()) {
                String columnName = colRet.getString("COLUMN_NAME");
                String columnType = colRet.getString("TYPE_NAME");
                String columnRemarks = colRet.getString("REMARKS");
                int columnSize = colRet.getInt("COLUMN_SIZE");
                int nullable = colRet.getInt("NULLABLE");
                int decimalDigits = colRet.getInt("DECIMAL_DIGITS");
                Column column = new Column();
                column.setName(columnName);
                column.setType(columnType);
                column.setRemarks(columnRemarks);
                column.setDecimalDigits(decimalDigits);
                column.setSize(columnSize);
                column.setNullable(nullable == 1);
                columns.add(column);
            }

            table.setName(tableName);
            table.setRemarks(tableRemarks);
            table.setColumns(columns);

            ResultSet primaryKeysRet;
            if (DbType.ORACLE.getType().equals(dbType)) {
                primaryKeysRet = conn.getMetaData().getPrimaryKeys(null, userName.toUpperCase(), tableName);
            } else {
                primaryKeysRet = conn.getMetaData().getPrimaryKeys(userName, null, tableName);
            }
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
