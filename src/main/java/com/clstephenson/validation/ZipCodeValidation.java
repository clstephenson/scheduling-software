package com.clstephenson.validation;

import javafx.scene.control.TextInputControl;

import java.util.Optional;

public class ZipCodeValidation extends Validation {

    private final String PATTERN = "^[0-9]{5}(-[0-9]{4})*$";

    public ZipCodeValidation(TextInputControl inputToValidate, String fieldName) {
        super(inputToValidate, fieldName);
    }

    public ZipCodeValidation(TextInputControl inputToValidate, String fieldName, String cssClass) {
        super(inputToValidate, fieldName, cssClass);
    }

    @Override
    public Optional<String> validate() {
        if (super.getInputToValidate() != null) {
            String text = super.getInputToValidate().getText();
            if (text == null || !text.matches(PATTERN)) {
                super.setMessage(String.format("%s must be in the format matching 12345 or 12345-1234", super.getFieldName()));
            }
        }
        return Optional.ofNullable(super.getMessage());
    }
}
