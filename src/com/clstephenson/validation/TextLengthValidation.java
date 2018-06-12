package com.clstephenson.validation;

import javafx.scene.control.TextInputControl;

import java.util.Optional;

public class TextLengthValidation extends Validation {

    private int minLength;
    private int maxLength;

    public TextLengthValidation(TextInputControl inputToValidate, String fieldName, int minLength, int maxLength) {
        super(inputToValidate, fieldName);
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    public TextLengthValidation(TextInputControl inputToValidate, String fieldName, String cssClass, int minLength, int maxLength) {
        super(inputToValidate, fieldName, cssClass);
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    @Override
    public Optional<String> validate() {
        if (super.getInputToValidate() != null) {
            int length = super.getInputToValidate().getLength();
            if (length <= minLength || length > maxLength) {
                super.setMessage(String.format("%s must be between %d and %d characters in length", super.getFieldName(), minLength, maxLength));
            }
        }
        return Optional.ofNullable(super.getMessage());
    }
}
