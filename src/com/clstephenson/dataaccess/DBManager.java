package com.clstephenson;

import com.clstephenson.AppConfiguration;
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
    private static List<Connection> openConnections;
    private Connection connection;

    static {
        openConnections = new ArrayList<>();
    }

    /**
     * @return Current database connection managed by this instance.  If the connection was previously closed,
     * then it opens a new connection.
     */
    public Connection getConnection() throws IOException, SQLException {
        if(!isConnected()) {
            openNewConnection();
        }
        return this.connection;
    }

    /**
     * Closes the open db connection if there is one.
     */
    public void closeConnection() {
        try {
            if(isConnected()) {
                int connectionIndex = openConnections.indexOf(this.connection);
                this.connection.close();
                openConnections.remove(connectionIndex);
            }
        } catch (SQLException ex) {  } //ignore this
    }

    private void openNewConnection() throws IOException, SQLException {
        try {
            if(!isConnected()) {
                this.connection = DriverManager.getConnection(
                        this.getDatabaseConnectionString(),
                        this.getDatabaseUser(),
                        this.getDatabasePassword()
                );
                openConnections.add(this.connection);
            }
        } catch (SQLException e) {
            String message = Localization.getString("error.db.connection") +
                    ": " + this.getDatabaseConnectionString();
            throw new SQLException(message, e);
        }
    }

    private boolean isConnected() {
        return this.connection != null;
    }

    private String getDatabasePassword() throws IOException {
        return AppConfiguration.getConfigurationProperty("db.password");
    }

    private String getDatabaseUser() throws IOException {
        return AppConfiguration.getConfigurationProperty("db.user");
    }

    private String getDatabaseConnectionString() throws IOException {
        return String.format(
                "JDBC:mysql://%s:%s/%s",
                AppConfiguration.getConfigurationProperty("db.server"),
                AppConfiguration.getConfigurationProperty("db.port"),
                AppConfiguration.getConfigurationProperty("db.schema")
        );
    }
}
