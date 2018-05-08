package com.clstephenson;

import java.time.ZonedDateTime;

public class Appointment {
    private int id;
    private Customer customer;
    private AppointmentType appointmentType; //using title column in db
    private String description;
    private AppointmentLocation appointmentLocation;
    private String consultant; //using contact column in db
    private String url;
    private ZonedDateTime start;
    private ZonedDateTime end;

    public Appointment() {}

    public Appointment(Customer customer, AppointmentType type, String description, AppointmentLocation location,
                       String consultant, String url, ZonedDateTime start, ZonedDateTime end) {
        this.customer = customer;
        this.appointmentType = type;
        this.description = description;
        this.appointmentLocation = location;
        this.consultant = consultant;
        this.url = url;
        this.start = start;
        this.end = end;
    }

    public Appointment(int id, Customer customer, AppointmentType type, String description, AppointmentLocation location,
                       String consultant, String url, ZonedDateTime start, ZonedDateTime end) {
        this.id = id;
        this.customer = customer;
        this.appointmentType = type;
        this.description = description;
        this.appointmentLocation = location;
        this.consultant = consultant;
        this.url = url;
        this.start = start;
        this.end = end;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public AppointmentType getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(AppointmentType type) {
        this.appointmentType = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AppointmentLocation getAppointmentLocation() {
        return appointmentLocation;
    }

    public void setAppointmentLocation(AppointmentLocation location) {
        this.appointmentLocation = location;
    }

    public String getConsultant() {
        return consultant;
    }

    public void setConsultant(String consultant) {
        this.consultant = consultant;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ZonedDateTime getStart() {
        return start;
    }

    public void setStart(ZonedDateTime start) {
        this.start = start;
    }

    public ZonedDateTime getEnd() {
        return end;
    }

    public void setEnd(ZonedDateTime end) {
        this.end = end;
    }

    public boolean hasId() {
        return this.id > 0;
    }

    public String toString() {
        return String.format("[%d, %s, %s, %s, %s, %s, %s, %s, %s]", this.id, this.customer.getName(),
                this.appointmentType.toString(), this.description, this.appointmentLocation.toString(), this.consultant,
                this.url, this.start, this.end);
        //todo change format string for start and end dates/times
    }
}
