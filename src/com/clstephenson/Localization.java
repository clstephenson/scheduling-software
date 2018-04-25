package com.clstephenson;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Localization {
    private static ResourceBundle resourceBundle;
    private static Localization localizationInstance;

    private Localization() {
        resourceBundle = ResourceBundle.getBundle("Localization");
    }

    public static String getString(String key) {
        if(localizationInstance == null) {
            localizationInstance = new Localization();
        }
        return resourceBundle.getString(key);
    }
}
