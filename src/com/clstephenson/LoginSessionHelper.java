package com.clstephenson;

import com.clstephenson.datamodels.User;

public class LoginSessionHelper {

    public static String getUsername() {
        return getCurrentUser().getUserName();
    }

    public static User getCurrentUser() {
        return getSession().getLoggedInUser();
    }

    public static LoginSession getSession() {
        return Main.session;
    }

    public static void logout() {
        getSession().logout();
    }
}
