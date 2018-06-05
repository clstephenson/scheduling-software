package com.clstephenson;

import com.clstephenson.datamodels.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.*;
import java.util.List;

public class Main extends Application{

    private static ServerSocket socket;
    public static LoginSession session;

    public static void main(String[] args) throws SQLException {

        //TimeZone.setDefault(TimeZone.getTimeZone("America/New_York"));
        //Locale.setDefault(Locale.ITALY);

        System.out.println("Current Date/Time: " + ZonedDateTime.now());

        List<Appointment> appointmentsNextFifteenMinutes =
                ScheduleManager.getUserAppointmentsStartingSoon("test", 15);
        System.out.println("**Appointments in next 15 minutes**");
        appointmentsNextFifteenMinutes.stream().forEach(System.out::println);
        System.out.println("**End of upcoming appointments**");

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        checkIfAppAlreadyRunning();

        Parent root = null;
        try {
            String fxmlPath = AppConfiguration.getConfigurationProperty("fxml.path") + "Main.fxml";
            root = FXMLLoader.load(Paths.get(fxmlPath).toUri().toURL());
        } catch (Exception e) {
            throw new RuntimeException("Could not load Main.fxml", e);
            //todo fix this exception handling
        }
        final int APP_WIDTH = 1000;
        final int APP_HEIGHT = 600;
        Scene scene = new Scene(root, APP_WIDTH, APP_HEIGHT);
        primaryStage.setTitle("Scheduling Application"); //todo localize the title
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        FXHelper.addStylesheet(scene);
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
            String msg = "Application is already running.  Exiting...";
            Dialog dialog = new Dialog(Alert.AlertType.ERROR);
            dialog.setTitle("Application Error");
            dialog.setMessage(msg);
            dialog.showDialog(true);
            System.exit(0);
        }
    }

    @Override
    public void stop() {
        // close the open socket if it exists
        if(socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                //do nothing here
            }
        }
    }
}
