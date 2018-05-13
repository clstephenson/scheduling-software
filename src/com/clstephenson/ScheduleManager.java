package com.clstephenson;

import com.clstephenson.dataaccess.AppointmentRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.time.*;
import java.util.List;

public class ScheduleManager {

    public static Appointment scheduleAppointment(Customer customer, AppointmentType type, String description,
                                           AppointmentLocation location, String consultant, String url,
                                           ZonedDateTime start, ZonedDateTime end, LoginSession session)  throws IOException, SQLException {
        if(isAppointmentOverlapping(consultant, start, end)) {
            //todo throw custom exception
            //throw new RuntimeException("need custom exception here");
            return null;
        } else if (isAppointmentOutsideBusinessHours(start, end, location)) {
            //todo throw custom exception
            //throw new RuntimeException("need custom exception here");
            return null;
        } else {
            Appointment newAppointment = new Appointment();
            newAppointment.setCustomer(customer);
            newAppointment.setAppointmentType(type);
            newAppointment.setDescription(description);
            newAppointment.setAppointmentLocation(location);
            newAppointment.setConsultant(consultant);
            newAppointment.setUrl(url);
            newAppointment.setStart(start);
            newAppointment.setEnd(end);

            AppointmentRepository appointmentRepository = new AppointmentRepository();
            int appointmentId = appointmentRepository.add(newAppointment, session);
            if(appointmentId > 0) {
                newAppointment.setId(appointmentId);
                return newAppointment;
            } else {
                return null;
            }
        }
    }

    private static boolean isAppointmentOutsideBusinessHours(ZonedDateTime start, ZonedDateTime end,
                                                             AppointmentLocation location)  throws IOException {
        // conditions for outside business hours
        // start time OR end time not between business.hours.start and business.hours.end
        LocalTime businessHoursStart = LocalTime.parse(
                AppConfiguration.getConfigurationProperty("business.hours.start"));
        LocalTime businessHoursEnd = LocalTime.parse(
                AppConfiguration.getConfigurationProperty("business.hours.end"));
        if(start.toLocalTime().isBefore(businessHoursStart) || end.toLocalTime().isAfter(businessHoursEnd)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isAppointmentOverlapping(String consultant, ZonedDateTime start, ZonedDateTime end)
            throws IOException, SQLException {
        AppointmentRepository appointmentRepository = new AppointmentRepository();
        List<Appointment> consultantAppointments = appointmentRepository.find(
                appointment -> appointment.getConsultant().equals(consultant)
        );

        // conditions for overlapping...
        // 1. start between start and end
        // 2. end between start and end

        for (Appointment appointment : consultantAppointments) {
            LocalTime existingAppointmentStart = appointment.getStart().toLocalTime();
            LocalTime existingAppointmentEnd = appointment.getEnd().toLocalTime();

            boolean startIsBetweenStartAndEnd = start.toLocalTime().isAfter(existingAppointmentStart) &&
                    start.toLocalTime().isBefore(existingAppointmentEnd);
            boolean endIsBetweenStartAndEnd = end.toLocalTime().isAfter(existingAppointmentStart) &&
                    end.toLocalTime().isBefore(existingAppointmentEnd);

            if(startIsBetweenStartAndEnd || endIsBetweenStartAndEnd) {
                return true;
            }
        }
        return false;
    }

    public static List<Appointment> getUserAppointmentsNextFifteenMinutes(String username) throws IOException, SQLException {
        ZonedDateTime fifteenMinutesFromNow = ZonedDateTime.now().plusMinutes(15);
        List<Appointment> userAppointmentsNextFifteenMinutes = new AppointmentRepository().find(
                a -> a.getConsultant().equals(username) &&
                            (a.getStart().isAfter(ZonedDateTime.now()) && a.getStart().isBefore(fifteenMinutesFromNow))
        );
        return userAppointmentsNextFifteenMinutes;
    }
}
