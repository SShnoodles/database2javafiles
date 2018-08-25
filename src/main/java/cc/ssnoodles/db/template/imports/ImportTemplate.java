package cc.ssnoodles.db.template.imports;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/8/25 22:27
 */
public interface ImportTemplate {
    String LINE = System.getProperty("line.separator");

    String getTemplate();
}
