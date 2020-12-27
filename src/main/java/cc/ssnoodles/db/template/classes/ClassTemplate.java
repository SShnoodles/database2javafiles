package cc.ssnoodles.db.template.classes;

import cc.ssnoodles.db.entity.Table;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/8/27 09:01
 */
public interface ClassTemplate {

    String LINE = System.getProperty("line.separator");

    String BEGIN = "{";

    String getTemplate(Table table, String newClassName);

    String SPACE = "    ";

    String CONTROLLER = "Controller";

    String REPOSITORY = "Repository";

    String CRITERIA = "Criteria";

    String DATA = "Data";

    String UPDATE = "Update";

    String NEW = "New";

    String DTO = "Dto";

    String REF = "Ref";

    String INFO = "Info";

    String DO = "Do";

}
