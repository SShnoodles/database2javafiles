package cc.ssnoodles.db.factory;

import cc.ssnoodles.db.constant.DbType;
import cc.ssnoodles.db.constant.TemplateType;
import cc.ssnoodles.db.handler.*;
import cc.ssnoodles.db.template.*;

import java.sql.SQLException;
import java.util.*;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/8/27 08:36
 */
public class DbFactoryImpl implements DbFactory {

    @Override
    public void create(String dbType, List<Template> templates) throws SQLException{
        if (DbType.ORACLE.getType().equals(dbType.toLowerCase())) {
            new OracleDbHandler().execute(templates);
        }
        if (DbType.POSTGRESQL.getType().equals(dbType.toLowerCase())) {
            new PostgreDbHandler().execute(templates);
        }
        if (DbType.MYSQL.getType().equals(dbType.toLowerCase())) {
            new MysqlDbHandler().execute(templates);
        }
    }

    @Override
    public void create(String dbType, Template template) throws SQLException {
        create(dbType, Collections.singletonList(template));
    }

    @Override
    public void create(String dbType, Template template, String singleTableName, String singleTableRename) throws SQLException {
        if (DbType.ORACLE.getType().equals(dbType.toLowerCase())) {
            new OracleDbHandler().execute(template, singleTableName, singleTableRename);
        }
        if (DbType.POSTGRESQL.getType().equals(dbType.toLowerCase())) {
            new PostgreDbHandler().execute(template, singleTableName, singleTableRename);
        }
        if (DbType.MYSQL.getType().equals(dbType.toLowerCase())) {
            new MysqlDbHandler().execute(template, singleTableName, singleTableRename);
        }
    }

    @Override
    public List<Template> getTemplates(String[] templateTypes) {
        List<Template> templates = new ArrayList<>(8);
        for (String templateType : templateTypes) {
            if (TemplateType.JPA.getType().equalsIgnoreCase(templateType)) {
                templates.add(new JpaTemplate());
            }
            else if (TemplateType.DTO.getType().equalsIgnoreCase(templateType)) {
                templates.add(new DtoTemplate());
            }
            else if (TemplateType.REPOSITORY.getType().equalsIgnoreCase(templateType)) {
                templates.add(new RepositoryTemplate());
            }
            else if (TemplateType.CONTROLLER.getType().equalsIgnoreCase(templateType)) {
                templates.add(new CriteriaTemplate());
                templates.add(new NewDataTemplate());
                templates.add(new UpdateTemplate());
                templates.add(new RecordTemplate());
                templates.add(new ControllerTemplate());
            }
            else {
                templates.add(new DoTemplate());
            }
        }
        return templates;
    }

    @Override
    public Template getTemplate(String templateType) {
        if (TemplateType.JPA.getType().equals(templateType)) {
            return new JpaTemplate();
        }
        else if (TemplateType.DTO.getType().equals(templateType)) {
            return new DtoTemplate();
        }
        else {
            return new DoTemplate();
        }
    }
}
