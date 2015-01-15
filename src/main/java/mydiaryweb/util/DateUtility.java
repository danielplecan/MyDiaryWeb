package mydiaryweb.util;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Daniel
 */
public class DateUtility {
    public static Date getBeginningOfDay(Date targetDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(targetDate);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        return new Date(year, month, day);
    }
    
    public static Date getEndOfDay(Date targetDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(targetDate);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        return new Date(year, month, day, 23, 59, 59);
    }
}
