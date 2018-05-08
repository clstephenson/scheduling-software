package com.clstephenson;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    private static String sqlDateTimeFormatString = "YYYY-MM-d HH:mm:s";

    public static String getDateTimeForSQL() {
        return ZonedDateTime.now().format(getFormatter());
    }

    public static String getDateTimeForSQL(ZonedDateTime zonedDateTime) {
        return zonedDateTime.format(getFormatter());
    }

    public static ZonedDateTime getZonedDateTimeFromSQLTimestamp(Timestamp timestamp) {
        return ZonedDateTime.ofInstant(timestamp.toInstant(), ZoneId.systemDefault());
    }

    private static DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern(sqlDateTimeFormatString);
    }

}
