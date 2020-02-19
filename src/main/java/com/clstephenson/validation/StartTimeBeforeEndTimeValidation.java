package com.clstephenson.validation;

import javafx.scene.control.TextInputControl;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class StartTimeBeforeEndTimeValidation extends Validation {

    TextInputControl endTimeControl;

    public StartTimeBeforeEndTimeValidation(TextInputControl startTimeControl, TextInputControl endTimeControl, String cssClass) {
        super(startTimeControl, "", cssClass);
        this.endTimeControl = endTimeControl;
    }

    @Override
    public Optional<String> validate() {
        if (super.getInputToValidate() != null && endTimeControl != null) {
            LocalTime start = null;
            LocalTime end = null;
            try {
                start = LocalTime.parse(super.getInputToValidate().getText(), DateTimeFormatter.ofPattern("HHmm"));
                end = LocalTime.parse(endTimeControl.getText(), DateTimeFormatter.ofPattern("HHmm"));
                if (start.isAfter(end)) {
                    super.setMessage("The end time must be after the start time");
                }
            } catch (DateTimeParseException e) {
                super.setMessage("Invalid start or end appointment time");
            }
        }
        return Optional.ofNullable(super.getMessage());
    }
}
