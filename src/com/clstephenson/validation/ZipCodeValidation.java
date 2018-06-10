package com.clstephenson.validation;

import javafx.scene.Node;

import java.util.Optional;

public class ZipCodeValidation extends Validation {

    public ZipCodeValidation(String input, String fieldName) {
        super(input, fieldName);
    }

    public ZipCodeValidation(String input, String fieldName, Node sourceControl, String cssClass) {
        super(input, fieldName, sourceControl, cssClass);
    }

    @Override
    public Optional<String> validate() {
        if ((super.getInputToValidate() == null) || !super.getInputToValidate().matches("^[0-9]{5}(-[0-9]{4})*$")) {
            super.setMessage(super.getFieldName() + " must be in the format matching 12345 or 12345-1234");
        }
        return Optional.ofNullable(super.getMessage());
    }
}
