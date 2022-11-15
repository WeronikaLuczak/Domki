package pl.wluczak.projektdomki.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static Date convertDate(String text) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return simpleDateFormat.parse(text);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String convertDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    public static int countDifferenceDaysBetween(Date from, Date to) {
        long fromSec = from.getTime() / 1000L;
        long toSec = to.getTime() / 1000L;

        long diff = toSec - fromSec;

        long diffDays = diff / (24 * 60 * 60);
        return (int) diffDays;
    }

    public static Date max(Date first, Date second) {
        if (first.before(second)) {
            return second;
        } else {
            return first;
        }
    }

    public static Date min(Date first, Date second) {
        if (first.before(second)) {
            return first;
        } else {
            return second;
        }
    }

}
