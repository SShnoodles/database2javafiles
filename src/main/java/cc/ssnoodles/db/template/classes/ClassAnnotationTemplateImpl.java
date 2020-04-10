package cc.ssnoodles.db.template.classes;

import cc.ssnoodles.db.entity.Table;
import cc.ssnoodles.db.util.StringUtil;

import java.util.List;

/**
 * 类注解
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/8/27 09:01
 */
public class ClassAnnotationTemplateImpl implements ClassTemplate {
    @Override
    public String getTemplate(Table table, String className) {
        StringBuilder sb = new StringBuilder();
        sb.append("@Entity").append(LINE)
                .append("@Table(name = \"").append(table.getName()).append("\")").append(LINE);

        List<String> primaryKeys = table.getPrimaryKeys();
        String primaryKeysString = null;
        if (primaryKeys != null && primaryKeys.size() > 0) {
            if (1 == primaryKeys.size()) {
                primaryKeysString = "\"" + StringUtil.underlineToHump(primaryKeys.get(0)) + "\"";
            }else {
                primaryKeysString = "{ ";
                for (String primaryKey : primaryKeys) {
                    primaryKeysString = primaryKeysString + "\"" + StringUtil.underlineToHump(primaryKey) + "\", ";
                }
                primaryKeysString = primaryKeysString.substring(0, primaryKeysString.length() - 2);
                primaryKeysString = primaryKeysString + " }";
            }
        }
        sb.append("@Data").append(LINE);
        if (primaryKeysString != null) {
            sb.append("@EqualsAndHashCode(of = ").append(primaryKeysString).append(")").append(LINE);
        }
        sb.append("@NoArgsConstructor(access = AccessLevel.PROTECTED)").append(LINE);
        sb.append("@RequiredArgsConstructor").append(LINE);
        return sb.toString();
    }
}
