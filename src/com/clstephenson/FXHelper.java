package com.clstephenson;

import com.clstephenson.controller.CustomerController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.nio.file.Paths;
import java.util.Optional;

public class FXHelper {
    public static Stage getStageFromActionEvent(ActionEvent event) {
        Node srcNode = (Node)event.getSource();
        return (Stage)srcNode.getScene().getWindow();
    }

    public static Stage getStageFromKeyEvent(KeyEvent event) {
        Node srcNode = (Node)event.getSource();
        return (Stage)srcNode.getScene().getWindow();
    }

    public static Stage getStageFromNode(Node node) {
        return (Stage)node.getScene().getWindow();
    }

    public static void exitApplication() {
        System.out.println("close application requested...");
        Platform.exit();
        System.exit(0);
    }

    public static void showUserLogin() {
        Parent root;
        try {
            String fxmlPath = AppConfiguration.getConfigurationProperty("fxml.path") + "Login.fxml";
            FXMLLoader loader = new FXMLLoader(Paths.get(fxmlPath).toUri().toURL());
            loader.setResources(Localization.getResourceBundle());
            root = loader.load();
        } catch (Exception e) {
            throw new RuntimeException("Could not load Login.fxml", e);
            //todo fix this exception
        }
        Stage loginStage = getLoginStage(root);
        while(LoginSessionHelper.getSession() == null || !LoginSessionHelper.getSession().isLoggedIn()) {
            loginStage.showAndWait();
        }
    }

    private static Stage getLoginStage(Parent root) {
        final int LOGIN_FORM_WIDTH = 300;
        final int LOGIN_FORM_HEIGHT = 200;
        Scene scene = new Scene(root, LOGIN_FORM_WIDTH, LOGIN_FORM_HEIGHT);
        Stage loginStage = new Stage();
        loginStage.setTitle(Localization.getString("ui.application.title"));
        loginStage.setResizable(false);
        loginStage.initModality(Modality.APPLICATION_MODAL);
        loginStage.setScene(scene);
        loginStage.setOnCloseRequest(event -> FXHelper.exitApplication());
        return loginStage;
    }

    public static void showCustomerDetails(Customer customer) {
        Parent root;
        try {
            String fxmlPath = AppConfiguration.getConfigurationProperty("fxml.path") + "Customer.fxml";
            FXMLLoader loader = new FXMLLoader(Paths.get(fxmlPath).toUri().toURL());
            loader.setResources(Localization.getResourceBundle());
            root = loader.load();
            if(customer != null) {
                ((CustomerController)loader.getController()).initData(customer);
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not load Customer.fxml", e);
            //todo fix this exception
        }
        Stage customerStage = getCustomerStage(root);
        customerStage.showAndWait();
    }

    private static Stage getCustomerStage(Parent root) {
        final int CUSTOMER_FORM_WIDTH = 360;
        final int CUSTOMER_FORM_HEIGHT = 400;
        Scene scene = new Scene(root, CUSTOMER_FORM_WIDTH, CUSTOMER_FORM_HEIGHT);
        Stage customerStage = new Stage();
        customerStage.setTitle(Localization.getString("ui.application.title"));
        customerStage.setResizable(false);
        customerStage.initModality(Modality.APPLICATION_MODAL);
        customerStage.setScene(scene);
        return customerStage;
    }
}
