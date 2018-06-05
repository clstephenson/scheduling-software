package com.clstephenson;

public enum AppointmentType {
    INTRO ("Initial Introduction"),
    CONSULT ("Consultation");

    private String type;

    @Override
    public String toString() {
        return type;
    }

    private AppointmentType(String type) {
        this.type = type;
    }
}
