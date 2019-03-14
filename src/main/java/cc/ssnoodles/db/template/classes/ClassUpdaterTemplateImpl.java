package cc.ssnoodles.db.template.classes;

import cc.ssnoodles.db.entity.Table;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2019-03-14 08:56
 */
public class ClassUpdaterTemplateImpl implements ClassTemplate {
    @Override
    public String getTemplate(Table table) {
        StringBuilder sb = new StringBuilder();
        sb.append("@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = \"spring\")").append(LINE);
        sb.append("public interface Updater ").append(BEGIN).append(LINE);
        return sb.toString();
    }
}
