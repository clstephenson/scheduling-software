package com.clstephenson.validation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class InvalidAppointmentException extends Exception {

    private LocalDateTime appointmentStart;
    private LocalDateTime appointmentEnd;

    public InvalidAppointmentException(LocalDateTime appointmentStart, LocalDateTime appointmentEnd) {
        this.appointmentStart = appointmentStart;
        this.appointmentEnd = appointmentEnd;
    }

    public LocalDate getAppointmentDate() {
        return appointmentStart.toLocalDate();
    }

    public LocalTime getAppointmentStartTime() {
        return appointmentStart.toLocalTime();
    }

    public LocalTime getAppointmentEndTime() {
        return appointmentEnd.toLocalTime();
    }
}
