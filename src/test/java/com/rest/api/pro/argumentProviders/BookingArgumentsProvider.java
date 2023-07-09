package com.rest.api.pro.argumentProviders;

import br.com.six2six.fixturefactory.Fixture;
import com.rest.api.pro.pojo.booking.Booking;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.List;
import java.util.stream.Stream;

public class BookingArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        List<Booking> validBookingList = Fixture.from(Booking.class).gimme(2, "valid");
        return validBookingList.stream().map(Arguments::of);
    }
}
