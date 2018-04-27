package com.clstephenson;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    private static String sqlDateTimeFormatString = "YYYY-MM-d HH:mm:s";

    public static String getCurrentDateTimeForSQL() {
        return ZonedDateTime.now().format(DateTimeFormatter.ofPattern(sqlDateTimeFormatString));
    }

}
