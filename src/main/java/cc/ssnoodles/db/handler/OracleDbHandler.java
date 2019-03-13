package cc.ssnoodles.db.handler;

import cc.ssnoodles.db.constant.DbType;
import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.template.Template;
import cc.ssnoodles.db.util.*;

import java.sql.SQLException;
import java.util.List;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/8/27 08:42
 */
public class OracleDbHandler implements DbHandler {

    @Override
    public void execute(List<Template> templates) throws SQLException {
        List<Table> tableList = getTables(ConnUtil.getConn(), DbType.ORACLE.getType(), USERNAME);
        tableList.forEach(table -> {
            templates.forEach(template -> FileUtil.write2JavaFiles(
                    OUTPATH + StringUtil.underlineToHumpTopUpperCase(table.getName()) + template.endsWith(),
                    template.tableDataToString(table)));
        });
    }
}
