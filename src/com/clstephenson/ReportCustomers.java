package com.clstephenson;

import com.clstephenson.datamodels.Customer;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReportCustomers {

    public ReportCustomers() {
        generateReport();
    }

    private void generateReport() {
        final String SEPARATOR = ",";
        File f = new File(String.format("customerList_%s.csv", LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)));
        try (BufferedWriter out = new BufferedWriter(new FileWriter(f))) {
            out.write(buildHeader(SEPARATOR));
            out.newLine();
            List<Customer> customers = new Customers().getCustomers();
            for (Customer cust : customers) {
                StringBuilder sb = buildCustomerString(cust, SEPARATOR);
                out.write(sb.toString());
                out.newLine();
            }
            showReport(f);
        } catch (IOException e) {
            e.printStackTrace(); //todo handle exception
        }
    }

    private String buildHeader(CharSequence separator) {
        return Arrays.asList("Name", "Address 1", "Address 2", "City", "Country", "Postal Code", "Phone", "Active")
                .stream().collect(Collectors.joining(separator));
    }

    private StringBuilder buildCustomerString(Customer customer, CharSequence separator) {
        StringBuilder sb = new StringBuilder();
        sb.append(customer.getName())
                .append(separator)
                .append(customer.getAddress().getAddressLine1())
                .append(separator)
                .append(customer.getAddress().getAddressLine2())
                .append(separator)
                .append(customer.getAddress().getCity().getName())
                .append(separator)
                .append(customer.getAddress().getCity().getCountry().getName())
                .append(separator)
                .append(customer.getAddress().getZipCode())
                .append(separator)
                .append(customer.getAddress().getPhoneNumber())
                .append(separator)
                .append(customer.isActive() ? "yes" : "no");
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
