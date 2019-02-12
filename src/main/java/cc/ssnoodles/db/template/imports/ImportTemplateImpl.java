package cc.ssnoodles.db.template.imports;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/8/27 09:12
 */
public class ImportTemplateImpl implements ImportTemplate {

    @Override
    public String getTemplate() {
        StringBuilder sb = new StringBuilder();
        sb.append("import lombok.*;").append(LINE)
                .append("import javax.persistence.*;").append(LINE)
                .append("import java.math.BigDecimal;").append(LINE)
                .append("import java.time.LocalDate;").append(LINE)
                .append("import java.time.OffsetDateTime;").append(LINE)
                .append("import java.util.Date;").append(LINE);
        return sb.toString();
    }
}
