package mydiaryweb.module.mdqa.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import mydiaryweb.module.mdqa.util.DateUtil;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public class ActionDate {
    public static final String DT_FORMAT = "dd/MM/yyyy HH:mm:ss";
    String iStringDate = null;
    Date iDate;
    DateTime jodaUTC;

    public ActionDate(String date) {
        initFromString(date);
    }

    public ActionDate(Date date) {
        initFromJavaDate(date);
    }
    protected void initFromString(String date){
        this.iStringDate = date;
        DateTimeFormatter formatter = DateTimeFormat.forPattern(DT_FORMAT);
        DateTime dt = formatter.parseDateTime(date);
        jodaUTC = getUTCDate(dt);
    }

    protected void initFromJavaDate(Date date){
        this.iDate = date;
        DateTime dateTime = new DateTime(date);
        jodaUTC = getUTCDate(dateTime);
    }

    protected  DateTime getUTCDate(DateTime dt){
        return DateUtil.getUTCDate(dt);
    }

    public boolean checkEqual(DateTime another){
        DateTime utcAnother = getUTCDate(another);
        return jodaUTC.isEqual(utcAnother);
    }

    public String toString(){
        if (iStringDate != null){
            return iStringDate;
        }
        DateFormat df = new SimpleDateFormat(DT_FORMAT);
        return df.format(df);
    }

    public String stringify(){
        return toString();
    }

    public int compareTo(DateTime another){
        DateTime utcAnother = getUTCDate(another);

        /*System.out.println();
        System.out.println(another);
        System.out.println(utcAnother);
        System.out.println(jodaUTC);
        System.out.println();*/

        return jodaUTC.compareTo(another);
    }

    /*
    IMPORTANT will be used to determine date recognition relative date
    USE UTC for now
     */
    public DateTime getUTCDate(){
        return jodaUTC;
    }




}
