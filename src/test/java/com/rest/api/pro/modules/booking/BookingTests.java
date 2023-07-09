package com.rest.api.pro.modules.booking;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.rest.api.pro.argumentProviders.BookingArgumentsProvider;
import com.rest.api.pro.config.production.IProdEnvConfig;
import com.rest.api.pro.controller.booking.BookingAPI;
import com.rest.api.pro.pojo.booking.Booking;
import com.rest.api.pro.pojo.booking.BookingData;
import com.rest.api.pro.reporting.FrameworkListener;
import com.rest.api.pro.utils.DataMockUtils;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static com.rest.api.pro.authorization.scopeFactory.UserScope.ADMIN;
import static com.rest.api.pro.authorization.scopeFactory.UserScope.DEVELOPER;
import static com.rest.api.pro.enums.CommonLocations.TEMPLATES_LOCATION;
import static com.rest.api.pro.enums.SchemaFileNames.*;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;

/*
 *                      To Do Checklist
 * Add more test cases under Booking Test File
 * Get the know about what is "SELF_TYPE" for interview question.
 * Learn about Junit 5 for interview.
 * Read about Slf4j.
 * -------------------------------------------------------------------------------
 * Done
 * Logger and Reporting for Framework -- Done
 * Run the test cases in Parallel - Done
 * Scope for Auth API (Scope Agnostic) - Done
 * uses <some> pattern to fetch the token so that it is accessed only when needed and create scope for auth token. - Singleton
 * Check Amuthan video on pojo fixture to create diff Pojo's - Done
 * Check for Dates under Date Strategy or ArgumentProvider. - Done
 * Fix BookingTemplates - Done
 * Schema Validations. (Do this during assertion)  -- Done
 * Avoid using object mapper and uses Response and during assertion only uses object mappers. -- Done
 * Check the way to uses generic Pojo's -- Done
 * Remove Object Mapper dependencies and instead uses response.as(XYZ.class) -- Done
 * Under Assertion Msg print the actual and expected values also. -- Done
 * */

@ExtendWith(FrameworkListener.class)
public class BookingTests {
    private final IProdEnvConfig config = ConfigFactory.create(IProdEnvConfig.class);
    private String bookingId;

    @BeforeAll
    public static void setUpClass() {
        FixtureFactoryLoader.loadTemplates(TEMPLATES_LOCATION.get());
    }

    @BeforeEach
    public void setUp() {
        Booking bookingBody = DataMockUtils.fetchBookingRequestPojo().mockData();
        // Act
        Response createdBookingResponse = BookingAPI.uses(ADMIN).toCreateNewBooking(bookingBody);
        // Fetch Booking ID
        bookingId = createdBookingResponse.as(BookingData.class).getBookingId();
        // Assert
        BookingAssert.verify(createdBookingResponse).statusCodeIs(SC_OK)
                .hasBookingId(bookingId)
                .hasNewBookingCreated(bookingBody)
                .matchesSchema(config.BOOKING_SCHEMA_DIR(BOOKING_DATA_SCHEMA.getFilePath()))
                .assertAll();
    }

    @ParameterizedTest
    @ArgumentsSource(BookingArgumentsProvider.class)
    void assertThatUsersCanUpdateExistingBooking(final Booking updatedBookingRequest) {
        // Act
        Response updateNewBookingResponse = BookingAPI.uses(ADMIN).toUpdateBookingPresent(updatedBookingRequest, bookingId);
        // Assert
        BookingAssert.verify(updateNewBookingResponse).statusCodeIs(SC_OK)
                .hasBooking(updatedBookingRequest)
                .matchesSchema(config.BOOKING_SCHEMA_DIR(BOOKING_SCHEMA.getFilePath()))
                .assertAll();
    }

    @Test
    void assertThatUserCanGetAllBookings() {
        //Act
        Response getAllBookingResponse = BookingAPI.uses(DEVELOPER).toGetAllBookingsPresent();

        //Assert
        BookingAssert.verify(getAllBookingResponse).statusCodeIs(SC_OK)
                .matchesSchema(config.BOOKING_SCHEMA_DIR(GET_ALL_BOOKING_SCHEMA.getFilePath()))
                .assertAll();
    }

    @Test
    void assertThatUserCanGetSingleBooking() {
        //Act
        Response getSingleBookingResponse = BookingAPI.uses(DEVELOPER).toGetBookingPresent(bookingId);

        //Assert
        BookingAssert.verify(getSingleBookingResponse).statusCodeIs(SC_OK)
                .matchesSchema(config.BOOKING_SCHEMA_DIR(BOOKING_SCHEMA.getFilePath()))
                .assertAll();
    }

    @ParameterizedTest
    @ArgumentsSource(BookingArgumentsProvider.class)
    void assertThatUserCanPartiallyUpdateExistingBooking(final Booking partialUpdatedBookingRequest) {
        //Act
        Response partialUpdatedBookingResponse = BookingAPI.uses(ADMIN).toPartiallyUpdateBookingPresent(partialUpdatedBookingRequest, bookingId);

        //Assert
        BookingAssert.verify(partialUpdatedBookingResponse).statusCodeIs(SC_OK)
                .hasBooking(partialUpdatedBookingRequest)
                .matchesSchema(config.BOOKING_SCHEMA_DIR(BOOKING_SCHEMA.getFilePath()))
                .assertAll();
    }


    @AfterEach
    public void tearDown() {
        // Act
        Response deletedBookingResponse = BookingAPI.uses(ADMIN).toDeleteBookingPresent(bookingId);
        // Assert
        BookingAssert.verify(deletedBookingResponse).statusCodeIs(SC_CREATED);
    }
}
