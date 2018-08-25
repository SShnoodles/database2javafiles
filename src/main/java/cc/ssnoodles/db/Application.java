package cc.ssnoodles.db;

import cc.ssnoodles.db.factory.DbFactory;
import cc.ssnoodles.db.factory.DbFactoryImpl;
import cc.ssnoodles.db.template.CommonTemplate;
import cc.ssnoodles.db.template.DtoTemplate;
import cc.ssnoodles.db.template.JpaTemplate;
import cc.ssnoodles.db.util.FileUtil;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/7/12 18:39
 */
public class Application {

    private static final String DB = FileUtil.PROPERTIES.getProperty("db");

    public static void main(String[] args) throws Exception {
        DbFactory dbFactory = new DbFactoryImpl();
        // To specify template
        dbFactory.toJavaFiles(DB, new CommonTemplate());
    }

}