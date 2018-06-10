package com.clstephenson;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LogFormatter extends Formatter {

    @Override
    public String format(LogRecord record) {
        ZonedDateTime zdt = ZonedDateTime.ofInstant(Instant.ofEpochMilli(record.getMillis()), ZoneId.systemDefault());
        StringBuilder sb = new StringBuilder();
        sb.append(zdt.format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss z")));
        sb.append("\t");
        sb.append(record.getMessage());
        sb.append(System.lineSeparator());
        return sb.toString();
    }
}
