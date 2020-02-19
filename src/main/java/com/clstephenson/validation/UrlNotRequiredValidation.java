package com.clstephenson.validation;

import javafx.scene.control.TextInputControl;

import java.util.Optional;

public class UrlNotRequiredValidation extends Validation {

    //matches empty string or the url pattern
    private final String PATTERN = "^$|^(http://www\\.|https://www\\.|http://|https://)?[a-z0-9]+([\\-.][a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(/.*)?$";

    public UrlNotRequiredValidation(TextInputControl inputToValidate, String fieldName) {
        super(inputToValidate, fieldName);
    }

    public UrlNotRequiredValidation(TextInputControl inputToValidate, String fieldName, String cssClass) {
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
