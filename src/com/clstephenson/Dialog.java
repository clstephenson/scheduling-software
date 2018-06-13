package com.clstephenson;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Dialog {
    private Alert alert;

    public static void showValidationError(String msg) {
        String title = "Data Validation Error";
        String header = "Please correct the following errors.";
        String message = msg == null || msg.isEmpty() ? "All fields are required." : msg;
        Dialog dialog = new Dialog(Alert.AlertType.ERROR, title, header, message);
        dialog.setSize(500, 300);
        dialog.showDialog(true);
    }

    public Dialog(Alert.AlertType type) {
        alert = new Alert(type);
    }

    public Dialog(Alert.AlertType type, String title, String header, String message) {
        alert = new Alert(type);
        setTitle(title);
        setMessage(message);
        setHeaderText(header);
    }

    public void setTitle(String title) {
        alert.setTitle(title);
    }

    public void setMessage(String message) {
        alert.setContentText(message);
    }

    public void setHeaderText(String message) {
        alert.setHeaderText(message);
    }

    public void setSize(double width, double height) {
        alert.setResizable(true);
        alert.getDialogPane().setPrefSize(width, height);
    }

    public Optional<ButtonType> showDialog(boolean wait) {
        if(wait) {
            return alert.showAndWait();
        } else {
            alert.show();
        }
        return Optional.empty();
    }
}
