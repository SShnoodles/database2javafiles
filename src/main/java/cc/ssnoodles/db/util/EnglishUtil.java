package cc.ssnoodles.db.util;

public class EnglishUtil {

    public static String simplePlural(String name) {
        if (name.endsWith("y")) {
            name = name.substring(0, name.length() - 1) + "ies";
        } else if (name.endsWith("x") || name.endsWith("z") || name.endsWith("ch") || name.endsWith("sh")) {
            name = name + "es";
        } else if (name.endsWith("s")) {
            // keep
        } else {
            name = name + "s";
        }
        return name;
    }
}
