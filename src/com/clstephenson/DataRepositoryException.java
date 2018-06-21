package com.clstephenson;

import java.sql.SQLException;

public class DataRepositoryException extends SQLException {

    private StringBuilder message = new StringBuilder();

    public DataRepositoryException(SQLException e, String msg) {
        message.append(msg)
                .append(System.lineSeparator())
                .append(System.lineSeparator())
                .append(e.getMessage())
                .append(System.lineSeparator())
                .append(String.format("SQL State: %s", e.getSQLState()))
                .append(System.lineSeparator())
                .append(String.format("DB Error Code: %d", e.getErrorCode()));
    }

    @Override
    public String getMessage() {
        return message.toString();
    }
}
