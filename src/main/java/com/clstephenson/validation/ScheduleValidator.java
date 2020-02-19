package com.clstephenson.validation;

import com.clstephenson.AppConfiguration;
import com.clstephenson.AppointmentLocation;
import com.clstephenson.datamodels.Appointment;
import com.clstephenson.datamodels.User;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.List;

public class ScheduleValidator {

    /**
     * Checks that this appointment within the business hours for the appointment office location.
     *
     * @param start    Start of appointment.
     * @param end      End of appointment.
     * @param location Office location where the appointment is scheduled.
     * @return True if the appointment passes validation.
     * @throws AppointmentOutsideBusinessHoursException
     */
    public static boolean isAppointmentWithinBusinessHours(LocalDateTime start, LocalDateTime end, AppointmentLocation location)
            throws AppointmentOutsideBusinessHoursException {
        ZonedDateTime apptStartAtLocation = start.atZone(location.getTimeZoneId());
        ZonedDateTime apptEndAtLocation = end.atZone(location.getTimeZoneId());

        LocalTime businessHoursStart = LocalTime.parse(AppConfiguration.getConfigurationProperty(
                "business.hours.start"));
        LocalTime businessHoursEnd = LocalTime.parse(AppConfiguration.getConfigurationProperty(
                "business.hours.end"));

        /*
        conditions for outside business hours:
        start time OR end time not between business.hours.start and business.hours.end
        */
        if (apptStartAtLocation.toLocalTime().isBefore(businessHoursStart)
                || apptEndAtLocation.toLocalTime().isAfter(businessHoursEnd)) {
            throw new AppointmentOutsideBusinessHoursException(start, end);
        } else {
            return true;
        }
    }

    /**
     * Checks a user's schedule for previously schedule appointments overlapping with a start and end date/time.
     * <p>
     * conditions for overlapping...
     * <ol>
     * <li>start between start and end
     * <li>end between start and end
     * </ol>
     *
     * @param user  User for which the appointment is being scheduled.
     * @param start Start of appointment.
     * @param end   End of appointment.
     * @return True is returned if the appointment is not overlapping with another in the user's schedule.
     * @throws OverlappingAppointmentException Thrown if an overlapping appointment is found in the user's schedule.
     */
    public static boolean isAppointmentNotOverlapping(User user, LocalDateTime start, LocalDateTime end, int apptId)
            throws OverlappingAppointmentException {
        boolean isOverlapping = false;
        List<Appointment> userAppointments = user.getUserAppointments(
                a -> a.getStart().toLocalDate().isEqual(start.toLocalDate())
        );

        Appointment overlappedAppointment = null;
        for (Appointment userAppointment : userAppointments) {
            LocalTime userAppointmentStart = userAppointment.getStart().toLocalTime();
            LocalTime userAppointmentEnd = userAppointment.getEnd().toLocalTime();

            boolean startIsBetweenStartAndEnd = start.toLocalTime().isAfter(userAppointmentStart) &&
                    start.toLocalTime().isBefore(userAppointmentEnd);
            boolean endIsBetweenStartAndEnd = end.toLocalTime().isAfter(userAppointmentStart) &&
                    end.toLocalTime().isBefore(userAppointmentEnd);
            boolean startAndEndAreEqual = start.toLocalTime().equals(userAppointmentStart) &&
                    end.toLocalTime().equals(userAppointmentEnd);

            /*
            do not check userAppointment if the id matches the appointment to validate.  This means it's an edit
            rather than a new appointment and we're comparing the appointment to itself with changes.  This would
            be a false positive.
            */
            if (apptId != userAppointment.getId()) {
                if (startIsBetweenStartAndEnd || endIsBetweenStartAndEnd || startAndEndAreEqual) {
                    isOverlapping = true;
                    overlappedAppointment = userAppointment;
                    break;
                }
            }
        }
        if (isOverlapping) {
            throw new OverlappingAppointmentException(start, end, overlappedAppointment);
        } else {
            return true;
        }
    }
}
