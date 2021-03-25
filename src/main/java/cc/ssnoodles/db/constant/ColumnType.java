package cc.ssnoodles.db.constant;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/7/12 23:00
 */
public enum ColumnType {
    UUID("UUID", "UUID"),

    LONGBLOB("LONGBLOB", "String"),
    LONGTEXT("LONGTEXT", "String"),
    CHAR("CHAR", "String"),
    VARCHAR("VARCHAR", "String"),
    VARCHAR2("VARCHAR2","String"),
    NCHAR("NCHAR", "String"),
    NVARCHAR2("NVARCHAR2", "String"),
    CLOB("CLOB", "String"),
    NCLOB("NCLOB", "String"),
    BLOB("BLOB", "String"),
    BPCHAR("BPCHAR", "String"),
    CHARACTER("CHARACTER", "String"),
    TEXT("TEXT", "String"),
    LONGVARCHAR("LONGVARCHAR", "String"),

    INTEGER("INTEGER", "Integer"),
    INT2("INT2", "Integer"),
    INT4("INT4", "Integer"),
    INT("INT", "Integer"),
    TINYINT("TINYINT", "Integer"),
    SMALLINT("SMALLINT", "Integer"),
    BIGINT("BIGINT", "Long"),
    INT8("INT8", "Long"),

    NUMERIC("NUMERIC", "BigDecimal"),
    NUMBER("NUMBER", "BigDecimal"),
    DECIMAL("DECIMAL", "BigDecimal"),
    DOUBLE("DOUBLE", "BigDecimal"),
    FLOAT("FLOAT", "BigDecimal"),
    REAL("REAL", "BigDecimal"),

    BINARY("BINARY", "Byte[]"),
    VARBINARY("VARBINARY", "Byte[]"),
    LONGVARBINARY("LONGVARBINARY", "Byte[]"),

    BOOL("BOOL", "Boolean"),
    BOOLEAN("BOOLEAN", "Boolean"),
    BIT("BIT", "Boolean"),

    DATE("DATE", "OffsetDateTime"),
    DATETIME("DATETIME", "OffsetDateTime"),
    TIMESTAMP("TIMESTAMP", "OffsetDateTime"),
    TIMESTAMPZ("TIMESTAMP WITH TIME ZONE", "OffsetDateTime"),
    TIMESTAMPTZ("TIMESTAMPTZ", "OffsetDateTime"),
    TIMESTAMPLTZ("TIMESTAMPLTZ", "OffsetDateTime"),
    TIMESTAMP6("TIMESTAMP(6)", "OffsetDateTime");

    private String columnType;

    private String javaType;

    ColumnType(String columnType, String javaType) {
        this.columnType = columnType;
        this.javaType = javaType;
    }

    public String getColumnType() {
        return columnType;
    }

    public String getJavaType() {
        return javaType;
    }

    public static String get(String type) {
        ColumnType[] values = values();
        for (ColumnType value : values) {
            if (value.columnType.equalsIgnoreCase(type)) {
                return value.getJavaType();
            }
        }
        return "Object";
    }

    public static boolean isString(String type) {
        return "String".equals(ColumnType.get(type));
    }

    public static boolean isBigString(String type) {
        return LONGTEXT.columnType.equalsIgnoreCase(type)
                || LONGBLOB.columnType.equalsIgnoreCase(type)
                || CLOB.columnType.equalsIgnoreCase(type)
                || NCLOB.columnType.equalsIgnoreCase(type)
                || BLOB.columnType.equalsIgnoreCase(type)
                || TEXT.columnType.equalsIgnoreCase(type);
    }

    public static boolean isNumber(String type) {
        return "Short".equals(ColumnType.get(type))
                || "Integer".equals(ColumnType.get(type))
                || "Long".equals(ColumnType.get(type))
                || "Double".equals(ColumnType.get(type))
                || "BigDecimal".equals(ColumnType.get(type));
    }

    public static boolean isTime(String type) {
        return "OffsetDateTime".equals(ColumnType.get(type))
                || "LocalDate".equals(ColumnType.get(type));
    }
}
