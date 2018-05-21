package com.clstephenson;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.ZonedDateTime;

public class Appointment {
    private SimpleIntegerProperty id = new SimpleIntegerProperty(this, "id");
    private SimpleObjectProperty<Customer> customer = new SimpleObjectProperty<>(this, "customer");
    private SimpleObjectProperty<AppointmentType> appointmentType = new SimpleObjectProperty<>(this, "appointmentType"); //using title column in db
    private SimpleStringProperty description = new SimpleStringProperty(this, "description");
    private SimpleObjectProperty<AppointmentLocation> appointmentLocation = new SimpleObjectProperty<>(this, "appointmentLocation");
    private SimpleStringProperty consultant = new SimpleStringProperty(this, "consultant"); //using contact column in db
    private SimpleStringProperty url = new SimpleStringProperty(this, "url");
    private SimpleObjectProperty<ZonedDateTime> start = new SimpleObjectProperty<>(this, "start");
    private SimpleObjectProperty<ZonedDateTime> end = new SimpleObjectProperty<>(this, "end");

    public Appointment() {}

    public Appointment(Customer customer, AppointmentType type, String description, AppointmentLocation location,
                       String consultant, String url, ZonedDateTime start, ZonedDateTime end) {
        this.customer.set(customer);
        this.appointmentType.set(type);
        this.description.set(description);
        this.appointmentLocation.set(location);
        this.consultant.set(consultant);
        this.url.set(url);
        this.start.set(start);
        this.end.set(end);
    }

    public Appointment(int id, Customer customer, AppointmentType type, String description, AppointmentLocation location,
                       String consultant, String url, ZonedDateTime start, ZonedDateTime end) {
        this.id.set(id);
        this.customer.set(customer);
        this.appointmentType.set(type);
        this.description.set(description);
        this.appointmentLocation.set(location);
        this.consultant.set(consultant);
        this.url.set(url);
        this.start.set(start);
        this.end.set(end);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public Customer getCustomer() {
        return customer.get();
    }

    public void setCustomer(Customer customer) {
        this.customer.set(customer);
    }

    public AppointmentType getAppointmentType() {
        return appointmentType.get();
    }

    public void setAppointmentType(AppointmentType type) {
        this.appointmentType.set(type);
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public AppointmentLocation getAppointmentLocation() {
        return appointmentLocation.get();
    }

    public void setAppointmentLocation(AppointmentLocation location) {
        this.appointmentLocation.set(location);
    }

    public String getConsultant() {
        return consultant.get();
    }

    public void setConsultant(String consultant) {
        this.consultant.set(consultant);
    }

    public String getUrl() {
        return url.get();
    }

    public void setUrl(String url) {
        this.url.set(url);
    }

    public ZonedDateTime getStart() {
        return start.get();
    }

    public void setStart(ZonedDateTime start) {
        this.start.set(start);
    }

    public ZonedDateTime getEnd() {
        return end.get();
    }

    public void setEnd(ZonedDateTime end) {
        this.end.set(end);
    }

    public boolean hasId() {
        return this.id.get() > 0;
    }

    public String toString() {
        return String.format("[%d, %s, %s, %s, %s, %s, %s, %s, %s]", this.id.get(), this.customer.get().getName(),
                this.appointmentType.get().toString(), this.description.get(), this.appointmentLocation.get().toString(), this.consultant.get(),
                this.url.get(), this.start.get(), this.end.get());
        //todo change format string for start and end dates/times
    }
}
