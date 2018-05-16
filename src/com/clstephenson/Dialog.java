package com.clstephenson;

import javafx.scene.control.Alert;

public class Dialog {
    private Alert alert;

    public Dialog(Alert.AlertType type) {
        alert = new Alert(type);
    }

    public Dialog(Alert.AlertType type, String title, String message) {
        alert = new Alert(type);
        setTitle(title);
        setMessage(message);
    }

    public void setTitle(String title) {
        alert.setTitle(title);
    }

    public void setMessage(String message) {
        alert.setContentText(message);
    }

    public void showDialog(boolean wait) {
        if(wait) {
            alert.showAndWait();
        } else {
            alert.show();
        }
    }
}
