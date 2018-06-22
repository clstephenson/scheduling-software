package com.clstephenson;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Dialog {
    private Alert alert;

    /**
     * Create an alert dialog.  showDialog must be called to display the alert.
     *
     * @param type Type of alert to display.
     */
    public Dialog(Alert.AlertType type) {
        alert = new Alert(type);
    }

    /**
     * Create an alert dialog.  showDialog must be called to display the alert.
     *
     * @param type    Type of alert to display.
     * @param header  Header message to display.
     * @param message Message to display to the user.
     */
    public Dialog(Alert.AlertType type, String header, String message) {
        alert = new Alert(type);
        setTitle(Localization.getString("ui.application.title"));
        setMessage(message);
        setHeaderText(header);
    }

    /**
     * Set the title of the dialog window.
     *
     * @param title
     */
    public void setTitle(String title) {
        alert.setTitle(title);
    }

    /**
     * Set the message for the dialog.
     *
     * @param message
     */
    public void setMessage(String message) {
        alert.setContentText(message);
    }

    /**
     * Set the header for the dialog.
     *
     * @param message
     */
    public void setHeaderText(String message) {
        alert.setHeaderText(message);
    }

    /**
     * Show an alert dialog to notify the user than an error has occurred.
     *
     * @param msg Customized message to display to the user.
     */
    public static void showErrorMessage(String msg) {
        String header = "An error occurred. Please try again.";
        String message = "Details: " + msg;
        Dialog dialog = new Dialog(Alert.AlertType.ERROR, header, message);
        dialog.showDialog(true);
    }

    /**
     * Display the dialog.
     * @param wait Display as a modal dialog if true.
     * @return Optional containing the button type that was pressed to close the dialog.
     */
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

    /**
     * Show an alert dialog to display an informational message.
     *
     * @param msg Customized message to display to the user.
     */
    public static void showInformationMessage(String msg) {
        String message = "Details: " + msg;
        Dialog dialog = new Dialog(Alert.AlertType.INFORMATION, "", message);
        dialog.showDialog(true);
    }

    /**
     * Show an alert dialog to notify the user than a database error has occurred.
     *
     * @param msg Customized message to display to the user.
     */
    public static void showDBError(String msg) {
        String header = "A database error occurred.";
        String message = "Details: " + msg;
        Dialog dialog = new Dialog(Alert.AlertType.ERROR, header, message);
        dialog.setSize(500, 300);
        dialog.showDialog(true);
    }

    /**
     * Set the window size of the dialog.
     * @param width
     * @param height
     */
    public void setSize(double width, double height) {
        alert.setResizable(true);
        alert.getDialogPane().setPrefSize(width, height);
    }

    /**
     * Show an alert dialog to notify the user than a validation error has occurred.
     *
     * @param msg Customized message to display to the user.
     */
    public static void showValidationError(String msg) {
        String header = "Please correct the following fields.";
        String message = msg == null || msg.isEmpty() ? "All fields are required." : msg;
        Dialog dialog = new Dialog(Alert.AlertType.ERROR, header, message);
        dialog.setSize(500, 300);
        dialog.showDialog(true);
    }
}
