package cc.ssnoodles.db.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2019-03-11 12:45
 */
public class TimeUtil {

    public static String getTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dtf.format(LocalDateTime.now());
    }
}
