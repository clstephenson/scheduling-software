package com.clstephenson;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    private static String sqlDateTimeFormatString = "YYYY-MM-d HH:mm:s";

    public static String getDateTimeForSQL() {
        return LocalDateTime.now().format(getFormatter());
    }

    public static String getDateTimeForSQL(LocalDateTime localDateTime) {
        return localDateTime.format(getFormatter());
    }

    public static LocalDateTime getZonedDateTimeFromSQLTimestamp(Timestamp timestamp) {
        return LocalDateTime.ofInstant(timestamp.toInstant(), ZoneId.systemDefault());
    }

    private static DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern(sqlDateTimeFormatString);
    }

}
