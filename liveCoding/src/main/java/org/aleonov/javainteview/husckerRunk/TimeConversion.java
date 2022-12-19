package org.aleonov.javainteview.husckerRunk;

import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TimeConversion {
    public static void main(String[] args) throws ParseException {
        String time1 = "07:05:45 PM";
        timeConversion(time1);
    }

    public static String timeConversion(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss a", Locale.ENGLISH);
        var time = LocalTime.parse(s, formatter);

        System.out.println(time);
        return "";
    }
}
