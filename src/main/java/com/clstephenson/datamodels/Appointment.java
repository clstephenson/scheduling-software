package com.clstephenson.datamodels;

import com.clstephenson.*;
import com.clstephenson.dataaccess.AppointmentRepository;
import javafx.beans.property.*;

import java.time.LocalDateTime;

public class Appointment implements Comparable {
    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private ObjectProperty<Customer> customer = new SimpleObjectProperty<>(this, "customer");
    private ObjectProperty<AppointmentType> appointmentType = new SimpleObjectProperty<>(this, "appointmentType"); //using title column in db
    private StringProperty description = new SimpleStringProperty(this, "description");
    private ObjectProperty<AppointmentLocation> appointmentLocation = new SimpleObjectProperty<>(this, "appointmentLocation");
    private StringProperty consultant = new SimpleStringProperty(this, "consultant"); //using contact column in db
    private StringProperty url = new SimpleStringProperty(this, "url");
    private ObjectProperty<LocalDateTime> start = new SimpleObjectProperty<>(this, "start");
    private ObjectProperty<LocalDateTime> end = new SimpleObjectProperty<>(this, "end");

    public Appointment() {
        this.description.set("");
        this.consultant.set("");
        this.url.set("");
    }

    public Appointment(Customer customer, AppointmentType type, String description, AppointmentLocation location,
                       String consultant, String url, LocalDateTime start, LocalDateTime end) {
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
                       String consultant, String url, LocalDateTime start, LocalDateTime end) {
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

    public IntegerProperty idProperty() {
        return id;
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public ObjectProperty<Customer> customerProperty() {
        return customer;
    }

    public Customer getCustomer() {
        return customer.get();
    }

    public void setCustomer(Customer customer) {
        this.customer.set(customer);
    }

    public ObjectProperty<AppointmentType> appointmentTypeProperty() {
        return appointmentType;
    }

    public AppointmentType getAppointmentType() {
        return appointmentType.get();
    }

    public void setAppointmentType(AppointmentType type) {
        this.appointmentType.set(type);
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public ObjectProperty<AppointmentLocation> appointmentLocationProperty() {
        return appointmentLocation;
    }

    public AppointmentLocation getAppointmentLocation() {
        return appointmentLocation.get();
    }

    public void setAppointmentLocation(AppointmentLocation location) {
        this.appointmentLocation.set(location);
    }

    public StringProperty consultantProperty() {
        return consultant;
    }

    public String getConsultant() {
        return consultant.get();
    }

    public void setConsultant(String consultant) {
        this.consultant.set(consultant);
    }

    public StringProperty urlProperty() {
        return url;
    }

    public String getUrl() {
        return url.get();
    }

    public void setUrl(String url) {
        this.url.set(url.isEmpty() ? "" : url);
    }

    public ObjectProperty<LocalDateTime> startProperty() {
        return start;
    }

    public LocalDateTime getStart() {
        return start.get();
    }

    public void setStart(LocalDateTime start) {
        this.start.set(start);
    }

    public ObjectProperty<LocalDateTime> endProperty() {
        return end;
    }

    public LocalDateTime getEnd() {
        return end.get();
    }

    public void setEnd(LocalDateTime end) {
        this.end.set(end);
    }

    public boolean hasId() {
        return this.id.get() > 0;
    }

    public boolean save() {
        boolean result = false;
        int resultId = 0;
        try {
            AppointmentRepository repository = new AppointmentRepository();
            if (this.id.get() > 0) {
                result = repository.update(this, LoginSessionHelper.getSession());
            } else {
                resultId = repository.add(this, LoginSessionHelper.getSession());
                if (resultId > 0) this.id.set(resultId);
            }
        } catch (DataRepositoryException e) {
            Dialog.showDBError(e.getMessage());
        }
        return result || resultId > 0;
    }

    public boolean remove() {
        boolean result = false;
        if(this.id.get() > 0) {
            try {
                AppointmentRepository repository = new AppointmentRepository();
                if (repository.remove(this)) {
                    this.id.set(0);
                    result = true;
                }
            } catch (DataRepositoryException e) {
                Dialog.showDBError(e.getMessage());
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Appointment that = (Appointment) o;

        return id.equals(that.id);
    }

    @Override
    public String toString() {
        return String.format("[%d, %s, %s, %s, %s, %s, %s, %s, %s]", this.id.get(), this.customer.get().getName(),
                this.appointmentType.get().toString(), this.description.get(), this.appointmentLocation.get().toString(),
                this.consultant.get(), this.url.get(), this.start.get(), this.end.get());
    }

    @Override
    public int compareTo(Object o) {
        Appointment other = (Appointment) o;
        return this.getStart().compareTo(other.getStart());
    }
}
