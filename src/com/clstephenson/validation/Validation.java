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

    void changeTextControlCss() {
        if (this.inputToValidate != null && this.cssClass != null) {
            if (this.message == null || this.message.isEmpty()) {
                this.inputToValidate.getStyleClass().remove(this.cssClass);
                //fixme this should not remove css class for subsequent validation errors on the same control
            } else {
                this.inputToValidate.getStyleClass().add(this.cssClass);
            }
        }
    }

    abstract Optional<String> validate();
}
