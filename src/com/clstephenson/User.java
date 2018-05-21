package com.clstephenson;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User {
    private SimpleStringProperty userName = new SimpleStringProperty(this, "userName");
    private SimpleIntegerProperty id = new SimpleIntegerProperty(this, "id");
    private SimpleStringProperty password = new SimpleStringProperty(this, "password");
    private SimpleBooleanProperty isActive = new SimpleBooleanProperty(this, "isActive");

    public User() {}

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

    public List<Appointment> getAppointmentsNextFifteenMinutes() {
        List<Appointment> appointments = new ArrayList<>();
        final int startTimeframe = 15;
        try {
            appointments = ScheduleManager.getUserAppointmentsStartingSoon(getUserName(), startTimeframe);
            return appointments;
        } catch (SQLException e) {
            throw new RuntimeException(e);
            //todo fix exceptions
        }
    }

    public String toString() {
        return this.userName.get();
    }
}
