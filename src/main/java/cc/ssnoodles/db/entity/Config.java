package cc.ssnoodles.db.entity;

import lombok.*;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2019-01-30 12:27
 */
@Data
@Builder
public class Config {
    private String db;

    private String url;

    private String username;

    private String password;

    private String outpath;

    private String[] templates;

    private String author;

    private boolean overwritefiles;

    private String singleTableName;

    private String singleTableRename;
}
