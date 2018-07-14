package cc.ssnoodles.db.constant;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/7/13 08:42
 */
public enum DbType {
    ORACLE("oracle", "oracle.jdbc.OracleDriver"),
    POSTGRESQL("postgresql", "org.postgresql.Driver"),
    MYSQL("mysql", "com.mysql.jdbc.Driver");

    private String type;

    private String driver;

    DbType(String type, String driver) {
        this.type = type;
        this.driver = driver;
    }

    public String getType() {
        return type;
    }

    public String getDriver() {
        return driver;
    }

    public static String get(String type) {
        DbType[] values = values();
        for (DbType value : values) {
            if (value.getType().equals(type)) {
                return value.getDriver();
            }
        }
        throw new RuntimeException("No database was found to be supported");
    }
}
