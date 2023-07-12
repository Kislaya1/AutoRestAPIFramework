package com.auto.rest.api.asserts;

import com.auto.rest.api.pojo.booking.Booking;

public interface ErrorMsg {
  static ErrorMsg STATUS_CODE_NOT_MATCHING(
      final int actualStatusCode, final int expectedStatusCode) {
    return () ->
        "Actual Status Code : "
            + actualStatusCode
            + " and Expected Status Code : "
            + expectedStatusCode
            + " are not matching.";
  }

  static ErrorMsg SCHEMA_NOT_MATCHING(final String filePath) {
    return () -> "Schema mis-match occurred. Please check the schema file under : " + filePath;
  }

  static ErrorMsg BOOKING_ID_NOT_MATCHING(
      final String actualBookingId, final String expectedBookingId) {
    return () ->
        "Actual Booking Id : "
            + actualBookingId
            + " and Expected Booking Id : "
            + expectedBookingId
            + " are not matching.";
  }

  static ErrorMsg BOOKING_DATA_NOT_MATCHING(
      final Booking actualBooking, final Booking expectedBooking) {
    return () ->
        "Booking Data Mis-Matched. Please check actual and expected booking data as : "
            + '\n'
            + "Actual Booking : "
            + actualBooking
            + '\n'
            + "Expected Booking : "
            + expectedBooking;
  }

  String getMsg();
}
