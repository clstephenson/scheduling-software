package com.clstephenson.validation;

import javafx.scene.control.TextInputControl;

import java.util.Optional;

public class StreetAddressNotRequiredValidation extends Validation {

    private final String PATTERN = "^$|^[0-9a-zA-Z -#.,]+$";

    public StreetAddressNotRequiredValidation(TextInputControl inputToValidate, String fieldName) {
        super(inputToValidate, fieldName);
    }

    public StreetAddressNotRequiredValidation(TextInputControl inputToValidate, String fieldName, String cssClass) {
        super(inputToValidate, fieldName, cssClass);
    }

    @Override
    public Optional<String> validate() {
        if (super.getInputToValidate() != null) {
            String text = super.getInputToValidate().getText();
            if (text == null || !text.matches(PATTERN)) {
                super.setMessage(String.format("%s contains invalid characters", super.getFieldName()));
            }
        }
        return Optional.ofNullable(super.getMessage());
    }
}
