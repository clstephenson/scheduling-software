package com.clstephenson;

import com.clstephenson.dataaccess.UserRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LoginSession {

    private User loggedInUser = null;

    public LoginSession(String userName, String password) {
        try {
            UserRepository userRepository = new UserRepository();
            loggedInUser = userRepository.findSingle(u ->
                    u.getUserName().equalsIgnoreCase(userName) &&
                            u.getPassword().equals(password) &&
                            u.isActive()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e); //todo fix exceptions
        }
        logAttempt(userName, isLoggedIn());
        if(loggedInUser == null) {
            throw new RuntimeException("Invalid login credentials");
            //todo add custom exception
        }
    }

    public void logout() {
        loggedInUser = null;
    }

    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    private void logAttempt(String userName, boolean isSuccessful) {
        try {
            LoginActivityLogger.logNewActivity(userName, isSuccessful);
        } catch (IOException e) {
            throw new RuntimeException("Log entry could not be written to the file.", e);
            //todo fix this exception
        }
    }
}
