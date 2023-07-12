package com.auto.rest.api.enums;

public enum ReportingLocation {
  REPORT_FOLDER("/report-output/");

  private String location;

  ReportingLocation(final String location) {
    this.location = System.getProperty("user.dir") + location;
  }

  public String get() {
    return this.location;
  }
}
