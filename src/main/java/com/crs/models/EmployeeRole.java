package com.crs.models;

public enum EmployeeRole {
    EMPLOYEE("Employee"),
    MANAGER("Manager"),
    OWNER("Owner");

    public final String displayValue;

    EmployeeRole(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
