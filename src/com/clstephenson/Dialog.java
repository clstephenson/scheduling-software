package com.clstephenson;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Dialog {
    private Alert alert;

    public Dialog(Alert.AlertType type, String header, String message) {
        alert = new Alert(type);
        setTitle(Localization.getString("ui.application.title"));
        setMessage(message);
        setHeaderText(header);
    }

    public static void showDBError(String msg) {
        String header = "A database error occurred.";
        String message = "Details: " + msg;
        Dialog dialog = new Dialog(Alert.AlertType.ERROR, header, message);
        dialog.setSize(500, 300);
        dialog.showDialog(true);
    }

    public Optional<ButtonType> showDialog(boolean wait) {
        if (alert.getTitle().isEmpty()) {
            alert.setTitle(Localization.getString("ui.application.title"));
        }
        if (wait) {
            return alert.showAndWait();
        } else {
            alert.show();
        }
        return Optional.empty();
    }

    public static void showValidationError(String msg) {
        String header = "Please correct the following fields.";
        String message = msg == null || msg.isEmpty() ? "All fields are required." : msg;
        Dialog dialog = new Dialog(Alert.AlertType.ERROR, header, message);
        dialog.setSize(500, 300);
        dialog.showDialog(true);
    }

    public Dialog(Alert.AlertType type) {
        alert = new Alert(type);
    }

    public static void showSimpleError(String msg) {
        String header = "An error occurred. Please try again.";
        String message = "Details: " + msg;
        Dialog dialog = new Dialog(Alert.AlertType.ERROR, header, message);
        dialog.showDialog(true);
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

    public static void showSimpleMessage(String msg) {
        String message = "Details: " + msg;
        Dialog dialog = new Dialog(Alert.AlertType.ERROR, "", message);
        dialog.showDialog(true);
    }
}
