package com.auto.rest.api.controller.booking;

import com.auto.rest.api.apiBuilder.ApiBuilder;
import com.auto.rest.api.authorization.scopeFactory.UserScope;
import com.auto.rest.api.config.production.IProdEnvConfig;
import com.auto.rest.api.pojo.booking.Booking;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;

import static com.auto.rest.api.apiBuilder.apiMethodFactory.ApiMethodType.*;
import static com.auto.rest.api.authorization.scopeFactory.ScopeFactory.scopeFactory;

@SuppressWarnings("unchecked")
public class BookingAPI {
  private static final IProdEnvConfig config = ConfigFactory.create(IProdEnvConfig.class);
  private static final String BASE_URL = config.BASE_URL();
  private final UserScope scope;

  private BookingAPI(final UserScope scope) {
    this.scope = scope;
  }

  public static BookingAPI uses(final UserScope scope) {
    return new BookingAPI(scope);
  }

  public Response toGetAllBookingsPresent() {
    return new ApiBuilder()
        .baseUri(BASE_URL)
        .headers(scopeFactory().get(scope).scope())
        .fetchResponse(GET, config.BOOKING_ENDPOINT());
  }

  public Response toGetBookingPresent(final String bookingId) {
    return new ApiBuilder()
        .baseUri(BASE_URL)
        .headers(scopeFactory().get(scope).scope())
        .fetchResponse(GET, config.GET_ALL_BOOKING_ENDPOINT(bookingId));
  }

  public Response toCreateNewBooking(final Booking bookingBody) {
    return new ApiBuilder()
        .baseUri(BASE_URL)
        .headers(scopeFactory().get(scope).scope())
        .body(bookingBody)
        .fetchResponse(POST, config.BOOKING_ENDPOINT());
  }

  public Response toUpdateBookingPresent(final Booking updatedBooking, final String bookingId) {
    return new ApiBuilder()
        .baseUri(BASE_URL)
        .headers(scopeFactory().get(scope).scope())
        .body(updatedBooking)
        .fetchResponse(PUT, config.MODIFY_BOOKING_ENDPOINT(bookingId));
  }

  public Response toPartiallyUpdateBookingPresent(
      final Booking partialUpdatedBooking, final String bookingId) {
    return new ApiBuilder()
        .baseUri(BASE_URL)
        .headers(scopeFactory().get(scope).scope())
        .body(partialUpdatedBooking)
        .fetchResponse(PATCH, config.MODIFY_BOOKING_ENDPOINT(bookingId));
  }

  public Response toDeleteBookingPresent(final String bookingId) {
    return new ApiBuilder()
        .baseUri(BASE_URL)
        .headers(scopeFactory().get(scope).scope())
        .fetchResponse(DELETE, config.DELETE_BOOKING_ENDPOINT(bookingId));
  }
}
