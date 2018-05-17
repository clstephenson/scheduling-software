package com.clstephenson.dataaccess;

import com.clstephenson.AppConfiguration;
import com.clstephenson.Localization;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The DBManager class is used to manage connections to a database.  After creating an instance of the
 * class, call getConnection to open and return an actual connection to the database.  The connection itself, including
 * the connection parameters to access the remote database, is managed by this class using
 * com.clstephenson.AppConfiguration.  The open connection should be closed using the closeConnection method.
 */
public class DBManager {
    private static Connection connection;

    /**
     * @return Current database connection managed by this instance.  If the connection was previously closed,
     * then it opens a new connection.
     */
    public static Connection getConnection() throws SQLException {
        if(!isConnected()) {
            openNewConnection();
        }
        return connection;
    }

    /**
     * Closes the open db connection if there is one.
     */
    public static void closeConnection() {
        try {
            if(isConnected()) {
                connection.close();
            }
        } catch (SQLException ex) {  } //ignore this
    }

    private static void openNewConnection() throws SQLException {
        try {
            if(!isConnected()) {
                connection = DriverManager.getConnection(
                        getDatabaseConnectionString(),
                        getDatabaseUser(),
                        getDatabasePassword()
                );
            }
        } catch (SQLException e) {
            String message = Localization.getString("error.db.connection") +
                    ": " + getDatabaseConnectionString();
            throw new SQLException(message, e);
        }
    }

    private static boolean isConnected() {
        return connection != null;
    }

    private static String getDatabasePassword() {
        return AppConfiguration.getConfigurationProperty("db.password");
    }

    private static String getDatabaseUser() {
        return AppConfiguration.getConfigurationProperty("db.user");
    }

    private static String getDatabaseConnectionString() {
        return String.format(
                "JDBC:mysql://%s:%s/%s",
                AppConfiguration.getConfigurationProperty("db.server"),
                AppConfiguration.getConfigurationProperty("db.port"),
                AppConfiguration.getConfigurationProperty("db.schema")
        );
    }
}
