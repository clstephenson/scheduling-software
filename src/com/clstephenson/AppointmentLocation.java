package com.clstephenson;

public enum AppointmentLocation {
    PHOENIX ("Phoenix, Arizona"),
    NEW_YORK ("New York, New York"),
    LONDON ("London, England");

    private final String location;

    public String getLocation() {
        return location;
    }

    private AppointmentLocation(String location) {
        this.location = location;
    }
}
