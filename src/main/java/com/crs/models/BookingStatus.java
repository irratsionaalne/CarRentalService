package com.crs.models;

public enum BookingStatus {

    UPCOMING("Upcoming"),
    COMPLETED("Completed"),
    IN_PROGRESS("In Progress"),
    CANCELLED("Cancelled");

    public final String displayValue;

    BookingStatus(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
