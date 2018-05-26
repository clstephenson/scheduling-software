package com.clstephenson;

public class LoginSessionHelper {

    public static String getUsername() {
        return getCurrentUser().getUserName();
    }

    public static User getCurrentUser() {
        return Main.session.getLoggedInUser();
    }

    public static void logout() {
        Main.session.logout();
    }
}
