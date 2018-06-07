package com.clstephenson;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.Locale;

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

    public static int getWeekOfYear(LocalDate date) {
        return date.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
    }

    private static DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern(sqlDateTimeFormatString);
    }

}
