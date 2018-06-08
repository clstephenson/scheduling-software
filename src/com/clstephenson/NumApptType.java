package com.clstephenson;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class NumApptType {

    private SimpleIntegerProperty month = new SimpleIntegerProperty(this, "month");
    private SimpleObjectProperty<AppointmentType> type = new SimpleObjectProperty<>(this, "type");
    private SimpleIntegerProperty numAppointments = new SimpleIntegerProperty(this, "numAppointments");

    public NumApptType(int month, AppointmentType type, int numAppointments) {
        this.month.set(month);
        this.type.set(type);
        this.numAppointments.set(numAppointments);
    }

    public int getMonth() {
        return month.get();
    }

    public SimpleIntegerProperty monthProperty() {
        return month;
    }

    public AppointmentType getType() {
        return type.get();
    }

    public SimpleObjectProperty<AppointmentType> typeProperty() {
        return type;
    }

    public int getNumAppointments() {
        return numAppointments.get();
    }

    public SimpleIntegerProperty numAppointmentsProperty() {
        return numAppointments;
    }
}
