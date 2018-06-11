package com.clstephenson.validation;

import javafx.scene.control.TextInputControl;

import java.util.Optional;

public class AlphaNumericValidation extends Validation {

    private final String PATTERN = "^[0-9a-zA-Z]+$";

    public AlphaNumericValidation(TextInputControl inputToValidate, String fieldName) {
        super(inputToValidate, fieldName);
    }

    public AlphaNumericValidation(TextInputControl inputToValidate, String fieldName, String cssClass) {
        super(inputToValidate, fieldName, cssClass);
    }

    @Override
    public Optional<String> validate() {
        if (super.getInputToValidate() != null) {
            String text = super.getInputToValidate().getText();
            if (text == null || !text.matches(PATTERN)) {
                super.setMessage(String.format("%s must be between 10 and 13 numbers without spaces or dashes", super.getFieldName()));
            }
        }
        return Optional.ofNullable(super.getMessage());
    }
}
