package com.crs.models;

public enum BodyType {

    SEDAN("Sedan"),
    SUV("SUV"),
    COUPE("Coupe"),
    HATCHBACK("Hatchback"),
    VAN("Van"),
    COMPACT("Compact");

    public final String bodyTypeValue;

    BodyType(String bodyTypeValue) {
        this.bodyTypeValue = bodyTypeValue;
    }

    public String getBodyTypeValue() {
        return bodyTypeValue;
    }

}
