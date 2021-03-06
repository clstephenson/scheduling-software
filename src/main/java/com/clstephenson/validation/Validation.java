package com.clstephenson.validation;

import javafx.scene.control.TextInputControl;

import java.util.Optional;

public abstract class Validation {

    private TextInputControl inputToValidate;
    private String fieldName;
    private String cssClass;
    private String message;

    Validation(TextInputControl fieldToValidate, String fieldName) {
        this.inputToValidate = fieldToValidate;
        this.fieldName = fieldName;
    }

    Validation(TextInputControl fieldToValidate, String fieldName, String cssClass) {
        this.inputToValidate = fieldToValidate;
        this.fieldName = fieldName;
        this.cssClass = cssClass;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    TextInputControl getInputToValidate() {
        return inputToValidate;
    }

    void setInputToValidate(TextInputControl inputToValidate) {
        this.inputToValidate = inputToValidate;
    }

    String getFieldName() {
        return fieldName;
    }

    void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    String getMessage() {
        return message;
    }

    void setMessage(String message) {
        this.message = message;
    }

    void addStyleClass() {
        if (inputToValidate != null && cssClass != null) {
            if (!inputToValidate.getStyleClass().contains(cssClass)) {
                inputToValidate.getStyleClass().add(cssClass);
            }
        }
    }

    void clearStyleClass() {
        if (inputToValidate != null && cssClass != null) {
            if (inputToValidate.getStyleClass().contains(cssClass)) {
                inputToValidate.getStyleClass().remove(cssClass);
            }
        }
    }

    abstract Optional<String> validate();
}
