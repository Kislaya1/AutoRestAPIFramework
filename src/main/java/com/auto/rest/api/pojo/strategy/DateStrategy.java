package com.auto.rest.api.pojo.strategy;

import com.auto.rest.api.enums.DatePatternTypes;
import com.auto.rest.api.utils.IRandomDateUtils;
import uk.co.jemos.podam.common.AttributeStrategy;

import java.lang.annotation.Annotation;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Supplier;

public class DateStrategy implements AttributeStrategy<String> {

  @Override
  public String getValue(Class<?> attrType, List<Annotation> attrAnnotations) {
    final Supplier<DateTimeFormatter> dateTimeFormatter =
        () -> DateTimeFormatter.ofPattern(DatePatternTypes.yyyy_MM_dd.getDateType());
    return IRandomDateUtils.pastSixMonthsDate().withDateFormatter(dateTimeFormatter.get());
  }
}
