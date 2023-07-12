package com.auto.rest.api.argumentProviders;

import br.com.six2six.fixturefactory.Fixture;
import com.auto.rest.api.pojo.booking.Booking;
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
