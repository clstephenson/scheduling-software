package com.clstephenson.validation;

import javafx.scene.Node;

import java.util.Optional;

public class PhoneNumberValidation extends Validation {

    public PhoneNumberValidation(String input, String fieldName) {
        super(input, fieldName);
    }

    public PhoneNumberValidation(String input, String fieldName, Node sourceControl, String cssClass) {
        super(input, fieldName, sourceControl, cssClass);
    }

    @Override
    public Optional<String> validate() {
        if ((super.getInputToValidate() == null) || !super.getInputToValidate().matches("^[0-9]{10,13}$")) {
            super.setMessage(super.getFieldName() + " must be between 10 and 13 numbers without spaces or dashes");
        }
        return Optional.ofNullable(super.getMessage());
    }
}
