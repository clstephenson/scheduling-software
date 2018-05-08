package com.clstephenson;

public enum AppointmentLocation {
    PHOENIX ("Phoenix, Arizona"),
    NEW_YORK ("New York, New York"),
    LONDON ("London, England"),
    ONLINE ("Online");

    private final String location;

    @Override
    public String toString() {
        return location;
    }

    private AppointmentLocation(String location) {
        this.location = location;
    }
}
