package com.clstephenson;

import com.clstephenson.datamodels.User;

/**
 * Helper class to get information about the current session without needing the session object.
 */
public class LoginSessionHelper {

    /**
     * Get the username of the currently logged in user.
     *
     * @return User's username.
     */
    public static String getUsername() {
        return getCurrentUser().getUserName();
    }

    /**
     * Get the user object for the currently logged in user.
     * @return Logged in User object.
     */
    public static User getCurrentUser() {
        return getSession().getLoggedInUser();
    }

    /**
     * Get the current LoginSession object.
     * @return The current LoginSession.
     */
    public static LoginSession getSession() {
        return Main.session;
    }

    /**
     * Call the logout method of the current LoginSession.
     */
    public static void logout() {
        getSession().logout();
    }
}
