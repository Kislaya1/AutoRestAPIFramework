package com.auto.rest.api.modules.booking;

import com.auto.rest.api.asserts.BaseAssert;
import com.auto.rest.api.pojo.booking.Booking;
import com.auto.rest.api.pojo.booking.BookingData;
import io.restassured.response.Response;

import java.util.function.Supplier;

import static com.auto.rest.api.asserts.ErrorMsg.BOOKING_DATA_NOT_MATCHING;
import static com.auto.rest.api.asserts.ErrorMsg.BOOKING_ID_NOT_MATCHING;

public class BookingAssert extends BaseAssert<BookingAssert> {
  private final Supplier<BookingData> bookingDataSupplier = () -> response.as(BookingData.class);
  private final Supplier<Booking> bookingSupplier = () -> response.as(Booking.class);

  private BookingAssert(Response response) {
    super(BookingAssert.class, response);
  }

  public static BookingAssert verify(final Response response) {
    return new BookingAssert(response);
  }

  public BookingAssert hasBookingId(final String bookingId) {
    softAssertions
        .assertThat(bookingDataSupplier.get().getBookingId())
        .withFailMessage(
            BOOKING_ID_NOT_MATCHING(bookingDataSupplier.get().getBookingId(), bookingId)::getMsg)
        .isEqualTo(bookingId);
    return this;
  }

  public BookingAssert hasBooking(final Booking booking) {
    softAssertions
        .assertThat(bookingSupplier.get())
        .withFailMessage(BOOKING_DATA_NOT_MATCHING(bookingSupplier.get(), booking)::getMsg)
        .isEqualTo(booking);
    return this;
  }

  public BookingAssert hasNewBookingCreated(final Booking booking) {
    softAssertions
        .assertThat(bookingDataSupplier.get().getBooking())
        .withFailMessage(
            BOOKING_DATA_NOT_MATCHING(bookingDataSupplier.get().getBooking(), booking)::getMsg)
        .isEqualTo(booking);
    return this;
  }
}
