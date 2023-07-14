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

/*
 *                      To Do Checklist
 * Refactor code for reporting.
 * Get the know about what is "SELF_TYPE" for interview question.
 * Learn about Junit 5 for interview.
 * Read about Slf4j.
 * -------------------------------------------------------------------------------
 * Done
 * Add more test cases under Booking Test File -- Done
 * Logger and Reporting for Framework -- Done
 * Run the test cases in Parallel - Done
 * Scope for Auth API (Scope Agnostic) - Done
 * uses <some> pattern to fetch the token so that it is accessed only when needed and create scope for auth token. - Singleton
 * Check video on pojo fixture to create diff Pojo's - Done
 * Check for Dates under Date Strategy or ArgumentProvider. - Done
 * Fix BookingTemplates - Done
 * Schema Validations. (Do this during assertion)  -- Done
 * Avoid using object mapper and uses Response and during assertion only uses object mappers. -- Done
 * Check the way to uses generic Pojo's -- Done
 * Remove Object Mapper dependencies and instead uses response.as(XYZ.class) -- Done
 * Under Assertion Msg print the actual and expected values also. -- Done
 * */

public class BookingTests {
  private String bookingId;

  @BeforeAll
  public static void setUpClass() {
    FixtureFactoryLoader.loadTemplates(CommonLocations.TEMPLATES_LOCATION.get());
  }

  @BeforeEach
  public void setUp() {
    Booking bookingBody = DataMockUtils.fetchBookingRequestPojo().mockData();
    // Act
    Response createdBookingResponse =
        BookingAPI.uses(UserScope.ADMIN).toCreateNewBooking(bookingBody);
    // Fetch Booking ID
    bookingId = createdBookingResponse.as(BookingData.class).getBookingId();
    // Assert
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
    // Act
    Response updateNewBookingResponse =
        BookingAPI.uses(UserScope.ADMIN).toUpdateBookingPresent(updatedBookingRequest, bookingId);
    // Assert
    BookingAssert.verify(updateNewBookingResponse)
        .statusCodeIs(SC_OK)
        .hasBooking(updatedBookingRequest)
        .matchesSchema(BOOKING_SCHEMA.getFilePath())
        .assertAll();
  }

  @Test
  void assertThatUserCanGetAllBookings() {
    // Act
    Response getAllBookingResponse = BookingAPI.uses(UserScope.DEVELOPER).toGetAllBookingsPresent();
    // Assert
    BookingAssert.verify(getAllBookingResponse)
        .statusCodeIs(SC_OK)
        .matchesSchema(GET_ALL_BOOKING_SCHEMA.getFilePath())
        .assertAll();
  }

  @Test
  void assertThatUserCanGetSingleBooking() {
    // Act
    Response getSingleBookingResponse =
        BookingAPI.uses(UserScope.DEVELOPER).toGetBookingPresent(bookingId);
    // Assert
    BookingAssert.verify(getSingleBookingResponse)
        .statusCodeIs(SC_OK)
        .matchesSchema(BOOKING_SCHEMA.getFilePath())
        .assertAll();
  }

  @ParameterizedTest
  @ArgumentsSource(BookingArgumentsProvider.class)
  void assertThatUserCanPartiallyUpdateExistingBooking(final Booking partialUpdatedBookingRequest) {
    // Act
    Response partialUpdatedBookingResponse =
        BookingAPI.uses(UserScope.ADMIN)
            .toPartiallyUpdateBookingPresent(partialUpdatedBookingRequest, bookingId);
    // Assert
    BookingAssert.verify(partialUpdatedBookingResponse)
        .statusCodeIs(SC_OK)
        .hasBooking(partialUpdatedBookingRequest)
        .matchesSchema(BOOKING_SCHEMA.getFilePath())
        .assertAll();
  }

  @AfterEach
  public void tearDown() {
    // Act
    Response deletedBookingResponse =
        BookingAPI.uses(UserScope.ADMIN).toDeleteBookingPresent(bookingId);
    // Assert
    BookingAssert.verify(deletedBookingResponse).statusCodeIs(SC_CREATED);
  }
}
