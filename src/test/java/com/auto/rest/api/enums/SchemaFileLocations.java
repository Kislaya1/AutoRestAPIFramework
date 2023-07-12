package com.auto.rest.api.enums;

public enum SchemaFileLocations {
  BOOKING_DATA_SCHEMA("booking-data-schema.json"),
  BOOKING_SCHEMA("booking-schema.json"),
  GET_ALL_BOOKING_SCHEMA("get-all-booking-schema.json");
  private final String filePath;

  SchemaFileLocations(final String filePath) {
    final String basePath = "src/test/java/com/auto/rest/api/schemas/booking/";
    this.filePath = basePath + filePath;
  }

  public String getFilePath() {
    return filePath;
  }
}
