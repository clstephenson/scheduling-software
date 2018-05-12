package com.clstephenson;

import java.time.ZoneId;

public enum AppointmentLocation {
    PHOENIX ("Phoenix, Arizona", "America/Phoenix"),
    NEW_YORK ("New York, New York", "America/New_York"),
    LONDON ("London, England", "Europe/London");

    private final String location;
    private final String timeZone;

    @Override
    public String toString() {
        return location;
    }

    public ZoneId getTimeZoneId() {
        return ZoneId.of(timeZone);
    }

    private AppointmentLocation(String location, String timeZone) {
        this.location = location;
        this.timeZone = timeZone;
    }
}
