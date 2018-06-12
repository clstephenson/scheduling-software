package com.clstephenson.validation;

import javafx.scene.control.TextInputControl;

import java.util.Optional;

public class UrlValidation extends Validation {

    private final String PATTERN = "^((https?|ftp|file)://)?[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

    public UrlValidation(TextInputControl inputToValidate, String fieldName) {
        super(inputToValidate, fieldName);
    }

    public UrlValidation(TextInputControl inputToValidate, String fieldName, String cssClass) {
        super(inputToValidate, fieldName, cssClass);
    }

    @Override
    public Optional<String> validate() {
        if (super.getInputToValidate() != null) {
            String text = super.getInputToValidate().getText();
            if (text == null || !text.matches(PATTERN)) {
                super.setMessage(String.format("%s is not a properly formatted URL", super.getFieldName()));
            }
        }
        return Optional.ofNullable(super.getMessage());
    }
}
