package com.clstephenson.validation;

import javafx.scene.control.TextInputControl;

import java.util.Optional;

public class TimeValidation extends Validation {

    private final String PATTERN = "^\\d{2}:\\d{2}$";

    public TimeValidation(TextInputControl inputToValidate, String fieldName) {
        super(inputToValidate, fieldName);
    }

    public TimeValidation(TextInputControl inputToValidate, String fieldName, String cssClass) {
        super(inputToValidate, fieldName, cssClass);
    }

    @Override
    public Optional<String> validate() {
        if (super.getInputToValidate() != null) {
            String text = super.getInputToValidate().getText();
            if (text == null || !text.matches(PATTERN)) {
                super.setMessage(String.format("%s must match the format of hh:mm using 24-hour time", super.getFieldName()));
            }
        }
        return Optional.ofNullable(super.getMessage());
    }
}
