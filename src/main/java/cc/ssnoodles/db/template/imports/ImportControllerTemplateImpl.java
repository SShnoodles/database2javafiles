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
        sb.append("import org.springframework.web.bind.annotation.*;").append(LINE)
                .append("import org.springframework.transaction.annotation.Transactional;").append(LINE)
                .append("import org.springframework.data.domain.Pageable;").append(LINE)
                .append("import org.springframework.http.HttpStatus;").append(LINE)
                .append("import org.springframework.validation.annotation.Validated;").append(LINE)
                .append("import java.util.Optional;").append(LINE)
                .append("import java.util.List;").append(LINE)
                .append("import java.util.UUID;").append(LINE)
                .append("import io.swagger.v3.oas.annotations.Operation;").append(LINE)
                .append("import io.swagger.v3.oas.annotations.tags.Tag;").append(LINE);
        return sb.toString();
    }
}
