package cc.ssnoodles.db.template.functions;

import cc.ssnoodles.db.entity.Table;

/**
 * 方法
 * @author ssnoodles
 * @version 1.0
 * Create at 2019-03-13 09:37
 */
public interface FunctionTemplate {

    String LINE = System.getProperty("line.separator");

    String BEGIN = "{";

    String END = "}";

    String getTemplate(Table table, String newClassName);

    String SPACE = "    ";

    String CONTROLLER = "Controller";

    String REPOSITORY = "Repository";

    String CRITERIA = "Criteria";

    String DATA = "Data";

    String NEW = "New";

    String UPDATE = "Update";

    String INFO = "Info";

    String REF = "Ref";
}
