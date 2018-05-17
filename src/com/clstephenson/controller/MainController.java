package com.clstephenson.controller;

import com.clstephenson.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.nio.file.Paths;
import java.sql.SQLException;

public class MainController {

    @FXML
    private void initialize() {
        Parent root = null;
        try {
            String fxmlPath = AppConfiguration.getConfigurationProperty("fxml.path") + "Login.fxml";
            FXMLLoader loader = new FXMLLoader(Paths.get(fxmlPath).toUri().toURL());
            loader.setResources(Localization.getResourceBundle());
            root = loader.load();
        } catch (Exception e) {
            throw new RuntimeException("Could not load Login.fxml", e);
            //todo fix this exception
        }
        Scene scene = new Scene(root, 300, 300);
        Stage loginStage = new Stage();
        loginStage.setTitle(Localization.getString("ui.application.title"));
        loginStage.setResizable(false);
        loginStage.setScene(scene);
        while(Main.session == null || !Main.session.isLoggedIn()) {
            loginStage.showAndWait();
        }
        try {
            Main.testSchedulingNewAppointment();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        showUserAppointmentsDialog();
    }

    private void showUserAppointmentsDialog() {
        String title = Localization.getString("ui.dialog.upcomingappointments");
        StringBuilder message = new StringBuilder();
        for(Appointment appt : Main.session.getLoggedInUser().getUserAppointmentsNextFifteenMinutes()) {
            message.append(appt.toString());
        }
        String header;
        if(message.length() == 0) {
            header = Localization.getString("ui.dialog.upcomingappointmentsnone");
        } else {
            header = Localization.getString("ui.dialog.upcomingappointmentsmessage");
        }
        new Dialog(Alert.AlertType.INFORMATION, title, header, message.toString()).showDialog(true);
    }
}
