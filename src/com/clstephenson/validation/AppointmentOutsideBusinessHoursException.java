package com.clstephenson.validation;

import com.clstephenson.AppConfiguration;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class AppointmentOutsideBusinessHoursException extends InvalidAppointmentException {

    private LocalTime businessHoursStart;
    private LocalTime businessHoursEnd;

    public AppointmentOutsideBusinessHoursException(LocalDateTime appointmentStart, LocalDateTime appointmentEnd) {
        super(appointmentStart, appointmentEnd);

        this.businessHoursStart = LocalTime.parse(AppConfiguration.getConfigurationProperty(
                "business.hours.start"));

        this.businessHoursEnd = LocalTime.parse(AppConfiguration.getConfigurationProperty(
                "business.hours.end"));
    }

    public LocalTime getBusinessHoursStart() {
        return businessHoursStart;
    }

    public LocalTime getBusinessHoursEnd() {
        return businessHoursEnd;
    }
}
