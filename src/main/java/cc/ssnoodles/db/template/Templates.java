package cc.ssnoodles.db.template;

import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.template.data.ClassTemplate;
import cc.ssnoodles.db.template.data.HeadNoteTemplate;
import cc.ssnoodles.db.template.data.ImportTemplate;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/8/25 00:03
 */
public class Templates {

    public StringBuilder common(Table table) {
        StringBuilder sb = new StringBuilder();
        sb.append(new ImportTemplate().tableDataToString())
                .append(new HeadNoteTemplate().tableDataToString(table))
                .append(new ClassTemplate().tableDataToString(table));
        return sb;
    }
}
