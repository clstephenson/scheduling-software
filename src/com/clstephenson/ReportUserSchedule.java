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
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;

public class ReportUserSchedule {

    private TemporalUnit timeframe;
    private User user;

    public ReportUserSchedule(User user) {
        this.timeframe = ChronoUnit.DAYS;
        this.user = user;
        generateReport();
    }

    private void generateReport() {
        LocalDate now = LocalDate.now();
        File f = new File(String.format("schedule_%s_%s.txt", this.user, now.format(DateTimeFormatter.ISO_LOCAL_DATE)));
        try (BufferedWriter out = new BufferedWriter(new FileWriter(f))) {
            out.write(String.format("%s's Schedule for %s", this.user, now.format(DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy"))));
            out.newLine();
            out.newLine();
            List<Appointment> appointments = this.user.getUserAppointments(a -> a.getStart().toLocalDate().equals(now));
            for (Appointment appt : appointments) {
                StringBuilder sb = buildAppointmentString(appt);
                out.write(sb.toString());
                out.newLine();
                out.write("-------------------------------------------------------------------------------------");
                out.newLine();
            }
            showReport(f);
        } catch (IOException e) {
            e.printStackTrace(); //todo handle exception
        }
    }

    private StringBuilder buildAppointmentString(Appointment appt) {
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

    private void showReport(File file) throws IOException {
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(file);
        } else {
            //todo show error message or location of created file
        }
    }
}
