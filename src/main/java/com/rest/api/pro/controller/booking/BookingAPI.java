package com.rest.api.pro.controller.booking;

import com.rest.api.pro.apiBuilder.ApiBuilder;
import com.rest.api.pro.authorization.scopeFactory.ScopeFactory;
import com.rest.api.pro.authorization.scopeFactory.UserScope;
import com.rest.api.pro.config.production.IProdEnvConfig;
import com.rest.api.pro.pojo.booking.Booking;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;

import java.util.function.Supplier;

import static com.rest.api.pro.apiBuilder.apiMethodFactory.ApiMethodType.*;

@SuppressWarnings("unchecked")
public class BookingAPI {
    private static final IProdEnvConfig config = ConfigFactory.create(IProdEnvConfig.class);
    private static final String baseUri = config.BASE_URL();
    private final Supplier<ScopeFactory> scopeFactory;
    private final UserScope scope;

    private BookingAPI(final UserScope scope) {
        this.scope = scope;
        this.scopeFactory = ScopeFactory::new;
    }

    public static BookingAPI uses(final UserScope scope) {
        return new BookingAPI(scope);
    }

    public Response toGetAllBookingsPresent() {
        return new ApiBuilder().baseUri(baseUri).headers(scopeFactory.get().user(scope).scope()).fetchResponse(GET, config.BOOKING_ENDPOINT());
    }

    public Response toGetBookingPresent(final String bookingId) {
        return new ApiBuilder().baseUri(baseUri).headers(scopeFactory.get().user(scope).scope()).fetchResponse(GET, config.GET_ALL_BOOKING_ENDPOINT(bookingId));
    }

    public Response toCreateNewBooking(final Booking bookingBody) {
        return new ApiBuilder().baseUri(baseUri).headers(scopeFactory.get().user(scope).scope()).body(bookingBody).fetchResponse(POST, config.BOOKING_ENDPOINT());
    }

    public Response toUpdateBookingPresent(final Booking updatedBooking, final String bookingId) {
        return new ApiBuilder().baseUri(baseUri).headers(scopeFactory.get().user(scope).scope()).body(updatedBooking).fetchResponse(PUT, config.MODIFY_BOOKING_ENDPOINT(bookingId));
    }

    public Response toPartiallyUpdateBookingPresent(final Booking partialUpdatedBooking, final String bookingId) {
        return new ApiBuilder().baseUri(baseUri).headers(scopeFactory.get().user(scope).scope()).body(partialUpdatedBooking).fetchResponse(PATCH, config.MODIFY_BOOKING_ENDPOINT(bookingId));
    }

    public Response toDeleteBookingPresent(final String bookingId) {
        return new ApiBuilder().baseUri(baseUri).headers(scopeFactory.get().user(scope).scope()).fetchResponse(DELETE, config.DELETE_BOOKING_ENDPOINT(bookingId));
    }
}
