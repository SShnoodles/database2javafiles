#### Predicates from ControllerTemplate
```java
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.SimpleExpression;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 *  @author zhihai
 */
public class Predicates {

    public static <T extends EntityPathBase<?>> Predicate build(Class<T> qClass, BiConsumer<T, Builder2> f) {

        T q = null;
        for (Field field : qClass.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers()) && field.getType().equals(qClass)) {
                try {
                    q = qClass.cast(field.get(null));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

            }
        }
        var builder = new Builder2();
        f.accept(q, builder);

        Predicate predicate = ExpressionUtils.allOf(builder.predicates);
        if (predicate == null) {
            predicate = new BooleanBuilder();
        }
        return predicate;
    }

    private Predicates() {}

    public static class Builder2 {

        final List<Predicate> predicates = new ArrayList<>();

        public Builder2 add(Predicate predicate) {
            predicates.add(predicate);
            return this;
        }

        public <T> Builder2 add(SimpleExpression<T> expression, T value) {
            if (StringUtils.isEmpty(value)) return this;
            predicates.add(expression.eq(value));
            return this;
        }

        public <T> Builder2 add(SimpleExpression<T> expression, Optional<T> value) {
            value.ifPresent(v -> predicates.add(expression.eq(v)));
            return this;
        }

        public <T> Builder2 add(Function<T, Predicate> pf, Optional<T> value) {
            value.ifPresent(v -> predicates.add(pf.apply(v)));
            return this;
        }

        public <T> Builder2 add(Function<T, Predicate> pf, T value) {
            if (StringUtils.isEmpty(value)) return this;
            predicates.add(pf.apply(value));
            return this;
        }

        public Builder2 add(Function<OffsetDateTime, Predicate> pf, Optional<LocalDate> date, LocalTime time) {
            if (date.isEmpty()) return this;
            return add(pf, date.get(), time, getDefaultZoneOffset());
        }

        public Builder2 add(Function<OffsetDateTime, Predicate> pf, LocalDate date, LocalTime time) {
            if (date == null) return this;
            return add(pf, date, time, getDefaultZoneOffset());
        }

        public Builder2 add(Function<OffsetDateTime, Predicate> pf, LocalDate date, LocalTime time, ZoneOffset zoneOffset) {
            if (date == null) return this;
            var t = OffsetDateTime.of(date, time, zoneOffset);
            predicates.add(pf.apply(t));
            return this;
        }

        public <T1, T2> Builder2 add(BiFunction<T1, T2, Predicate> toPredicate,
                                     T1 value1,
                                     T2 value2) {
            predicates.add(toPredicate.apply(value1, value2));
            return this;
        }

        public <T1, T2> Builder2 add(BiFunction<T1, T2, Predicate> toPredicate,
                                     Optional<T1> value1,
                                     Optional<T2> value2) {
            if (value1.isEmpty() || value2.isEmpty()) return this;
            predicates.add(toPredicate.apply(value1.get(), value2.get()));
            return this;
        }

        private ZoneOffset getDefaultZoneOffset() {
            TimeZone zone = TimeZone.getDefault();
            Instant instant = Instant.now();
            return zone.toZoneId().getRules().getOffset(instant);
        }

    }

}

```