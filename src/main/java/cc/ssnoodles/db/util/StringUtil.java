package cc.ssnoodles.db.util;

/**
 * @author ssnoodles
 * @version 1.0
 * Create at 2018/7/13 08:49
 */
public class StringUtil {

    public static String underlineToHump(String str){
        StringBuilder result = new StringBuilder();
        String a[] = str.split("_");
        for(String s : a){
            if(result.length() == 0){
                result.append(s.toLowerCase());
            }else{
                result.append(s.substring(0, 1).toUpperCase());
                result.append(s.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

    public static String underlineToHumpTopUpperCase(String str) {
        String s = underlineToHump(str);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
