package com.clstephenson;

import com.clstephenson.dataaccess.UserRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LoginSession {

    private User loggedInUser = null;

    public LoginSession(String userName, String password) throws SQLException, IOException {
        UserRepository userRepository = new UserRepository();
        List<User> users = userRepository.find(u ->
            u.getUserName().equalsIgnoreCase(userName) &&
                    u.getPassword().equals(password) &&
                    u.isActive()
        );
        if(users.size() > 0) {
            loggedInUser = users.get(0);
        } else {
            loggedInUser = null;
        }
        logAttempt(userName, isLoggedIn());
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
