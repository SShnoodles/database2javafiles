package cc.ssnoodles.db.template.imports;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2019-03-16 13:05
 */
public class ImportControllerTemplateImpl implements ImportTemplate {
    @Override
    public String getTemplate() {
        StringBuilder sb = new StringBuilder();
        sb.append("import org.springframework.beans.factory.annotation.Autowired;").append(LINE)
                .append("import org.springframework.web.bind.annotation.*;").append(LINE)
                .append("import org.springframework.data.domain.Pageable;").append(LINE)
                .append("import java.util.Optional;").append(LINE);
        return sb.toString();
    }
}
