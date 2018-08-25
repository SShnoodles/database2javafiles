package cc.ssnoodles.db.template.fields;

import cc.ssnoodles.db.constant.ColumnType;
import cc.ssnoodles.db.entity.Column;
import cc.ssnoodles.db.util.StringUtil;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/8/25 23:20
 */
public class FieldPublicTemplateImpl implements FieldTemplate{
    @Override
    public String getTemplate(Column column) {
        StringBuilder sb = new StringBuilder();
        sb.append("    public ");
        if (!column.isDecimalDigits() && "NUMBER".equals(column.getType())) {
            sb.append("Integer");
        }else {
            sb.append(ColumnType.get(column.getType().toUpperCase()));
        }
        sb.append(" ").append(StringUtil.underlineToHump(column.getName())).append(";").append(LINE);
        return sb.toString();
    }
}
