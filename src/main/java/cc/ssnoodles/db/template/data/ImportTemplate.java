package cc.ssnoodles.db.template.data;

import cc.ssnoodles.db.entity.Column;
import cc.ssnoodles.db.entity.Table;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/8/24 22:34
 */
public class ImportTemplate implements Template {

    @Override
    public String tableDataToString(Table table, Column column) {
        return null;
    }

    @Override
    public String tableDataToString(Table table) {
        return null;
    }

    @Override
    public String tableDataToString(Column column) {
        return null;
    }

    @Override
    public String tableDataToString() {
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
