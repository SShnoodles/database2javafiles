package cc.ssnoodles.db;

import cc.ssnoodles.db.entity.Config;
import cc.ssnoodles.db.factory.DbFactory;
import cc.ssnoodles.db.factory.DbFactoryImpl;
import cc.ssnoodles.db.template.*;
import cc.ssnoodles.db.util.FileUtil;

import java.util.List;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/7/12 18:39
 */
public class Application {

    /**
     * Specify template
     * 1.JpaTemplate、DtoTemplate、DoTemplate、ControllerTemplate
     * 2.If you need a specific template, implement Template interface.
     * 3.If you need a specific sub-template, implement them：
     *  ImportTemplate、ClassTemplate、FieldTemplate
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // load config
        final Config config = FileUtil.PROPERTIES;
        // create factory
        DbFactory dbFactory = new DbFactoryImpl();
        // generate templates
        List<Template> templates = dbFactory.getTemplates(config.getTemplates());

        // generate jpa templates
//        dbFactory.create(config.getDb(), new JpaTemplate());

        // generate template from the specified table
//        dbFactory.create(config.getDb(), new JpaTemplate(), config.getSingleTableName(), config.getSingleTableRename());
    }

}