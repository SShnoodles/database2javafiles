package cc.ssnoodles.db.template.classes;

import cc.ssnoodles.db.entity.Table;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2019-03-14 08:53
 */
public class ClassDataMapperTemplateImpl implements ClassTemplate {
    @Override
    public String getTemplate(Table table, String newClassName) {
        StringBuilder sb = new StringBuilder();
        sb.append("@Mapper(componentModel = \"spring\")").append(LINE);
        sb.append("public interface DataMapper ").append(BEGIN).append(LINE);
        return sb.toString();
    }
}
