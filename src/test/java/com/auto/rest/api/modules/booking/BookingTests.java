package com.auto.rest.api.modules.booking;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.auto.rest.api.argumentProviders.BookingArgumentsProvider;
import com.auto.rest.api.authorization.scopeFactory.UserScope;
import com.auto.rest.api.controller.booking.BookingAPI;
import com.auto.rest.api.enums.CommonLocations;
import com.auto.rest.api.pojo.booking.Booking;
import com.auto.rest.api.pojo.booking.BookingData;
import com.auto.rest.api.utils.DataMockUtils;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static com.auto.rest.api.enums.SchemaFileLocations.*;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;

public class BookingTests {
  private String bookingId;

  @BeforeAll
  public static void setUpClass() {
    FixtureFactoryLoader.loadTemplates(CommonLocations.TEMPLATES_LOCATION.get());
  }

  @BeforeEach
  public void setUp() {
    Booking bookingBody = DataMockUtils.fetchBookingRequestPojo().mockData();
    Response createdBookingResponse =
        BookingAPI.uses(UserScope.ADMIN).toCreateNewBooking(bookingBody);
    bookingId = createdBookingResponse.as(BookingData.class).getBookingId();
    BookingAssert.verify(createdBookingResponse)
        .statusCodeIs(SC_OK)
        .hasBookingId(bookingId)
        .hasNewBookingCreated(bookingBody)
        .matchesSchema(BOOKING_DATA_SCHEMA.getFilePath())
        .assertAll();
  }

  @ParameterizedTest
  @ArgumentsSource(BookingArgumentsProvider.class)
  void assertThatUsersCanUpdateExistingBooking(final Booking updatedBookingRequest) {
    Response updateNewBookingResponse =
        BookingAPI.uses(UserScope.ADMIN).toUpdateBookingPresent(updatedBookingRequest, bookingId);
    BookingAssert.verify(updateNewBookingResponse)
        .statusCodeIs(SC_OK)
        .hasBooking(updatedBookingRequest)
        .matchesSchema(BOOKING_SCHEMA.getFilePath())
        .assertAll();
  }

  @Test
  void assertThatUserCanGetAllBookings() {
    Response getAllBookingResponse = BookingAPI.uses(UserScope.DEVELOPER).toGetAllBookingsPresent();
    BookingAssert.verify(getAllBookingResponse)
        .statusCodeIs(SC_OK)
        .matchesSchema(GET_ALL_BOOKING_SCHEMA.getFilePath())
        .assertAll();
  }

  @Test
  void assertThatUserCanGetSingleBooking() {
    Response getSingleBookingResponse =
        BookingAPI.uses(UserScope.DEVELOPER).toGetBookingPresent(bookingId);
    BookingAssert.verify(getSingleBookingResponse)
        .statusCodeIs(SC_OK)
        .matchesSchema(BOOKING_SCHEMA.getFilePath())
        .assertAll();
  }

  @ParameterizedTest
  @ArgumentsSource(BookingArgumentsProvider.class)
  void assertThatUserCanPartiallyUpdateExistingBooking(final Booking partialUpdatedBookingRequest) {
    Response partialUpdatedBookingResponse =
        BookingAPI.uses(UserScope.ADMIN)
            .toPartiallyUpdateBookingPresent(partialUpdatedBookingRequest, bookingId);
    BookingAssert.verify(partialUpdatedBookingResponse)
        .statusCodeIs(SC_OK)
        .hasBooking(partialUpdatedBookingRequest)
        .matchesSchema(BOOKING_SCHEMA.getFilePath())
        .assertAll();
  }

  @AfterEach
  public void tearDown() {
    Response deletedBookingResponse =
        BookingAPI.uses(UserScope.ADMIN).toDeleteBookingPresent(bookingId);
    BookingAssert.verify(deletedBookingResponse).statusCodeIs(SC_CREATED);
  }
}
