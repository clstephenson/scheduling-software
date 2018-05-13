package com.clstephenson;

import com.clstephenson.dataaccess.UserRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LoginSession {

    private User loggedInUser = null;

    public LoginSession(String userName, String password) throws SQLException, IOException {
        UserRepository userRepository = new UserRepository();
        loggedInUser = userRepository.findSingle(u ->
            u.getUserName().equalsIgnoreCase(userName) &&
                    u.getPassword().equals(password) &&
                    u.isActive()
        );
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

    private void logAttempt(String userName, boolean isSuccessful) throws IOException {
        LoginActivityLogger.logNewActivity(userName, isSuccessful);
    }
}
