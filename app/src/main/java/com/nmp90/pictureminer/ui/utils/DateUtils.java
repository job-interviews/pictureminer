package com.nmp90.pictureminer.ui.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by joro on 18.12.16.
 */

public class DateUtils {
    public static String getReadableDate(Date date) {
        String datePattern;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if(calendar.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR)) {
            datePattern = "MMMM dd, HH:mm";
        } else {
            datePattern = "MMMM dd HH:mm, yyyy";
        }
        return new SimpleDateFormat(datePattern, Locale.getDefault()).format(date);
    }
}
