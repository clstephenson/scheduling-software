package com.clstephenson;

import com.clstephenson.datamodels.User;

import java.security.AccessControlException;

/**
 * LoginSession constructor takes a username and password as inputs.  If the combination is valid, then the associated
 * User is added to the session.
 */
public class LoginSession {

    private User loggedInUser;

    /**
     * Checks if username/password combination is valid, and if so, creates a new login session.
     *
     * @param userName
     * @param password
     * @throws AccessControlException if the username/password combination is not valid.
     */
    public LoginSession(String userName, String password) throws AccessControlException {
        loggedInUser = User.getActiveUser(userName, password);
        logAttempt(userName, isLoggedIn());
        if(loggedInUser == null) {
            throw new AccessControlException("Invalid login credentials");
        }
    }

    private void logAttempt(String userName, boolean isSuccessful) {
        LoginActivityLogger.logNewActivity(userName, isSuccessful);
    }

    /**
     * Checks if the current session has a valid user logged in.
     *
     * @return true if a user is currently logged in, otherwise returns false.
     */
    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    /**
     * Invalidates the current login session user.
     */
    public void logout() {
        loggedInUser = null;
    }

    /**
     * Get the current logged in user.
     *
     * @return the session's currently logged in user.
     */
    public User getLoggedInUser() {
        return loggedInUser;
    }
}
