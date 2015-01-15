package mydiaryweb.module.mdqa.util;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class DateUtil {

    public static DateTime getUTCDate(DateTime dt){
        return dt.withZone(DateTimeZone.forID("UTC"));
    }
}
