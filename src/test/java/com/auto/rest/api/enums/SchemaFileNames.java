package com.auto.rest.api.enums;

public enum SchemaFileNames {
    BOOKING_DATA_SCHEMA("booking-data-schema.json"),
    BOOKING_SCHEMA("booking-schema.json"),
    GET_ALL_BOOKING_SCHEMA("get-all-booking-schema.json");
    private String filePath;

    SchemaFileNames(final String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
