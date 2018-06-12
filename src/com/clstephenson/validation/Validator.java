package com.clstephenson.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class Validator {

    private List<Validation> validations;
    private StringBuilder message;
    private HashMap<String, Integer> numErrorsMap;

    public Validator() {
        validations = new ArrayList<>();
        message = new StringBuilder();
        numErrorsMap = new HashMap<>();
    }

    public List<Validation> getValidations() {
        return validations;
    }

    public Optional<String> validateAll() {
        for (Validation validation : this.validations) {
            if (validation.validate().isPresent()) {
                //if the key exists with a non-null value, then increment by one, else create the key with initial value of zero.
                numErrorsMap.merge(validation.getFieldName(), 0, (k, v) -> v++);
                message.append("* ");
                message.append(validation.getMessage());
                message.append(System.lineSeparator());
                validation.addStyleClass();
            } else {
                numErrorsMap.computeIfPresent(validation.getFieldName(), (k, v) -> (v > 0 ? v-- : 0));
                if (numErrorsMap.getOrDefault(validation.getFieldName(), -1) <= 0) {
                    validation.clearStyleClass();
                }
            }

            //validation.changeTextControlCss();
        }
        return Optional.ofNullable(getMessage());
    }

    public String getMessage() {
        return message.toString();
    }
}
