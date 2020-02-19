package com.clstephenson;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * The AppConfiguration class manages the configuration parameters for the application.  This class accesses the key/value
 * pairs from a configuration properties file.  This class is implemented as a Singleton, where there is only one instance
 * generated, and properties are requested via a static method (getConfigurationProperty).
 */
public class AppConfiguration {
    private final String CONFIG_PATH = "./resources/app_config.properties";
    private Properties properties = null;
    private static AppConfiguration appConfigurationInstance = null;

    /**
     * @param propertyName The name of a property key to retrieve
     * @return The value associated with the requested property key
     * @throws IOException
     */
    public static String getConfigurationProperty(String propertyName) {
        if(appConfigurationInstance == null) {
            appConfigurationInstance = new AppConfiguration();
        }
        String keyWithModePrefix = getAppModeFromConfig() + "." + propertyName;
        String propValueFromMode = appConfigurationInstance.properties.getProperty(keyWithModePrefix);
        if(propValueFromMode != null) propertyName = keyWithModePrefix;
        return appConfigurationInstance.properties.getProperty(propertyName,
                    Localization.getString("config.notconfigured"));
    }

    private AppConfiguration() {
        this.properties = new Properties();
        try(FileReader inputReader = new FileReader(CONFIG_PATH)) {
            this.properties.load(inputReader);
        } catch (IOException e) {
            String message = Localization.getString("error.io.filenotfound") +
                    ": " + Paths.get(CONFIG_PATH).toAbsolutePath().normalize();
            Dialog.showErrorMessage(message);
        }
    }

    private static String getAppModeFromConfig() {
        return appConfigurationInstance.properties.getProperty("app.mode");
    }
}
