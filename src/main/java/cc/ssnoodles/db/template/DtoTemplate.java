package cc.ssnoodles.db.template;

import cc.ssnoodles.db.entity.Column;
import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.template.classes.*;
import cc.ssnoodles.db.template.fields.*;
import cc.ssnoodles.db.template.imports.ImportSimpleTemplateImpl;
import java.util.List;

/**
 * dto 模板
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/8/27 09:22
 */
public class DtoTemplate implements Template{
    // 生成模板样式
    //
    // /**
    //  * 数据字典数据明细
    //  */
    // public class BasicDicItems {
    //    /**
    //     * 主键
    //     */
    //    public String guid;
    //    /**
    //     * 编辑人主键
    //     */
    //    public String editorGuid;
    // }
    @Override
    public String tableDataToString(Table table, String newClassName) {
        StringBuilder sb = new StringBuilder();
        sb.append(new ImportSimpleTemplateImpl().getTemplate());
        sb.append(LINE);
        sb.append(new ClassNoteTemplateImpl().getTemplate(table, newClassName));
        sb.append(new ClassDtoTemplateImpl().getTemplate(table, newClassName));
        List<Column> columns = table.getColumns();
        for (Column column : columns) {
            sb.append(LINE);
            sb.append(new FieldNoteTemplateImpl().getTemplate(column));
            sb.append(new FieldPublicTemplateImpl().getTemplate(column));
        }
        sb.append(END);
        return sb.toString();
    }

    @Override
    public String endsWith() {
        return "Dto";
    }
}
