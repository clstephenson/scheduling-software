package com.clstephenson.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Validator {

    private List<Validation> validations;
    private StringBuilder message;

    public Validator() {
        validations = new ArrayList<>();
        message = new StringBuilder();
    }

    public List<Validation> getValidations() {
        return validations;
    }

    public Optional<String> validateAll() {
        for (Validation v : this.validations) {
            if (v.validate().isPresent()) {
                message.append("* ");
                message.append(v.getMessage());
                message.append(System.lineSeparator());
            }
            v.changeSourceControlStyle();
        }
        return Optional.ofNullable(getMessage());
    }

    public String getMessage() {
        return message.toString();
    }
}
