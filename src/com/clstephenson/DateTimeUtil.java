package com.clstephenson;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class DateTimeUtil {

    private static String sqlDateTimeFormatString = "YYYY-MM-d HH:mm:s";

    /**
     * Used for saving a date/time to the DB.  Adds default timezone information before converting to UTC,
     * and ultimately returns a timestamp for data persistence.
     * @param localDateTime
     * @return timestamp that can be persisted to a database
     */
    public static Timestamp getTimestampFromLocalDateTime(LocalDateTime localDateTime) {
        ZonedDateTime zdt = localDateTime.atZone(ZoneId.systemDefault());
        ZonedDateTime utc = zdt.withZoneSameInstant(ZoneId.of("UTC"));
        localDateTime = utc.toLocalDateTime();
        return Timestamp.valueOf(localDateTime);
    }

    /**
     * Used for getting a date from the DB.  Converts a timestamp (e.g. from DB) to a ZonedDateTime using the
     * system default timezone.
     * @param timestamp
     * @return date and time with default timezone information added
     */
    public static LocalDateTime getZonedDateTimeFromTimestamp(Timestamp timestamp) {
        ZonedDateTime utc = timestamp.toLocalDateTime().atZone(ZoneId.of("UTC"));
        return utc.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static String getDateTimeForSQL() {
        return LocalDateTime.now().format(getDateTimeFormatter());
    }

    public static int getWeekOfYear(LocalDate date) {
        return date.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
    }

    private static DateTimeFormatter getDateTimeFormatter() {
        return DateTimeFormatter.ofPattern(sqlDateTimeFormatString);
    }

}
