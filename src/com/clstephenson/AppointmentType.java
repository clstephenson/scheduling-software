package com.clstephenson;

public enum AppointmentType {
    INITIAL_CONSULT ("Initial Consulation"),
    FOLLOW_UP ("Follow-Up");

    private String type;

    public String getType() {
        return type;
    }

    private AppointmentType(String type) {
        this.type = type;
    }
}
