package cc.ssnoodles.db.constant;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2019-01-30 13:52
 */
public enum TemplateType {
    JPA("jpa"),
    DTO("dto"),
    COMMON("common");

    private String type;

    TemplateType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
