package com.auto.rest.api.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public interface IRandomDateUtils {
  static IRandomDateUtils pastSixMonthsDate() {
    final Supplier<LocalDate> startDate = () -> LocalDate.now().minusMonths(6);
    final Supplier<LocalDate> endDate = LocalDate::now;

    return (dateTimeFormatter) ->
        startDate
            .get()
            .datesUntil(endDate.get())
            .map(localDate -> localDate.format(dateTimeFormatter))
            .collect(Collectors.toList())
            .stream()
            .findAny()
            .orElseThrow();
  }

  static IRandomDateUtils currentDate() {
    final Supplier<LocalDate> dateSupplier = LocalDate::now;

    return (dateTimeFormatter) -> dateSupplier.get().format(dateTimeFormatter);
  }

  String withDateFormatter(final DateTimeFormatter dateTimeFormatter);
}
