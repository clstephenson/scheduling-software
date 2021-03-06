package com.clstephenson.datamodels;

import com.clstephenson.DataRepositoryException;
import com.clstephenson.Dialog;
import com.clstephenson.dataaccess.AppointmentRepository;
import com.clstephenson.dataaccess.UserRepository;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.function.Predicate;

public class User {
    private SimpleStringProperty userName = new SimpleStringProperty(this, "userName");
    private SimpleIntegerProperty id = new SimpleIntegerProperty(this, "id");
    private SimpleStringProperty password = new SimpleStringProperty(this, "password");
    private SimpleBooleanProperty isActive = new SimpleBooleanProperty(this, "isActive");

    private ObservableList<Appointment> userAppointments = FXCollections.observableArrayList();

    public static User getActiveUser(String username, String password) {
        try {
            UserRepository userRepository = new UserRepository();
            return userRepository.findSingle(u ->
                    u.getUserName().equalsIgnoreCase(username) &&
                            u.getPassword().equals(password) &&
                            u.isActive()
            );
        } catch (DataRepositoryException e) {
            Dialog.showDBError(e.getMessage());
            return null;
        }
    }

    public User() {
        this.userName.set("");
        this.password.set("");
    }

    public User(String userName, String password, boolean isActive) {
        this.userName.set(userName);
        this.password.set(password);
        this.isActive.set(isActive);
    }

    public User(int id, String userName, String password, boolean isActive) {
        this.id.set(id);
        this.userName.set(userName);
        this.password.set(password);
        this.isActive.set(isActive);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getUserName() {
        return userName.get();
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public boolean isActive() {
        return isActive.get();
    }

    public void setActive(boolean active) {
        isActive.set(active);
    }

    public boolean hasId() {
        return this.id.get() > 0;
    }

    public ObservableList<Appointment> getUserAppointments() {
        try {
            userAppointments = FXCollections.observableArrayList(
                    new AppointmentRepository().find(appt -> appt.getConsultant().equalsIgnoreCase(this.getUserName()))
            );
        } catch (DataRepositoryException e) {
            Dialog.showDBError(e.getMessage());
        }
        return userAppointments;
    }

    public ObservableList<Appointment> getUserAppointments(Predicate<Appointment> predicate) {
        return getUserAppointments().filtered(predicate);
    }

    public List<Appointment> getUserFutureAppointments(TemporalUnit temporalUnit, int numUnits) {
        LocalDateTime end = LocalDateTime.now().plus(numUnits, temporalUnit);
        return getUserAppointments(a -> a.getStart().isAfter(LocalDateTime.now()) && a.getStart().isBefore(end));
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id.equals(user.id);
    }

    @Override
    public String toString() {
        return this.userName.get();
    }
}
