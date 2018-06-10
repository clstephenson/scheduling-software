package com.clstephenson;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class LoginActivityLogger {
    private static LoginActivityLogger loginActivityLoggerInstance = null;
    private static Logger logger = null;

    private LoginActivityLogger() throws IOException {
        logger = Logger.getLogger("com.clstephenson.activitylogger");
        String filePath = getLogFileNameFromAppConfiguration();
        try {
            FileHandler fileHandler = new FileHandler(filePath, true);
            fileHandler.setFormatter(new LogFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.INFO);
        } catch (IOException ex) {
            String message = Localization.getString("error.io.filenotfound")
                    + ": " + Paths.get(filePath).toAbsolutePath().normalize() + ".";
            throw new RuntimeException(message);
        }
    }

    /**
     * @param message
     */
    public static void logNewActivity(String userName, boolean isSuccessful) throws IOException {
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
        //message.append(": ");
        logger.info(message.toString());
    }

    private String getLogFileNameFromAppConfiguration() throws IOException {
        return AppConfiguration.getConfigurationProperty("logging.activity.filename");
    }
}
