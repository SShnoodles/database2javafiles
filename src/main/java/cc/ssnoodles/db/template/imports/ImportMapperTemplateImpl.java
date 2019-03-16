package cc.ssnoodles.db.template.imports;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2019-03-16 12:27
 */
public class ImportMapperTemplateImpl implements ImportTemplate {
    @Override
    public String getTemplate() {
        return "import org.mapstruct.*;" + LINE;
    }
}
