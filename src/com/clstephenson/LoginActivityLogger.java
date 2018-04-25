package com.clstephenson;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.*;

/**
 *
 */
public class LoginActivityLogger {
    private static LoginActivityLogger loginActivityLoggerInstance = null;
    private static Logger logger = null;

    /**
     * @param message
     */
    public static void logNewActivity(String userName, boolean isSuccessful) throws IOException {
        if(loginActivityLoggerInstance == null) {
            loginActivityLoggerInstance = new LoginActivityLogger();
        }
        StringBuilder message = new StringBuilder();
        message.append(Localization.getString("log.loginattempted") + " ");
        message.append(userName);
        message.append(": ");
        message.append(isSuccessful ? Localization.getString("log.success") : Localization.getString("log.denied"));
        logger.info(message.toString());
    }

    private LoginActivityLogger() throws IOException {
        logger = Logger.getLogger("com.clstephenson.activitylogger");
        String filePath = getLogFileNameFromAppConfiguration();
        try {
            FileHandler fileHandler = new FileHandler(filePath, true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.INFO);
        } catch (IOException ex) {
            String message = Localization.getString("error.io.filenotfound")
                    + ": " + Paths.get(filePath).toAbsolutePath().normalize() + ".";
            throw new RuntimeException(message);
        }
    }

    private String getLogFileNameFromAppConfiguration() throws IOException {
        return AppConfiguration.getConfigurationProperty("logging.activity.filename");
    }
}
