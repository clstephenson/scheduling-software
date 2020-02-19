package com.clstephenson;

import java.util.ResourceBundle;

/**
 * Provide localization capability using a resource bundle.  Only one instance of this class will be created, and it
 * is accessed by calling the getString method.
 */
public class Localization {
    private static ResourceBundle resourceBundle;
    private static Localization localizationInstance;

    private Localization() {
        resourceBundle = ResourceBundle.getBundle("Localization");
    }

    /**
     * Get a string from the resource bundle.
     *
     * @param key The key associated with the desired string to be retrieved.
     * @return The string associated with the key given.
     */
    public static String getString(String key) {
        if(localizationInstance == null) {
            localizationInstance = new Localization();
        }
        return resourceBundle.getString(key);
    }

    /**
     * @return the current ResourceBundle instance.
     */
    public static ResourceBundle getResourceBundle() {
        return resourceBundle;
    }
}
