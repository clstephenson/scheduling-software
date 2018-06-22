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

    /**
     * Create a CSV (comma-separated values) file, containing a list of all customers in the database.
     *
     * @param openAfterSaving Attempt to open the file in the systems default editor for CSV files after the
     *                        report is generated and saved.
     */
    public static void generateReport(boolean openAfterSaving) {
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
            if (openAfterSaving)
                showReport(f);
        } catch (IOException e) {
            Dialog.showErrorMessage("Customer report could not be generated. " + e.getMessage());
        }
    }

    private static String buildHeader(CharSequence separator) {
        return Arrays.asList("Name", "Address 1", "Address 2", "City", "Country", "Postal Code", "Phone", "Active")
                .stream().collect(Collectors.joining(separator));
    }

    private static StringBuilder buildCustomerString(Customer customer, CharSequence separator) {
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

    private static void showReport(File file) throws IOException {
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(file);
        } else {
            throw new IOException("Unable to open " + file.getAbsolutePath());
        }
    }
}
