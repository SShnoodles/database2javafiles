package cc.ssnoodles.db.entity;

import lombok.*;

import java.util.List;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/7/12 23:13
 */
@Data
public class Table {
    private String name;
    private String remarks;
    private List<Column> columns;
    private List<String> primaryKeys;
}
