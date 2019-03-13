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

    String getTemplate(Table table);

    String SPACE = "    ";

    String CONTROLLER = "Controller";

    String REPOSITORY = "Repository";

    String CRITERIA = "Criteria";

    String FORM = "Form";

    String Dto = "Dto";

    String REF = "Ref";

}
