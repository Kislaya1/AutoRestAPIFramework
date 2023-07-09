package com.rest.api.pro.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.rest.api.pro.pojo.booking.Booking;
import com.rest.api.pro.pojo.booking.entity.BookingDates;
import com.rest.api.pro.utils.IRandomDateUtils;

import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;

import static com.rest.api.pro.enums.DatePatternTypes.yyyy_MM_dd;

public class BookingTemplates implements TemplateLoader {
    @Override
    public void load() {
        final Supplier<DateTimeFormatter> dateTimeFormatter = () -> DateTimeFormatter.ofPattern(yyyy_MM_dd.getDateType());

        Fixture.of(BookingDates.class).addTemplate("valid", new Rule() {{
            add("checkIn", IRandomDateUtils.pastSixMonthsDate().withDateFormatter(dateTimeFormatter.get()));
            add("checkOut", IRandomDateUtils.currentDate().withDateFormatter(dateTimeFormatter.get()));
        }});

        Fixture.of(Booking.class).addTemplate("valid", new Rule() {{
            add("firstName", firstName());
            add("lastName", lastName());
            add("totalPrice", random(Long.class, range(100L, 10_000L)));
            add("depositPaid", random(true, false));
            add("bookingDates", one(BookingDates.class, "valid"));
            add("additionalNeeds", uniqueRandom("Writing", "Blogging", "Learning Languages", "Photography", "Sports", "Travel", "Reading", "Creating Music", "Art", "Dance"));
        }});
    }
}
