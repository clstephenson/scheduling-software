package com.clstephenson;

public enum AppointmentType {
    INITIAL_CONSULT ("Initial Consulation"),
    FOLLOW_UP ("Follow-Up");

    private String type;

    @Override
    public String toString() {
        return type;
    }

    private AppointmentType(String type) {
        this.type = type;
    }
}
