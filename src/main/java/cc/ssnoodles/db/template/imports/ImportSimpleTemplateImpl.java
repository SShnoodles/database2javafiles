package cc.ssnoodles.db.template.imports;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2019-03-16 12:19
 */
public class ImportSimpleTemplateImpl implements ImportTemplate {
    @Override
    public String getTemplate() {
        StringBuilder sb = new StringBuilder();
        sb.append("import lombok.*;").append(LINE)
                .append("import java.math.BigDecimal;").append(LINE)
                .append("import java.time.*;").append(LINE)
                .append("import org.springframework.format.annotation.DateTimeFormat;").append(LINE)
                .append("import io.swagger.v3.oas.annotations.media.Schema;").append(LINE)
                .append("import javax.validation.constraints.NotBlank;").append(LINE)
                .append("import javax.validation.constraints.NotNull;").append(LINE)
                .append("import javax.validation.constraints.Size;").append(LINE);
        return sb.toString();
    }
}
