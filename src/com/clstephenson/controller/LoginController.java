package com.clstephenson.controller;

import com.clstephenson.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.security.AccessControlException;

public class LoginController {

    //private LoginSession session;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private Button loginButton;

    @FXML
    private Button exitButton;

    @FXML
    private Label loginErrorTitleLabel;

    @FXML
    private Label loginErrorDescriptionLabel;

    @FXML
    void handleExitButtonClicked(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void handleLoginButtonClicked(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        Node srcNode = (Node)event.getSource();
        Stage srcStage = (Stage)srcNode.getScene().getWindow();
        try {
            Main.session = new LoginSession(username, password);
            if(Main.session.isLoggedIn()) {
                srcStage.close();
            }
        } catch (AccessControlException e) {
            loginErrorTitleLabel.setVisible(true);
            loginErrorDescriptionLabel.setVisible(true);
        }

    }

    @FXML
    private void initialize() {
        loginErrorTitleLabel.setVisible(false);
        loginErrorDescriptionLabel.setVisible(false);
    }

}
