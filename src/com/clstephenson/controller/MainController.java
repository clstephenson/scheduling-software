package com.clstephenson.controller;

import com.clstephenson.AppConfiguration;
import com.clstephenson.Localization;
import com.clstephenson.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.nio.file.Paths;

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
        loginStage.setTitle("Scheduling Application"); //todo localize the title
        loginStage.setResizable(false);
        loginStage.setScene(scene);
        while(Main.session == null || !Main.session.isLoggedIn()) {
            loginStage.showAndWait();
        }
    }
}
