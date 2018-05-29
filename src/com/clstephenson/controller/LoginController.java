package com.clstephenson.controller;

import com.clstephenson.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.security.AccessControlException;

public class LoginController {

    @FXML private PasswordField passwordField;
    @FXML private TextField usernameField;
    @FXML private Button loginButton;
    @FXML private Button exitButton;
    @FXML private Label loginErrorTitleLabel;
    @FXML private Label loginErrorDescriptionLabel;

    @FXML
    private void initialize() {
        loginErrorTitleLabel.setVisible(false);
        loginErrorDescriptionLabel.setVisible(false);
        setUpEventHandlers();
    }

    private void setUpEventHandlers() {
        exitButton.setOnAction(event -> FXHelper.exitApplication());
        loginButton.setOnAction(event -> login());
    }

    void login() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        //Stage srcStage = FXHelper.getStageFromActionEvent(event);
        try {
            Main.session = new LoginSession(username, password);
            if(Main.session.isLoggedIn()) {
                FXHelper.getStageFromNode(usernameField).close();
            }
        } catch (AccessControlException e) {
            loginErrorTitleLabel.setVisible(true);
            loginErrorDescriptionLabel.setVisible(true);
        }
    }

}
