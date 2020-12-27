package cc.ssnoodles.db.template;

import cc.ssnoodles.db.entity.Column;
import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.template.classes.*;
import cc.ssnoodles.db.template.fields.*;
import cc.ssnoodles.db.template.imports.ImportTemplateImpl;

import java.util.List;

/**
 * jpa template
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/8/27 09:22
 */
public class JpaTemplate implements Template {
    // 生成模板样式
    //
    // /**
    //  * 数据字典数据明细
    //  */
    // @Data
    // @Entity
    // @Table(name = "BASIC_DIC_ITEMS")
    // @EqualsAndHashCode(of = "guid")
    // @NoArgsConstructor(access = AccessLevel.PROTECTED)
    // @RequiredArgsConstructor
    // public class BasicDicItems {
    //    /**
    //     * 主键
    //      */
    //     @Id
    //     @Setter(AccessLevel.PROTECTED)
    //     @NonNull
    //     @Column(name = "GUID")
    //     private String guid;
    //     /**
    //      * 编辑人主键
    //      */
    //     @Column(name = "EDITOR_GUID")
    //     private String editorGuid;
    // }
    @Override
    public String tableDataToString(Table table, String newClassName) {
        StringBuilder sb = new StringBuilder();
        sb.append(new ImportTemplateImpl().getTemplate());
        sb.append(LINE);
        sb.append(new ClassNoteTemplateImpl().getTemplate(table, newClassName));
        sb.append(new ClassAnnotationTemplateImpl().getTemplate(table, newClassName));
        sb.append(new ClassTemplateImpl().getTemplate(table, newClassName));
        List<Column> columns = table.getColumns();
        for (Column column : columns) {
            sb.append(LINE);
            sb.append(new FieldNoteTemplateImpl().getTemplate(column));
            sb.append(new FieldAnnotationTemplateImpl().getTemplate(column));
            sb.append(new FieldTemplateImpl().getTemplate(column));
        }
        sb.append(END);
        return sb.toString();
    }
}
