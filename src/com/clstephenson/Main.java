package com.clstephenson;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main extends Application {

    public static LoginSession session;
    private static ServerSocket socket;
    private static ScheduledExecutorService executorService;

    public static void main(String[] args) throws SQLException {

        /*
        Change the default system time zone.
        */
        TimeZone.setDefault(TimeZone.getTimeZone("America/Phoenix"));
        //TimeZone.setDefault(TimeZone.getTimeZone("America/New_York"));


        /*
        Change the default locale to Italy or French Canadian
        */
        //Locale.setDefault(Locale.ITALY);
        //Locale.setDefault(Locale.CANADA_FRENCH);


        Platform.setImplicitExit(true);
        Runtime.getRuntime().addShutdownHook(new Thread(Main::cleanupResources));
        launch(args);
    }

    private static void cleanupResources() {
        // close the open socket if it exists
        if (socket != null) {
            try {
                executorService.shutdownNow();
                socket.close();
            } catch (Exception notImportant) {
                //do nothing here
            }
        }
    }

    @Override
    public void start(Stage primaryStage) {
        checkIfAppAlreadyRunning();
        FXMLLoader loader = null;
        Parent root = null;
        String fxmlPath = "";
        try {
            fxmlPath = AppConfiguration.getConfigurationProperty("fxml.path") + "Main.fxml";
            loader = new FXMLLoader(Paths.get(fxmlPath).toUri().toURL());
            root = loader.load();
        } catch (Exception e) {
            Dialog.showErrorMessage("Could not load FXML file: " + fxmlPath);
            System.exit(0);
        }
        final int APP_WIDTH = 1000;
        final int APP_HEIGHT = 600;
        Scene scene = new Scene(root, APP_WIDTH, APP_HEIGHT);
        primaryStage.setTitle(Localization.getString("ui.application.title"));
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        FXHelper.addStylesheet(scene);
        startDateTimeThread(loader);
        primaryStage.show();
    }

    /**
     * Uses a server socket to make sure only one instance of the application is running. If the socket cannot be
     * created, it means the socket is already present from another running instance.
     */
    private void checkIfAppAlreadyRunning() {
        try {
            socket = new ServerSocket(56284);
        } catch (IOException e) {
            String msg = "Application is already running.  Please close running application and try again.";
            Dialog.showErrorMessage(msg);
            System.exit(0);
        }
    }

    /**
     * Schedule the current time in the UI to be updated every 1 second.
     *
     * @param loader
     */
    private static void startDateTimeThread(FXMLLoader loader) {
        Label targetLabel = (Label) loader.getNamespace().get("dateTimeLabel");
        executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(() -> Platform.runLater(new Runnable() {
            @Override
            public void run() {
                String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/YY HH:mm:ss"));
                targetLabel.setText(dateTime);
            }
        }), 0L, 1000L, TimeUnit.MILLISECONDS);
    }

    @Override
    public void stop() {
        cleanupResources();
    }
}
