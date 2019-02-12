package cc.ssnoodles.db.template.imports;

/**
 * 引入仓库包
 * @author ssnoodles
 * @version 1.0
 * Create at 2019-02-12 09:08
 */
public class ImportRepositoryTemplateImpl implements ImportTemplate{
    @Override
    public String getTemplate() {
        StringBuilder sb = new StringBuilder();
        sb.append("import org.springframework.data.jpa.repository.JpaRepository;").append(LINE)
                .append("import org.springframework.data.querydsl.QuerydslPredicateExecutor;").append(LINE);
        return sb.toString();
    }
}
