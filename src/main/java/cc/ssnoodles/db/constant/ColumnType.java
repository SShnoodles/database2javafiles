package cc.ssnoodles.db.constant;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/7/12 23:00
 */
public enum ColumnType {
    CHAR("String"),
    VARCHAR("String"),
    VARCHAR2("String"),
    NVARCHAR2("String"),
    DATE("OffsetDateTime"),
    DATETIME("OffsetDateTime"),
    NUMBER("BigDecimal"),

    INTEGER("Integer"),
    UUID("UUID"),
    BPCHAR("String"),
    CHARACTER("String"),
    TEXT("String"),
    INT2("Integer"),
    INT4("Integer"),
    INT8("Long"),
    TIMESTAMP("OffsetDateTime"),
    BOOL("Boolean"),
    BOOLEAN("Boolean"),
    NUMERIC("BigDecimal"),

    LONGTEXT("String"),
    DECIMAL("BigDecimal"),
    BIGINT("Long"),
    DOUBLE("BigDecimal"),
    INT("Integer"),
    BIT("Integer"),
    TINYINT("Integer");

    private String javaType;

    ColumnType(String javaType) {
        this.javaType = javaType;
    }

    public String getJavaType() {
        return javaType;
    }

    public static String get(String type) {
        ColumnType[] values = values();
        for (ColumnType value : values) {
            if (value.name().equalsIgnoreCase(type)) {
                return value.getJavaType();
            }
        }
        return "Object";
    }
}
