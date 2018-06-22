package com.clstephenson;

import com.clstephenson.datamodels.Appointment;
import com.clstephenson.datamodels.User;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReportUserSchedule {

    /**
     * Generate a text report of a user's schedule for the current day.  The report is saved as a txt file
     * and automatically opened in the system's default text editor.
     *
     * @param user User for which the schedule should be created.
     */
    public static void generateReport(User user) {
        LocalDate now = LocalDate.now();
        File f = new File(String.format("schedule_%s_%s.txt", user, now.format(DateTimeFormatter.ISO_LOCAL_DATE)));
        try (BufferedWriter out = new BufferedWriter(new FileWriter(f))) {
            out.write(String.format("%s's Schedule for %s", user, now.format(DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy"))));
            out.newLine();
            out.newLine();
            List<Appointment> appointments = user.getUserAppointments(a -> a.getStart().toLocalDate().equals(now));
            for (Appointment appt : appointments) {
                StringBuilder sb = buildAppointmentString(appt);
                out.write(sb.toString());
                out.newLine();
                out.write("-------------------------------------------------------------------------------------");
                out.newLine();
            }
            showReport(f);
        } catch (IOException e) {
            Dialog.showErrorMessage("User's schedule could not be generated. " + e.getMessage());
        }
    }

    private static StringBuilder buildAppointmentString(Appointment appt) {
        StringBuilder sb = new StringBuilder();
        sb.append(appt.getStart().format(DateTimeFormatter.ofPattern("HH:mm")));
        sb.append(" - ");
        sb.append(appt.getEnd().format(DateTimeFormatter.ofPattern("HH:mm")));
        sb.append("\t");
        sb.append(String.format("%s with %s (ph: %s)", appt.getAppointmentType(), appt.getCustomer().getName(),
                appt.getCustomer().getAddress().getPhoneNumber()));
        sb.append(System.lineSeparator());
        sb.append(String.format("\t\tTopic: %s", appt.getDescription()));
        return sb;
    }

    private static void showReport(File file) throws IOException {
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(file);
        } else {
            throw new IOException("Unable to open " + file.getAbsolutePath());
        }
    }
}
