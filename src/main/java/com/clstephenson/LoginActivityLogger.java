package com.clstephenson;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles logging login attempts to a log file.  Both successful, and failed attempts are logged.  There will only be
 * one instance of the logger created.  The logger is accessed through the logNewActivity static method.
 */
public class LoginActivityLogger {
    private static LoginActivityLogger loginActivityLoggerInstance = null;
    private static Logger logger = null;

    private LoginActivityLogger() {
        logger = Logger.getLogger("com.clstephenson.activitylogger");
        String filePath = getLogFileNameFromAppConfiguration();
        try {
            FileHandler fileHandler = new FileHandler(filePath, true);
            fileHandler.setFormatter(new LogFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.INFO);
        } catch (IOException e) {
            String message = Localization.getString("error.io.filenotfound")
                    + ": " + Paths.get(filePath).toAbsolutePath().normalize() + ".";
            throw new RuntimeException(message, e);
        }
    }

    private String getLogFileNameFromAppConfiguration() {
        return AppConfiguration.getConfigurationProperty("logging.activity.filename");
    }

    /**
     * Add a new login event entry to the log file.  If there is not a current instance of the LoginActivityLogger,
     * then one is created.
     * @param userName The username entered, if present.
     * @param isSuccessful Was the attempt successful or not
     */
    public static void logNewActivity(String userName, boolean isSuccessful) {
        if (loginActivityLoggerInstance == null) {
            loginActivityLoggerInstance = new LoginActivityLogger();
        }
        StringBuilder message = new StringBuilder();
        message.append("[");
        message.append(isSuccessful ? Localization.getString("log.success") : Localization.getString("log.denied"));
        message.append("]");
        message.append("\t");
        message.append(Localization.getString("log.loginattempted") + " ");
        message.append(userName.isEmpty() ? "[not entered]" : userName);
        logger.info(message.toString());
    }
}
