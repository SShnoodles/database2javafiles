package cc.ssnoodles.db.template;

import cc.ssnoodles.db.entity.Column;
import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.template.classes.ClassNoteTemplateImpl;
import cc.ssnoodles.db.template.classes.ClassTemplateImpl;
import cc.ssnoodles.db.template.fields.FieldNoteTemplateImpl;
import cc.ssnoodles.db.template.fields.FieldTemplateImpl;
import cc.ssnoodles.db.template.imports.ImportSimpleTemplateImpl;

import java.util.List;

/**
 * 通用模板
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/8/27 09:22
 */
public class CommonTemplate implements Template{
    // 生成模板样式
    //
    // /**
    //  * 数据字典数据明细
    //  */
    // public class BasicDicItems {
    //    /**
    //     * 主键
    //     */
    //    private String guid;
    // }
    @Override
    public String tableDataToString(Table table) {
        StringBuilder sb = new StringBuilder();
        sb.append(new ImportSimpleTemplateImpl().getTemplate());
        sb.append(LINE);
        sb.append(new ClassNoteTemplateImpl().getTemplate(table));
        sb.append(new ClassTemplateImpl().getTemplate(table));
        List<Column> columns = table.getColumns();
        for (Column column : columns) {
            sb.append(LINE);
            sb.append(new FieldNoteTemplateImpl().getTemplate(column));
            sb.append(new FieldTemplateImpl().getTemplate(column));
        }
        sb.append(END);
        return sb.toString();
    }

    @Override
    public String endsWith() {
        return "Do";
    }
}
