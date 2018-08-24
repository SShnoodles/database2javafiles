package cc.ssnoodles.db.handler;

import cc.ssnoodles.db.constant.DbType;
import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.template.Templates;
import cc.ssnoodles.db.util.ConnUtil;
import cc.ssnoodles.db.util.FileUtil;
import cc.ssnoodles.db.util.StringUtil;

import java.sql.SQLException;
import java.util.*;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/7/13 08:46
 */
public class OracleDbHandler implements DbHandler {

    @Override
    public void execute() throws SQLException {
        List<Table> tableList = getTables(ConnUtil.getConn(), DbType.ORACLE.getType(), USERNAME);
        tableList.forEach(table -> FileUtil.write2JavaFiles(OUTPATH + StringUtil.underlineToHumpTopUpperCase(table.getName()),
                new Templates().common(table)));
    }
}
