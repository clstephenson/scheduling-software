package com.clstephenson.validation;

import com.clstephenson.datamodels.Appointment;

import java.time.LocalDateTime;

public class OverlappingAppointmentException extends InvalidAppointmentException {

    private Appointment overlappedAppointment;

    public OverlappingAppointmentException(LocalDateTime appointmentStart, LocalDateTime appointmentEnd,
                                           Appointment overlappedAppointment) {
        super(appointmentStart, appointmentEnd);
        this.overlappedAppointment = overlappedAppointment;
    }

    public Appointment getOverlappedAppointment() {
        return overlappedAppointment;
    }
}
