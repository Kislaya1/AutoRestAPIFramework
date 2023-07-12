package com.auto.rest.api.enums;

public enum CommonLocations {
  TEMPLATES_LOCATION("com.rest.api.pro.templates");

  private String location;

  CommonLocations(final String location) {
    this.location = location;
  }

  public String get() {
    return this.location;
  }
}
