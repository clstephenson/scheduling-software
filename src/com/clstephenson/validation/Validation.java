package com.clstephenson.validation;

import javafx.scene.Node;

import java.util.Optional;

public abstract class Validation {

    private String inputToValidate;
    private String fieldName;
    private Node sourceControl;
    private String cssClass;
    private String message;

    Validation(String input, String fieldName) {
        this.inputToValidate = input;
        this.fieldName = fieldName;
    }

    Validation(String input, String fieldName, Node sourceControl, String cssClass) {
        this.inputToValidate = input;
        this.fieldName = fieldName;
        this.sourceControl = sourceControl;
        this.cssClass = cssClass;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    String getInputToValidate() {
        return inputToValidate;
    }

    void setInputToValidate(String inputToValidate) {
        this.inputToValidate = inputToValidate;
    }

    String getFieldName() {
        return fieldName;
    }

    void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    Node getSourceControl() {
        return this.sourceControl;
    }

    void setSourceControl(Node sourceControl) {
        this.sourceControl = sourceControl;
    }

    String getMessage() {
        return message;
    }

    void setMessage(String message) {
        this.message = message;
    }

    void changeSourceControlStyle() {
        if (this.sourceControl != null && this.cssClass != null) {
            if (this.message.isEmpty()) {
                this.sourceControl.getStyleClass().remove(this.cssClass);
            } else {
                this.sourceControl.getStyleClass().add(this.cssClass);
            }
        }
    }

    abstract Optional<String> validate();
}
