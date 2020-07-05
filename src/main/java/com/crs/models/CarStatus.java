package com.crs.models;


public enum CarStatus {

    AVAILABLE("Available"),
    BOOKED("Booked"),
    RENTED("Rented"),
    NOT_AVAILABLE("Not available");

    public final String displayValue;

    CarStatus(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
