package com.clstephenson;

import javafx.scene.Node;

public class Validation {

    public static boolean validateNotEmptyOrNull(String text, Node targetNode) {
        boolean isValid = false;
        if(text != null && !text.isEmpty()) {
            isValid = true;
            FXHelper.removeErrorClass(targetNode);
        } else {
            FXHelper.addErrorClass(targetNode);
        }
        return isValid;
    }

    public static boolean validateAlphaNumericString(String text, Node targetNode) {
        boolean isValid = false;
        if(text.matches("^[\\w ]+$")) {
            isValid = true;
            FXHelper.removeErrorClass(targetNode);
        } else {
            FXHelper.addErrorClass(targetNode);
        }
        return isValid;
    }

    public static boolean validateZipCode(String text, Node targetNode) {
        boolean isValid = false;
        if(text.matches("^[\\w ]+$")) {
            isValid = true;
            FXHelper.removeErrorClass(targetNode);
        } else {
            FXHelper.addErrorClass(targetNode);
        }
        return isValid;
    }

    public static boolean validatePhoneNumber(String text, Node targetNode) {
        boolean isValid = false;
        if(text.matches("^[\\d-() ]+$")) {
            isValid = true;
            FXHelper.removeErrorClass(targetNode);
        } else {
            FXHelper.addErrorClass(targetNode);
        }
        return isValid;
    }

    public static boolean validateAddress(String text, Node targetNode) {
        boolean isValid = false;
        if(text.matches("^[\\w#., ]+$")) {
            isValid = true;
            FXHelper.removeErrorClass(targetNode);
        } else {
            FXHelper.addErrorClass(targetNode);
        }
        return isValid;
    }
}
