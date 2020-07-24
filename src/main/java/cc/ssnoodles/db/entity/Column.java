package cc.ssnoodles.db.entity;

import lombok.*;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/7/12 23:13
 */
@Data
public class Column {
    private String name;
    private String type;
    private String remarks;
    private boolean primaryKey;
    private int decimalDigits;
    private boolean nullable;
    private int size;

    public boolean isDecimalDigits() {
        return decimalDigits > 0;
    }
}
