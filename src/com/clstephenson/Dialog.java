package com.clstephenson;

import javafx.scene.control.Alert;

public class Dialog {
    private Alert alert;

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

    public void showDialog(boolean wait) {
        if(wait) {
            alert.showAndWait();
        } else {
            alert.show();
        }
    }
}
