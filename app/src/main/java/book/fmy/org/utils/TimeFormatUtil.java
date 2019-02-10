package book.fmy.org.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFormatUtil {

    public static Date UTC2GSTDate(String timeString) {
        String date = timeString;
        date = date.replace("Z", " UTC");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        Date time = null;
        try {
            time = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return time;
    }

    public static String UTC2GST(String timeString) {
        String date = timeString;
        date = date.replace("Z", " UTC");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        Date time = null;
        try {
            time = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strTime = dateFormat.format(time);


        return strTime;
    }
}
