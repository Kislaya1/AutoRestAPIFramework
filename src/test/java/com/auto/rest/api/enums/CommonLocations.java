package com.auto.rest.api.enums;

public enum CommonLocations {
  TEMPLATES_LOCATION("com.auto.rest.api.templates");

  private String location;

  CommonLocations(final String location) {
    this.location = location;
  }

  public String get() {
    return this.location;
  }
}
