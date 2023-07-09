package com.rest.api.pro.pojo.strategy;

import com.rest.api.pro.utils.IRandomDateUtils;
import uk.co.jemos.podam.common.AttributeStrategy;

import java.lang.annotation.Annotation;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Supplier;

import static com.rest.api.pro.enums.DatePatternTypes.yyyy_MM_dd;

public class DateStrategy implements AttributeStrategy<String> {

    @Override
    public String getValue(Class<?> attrType, List<Annotation> attrAnnotations) {
        final Supplier<DateTimeFormatter> dateTimeFormatter = () -> DateTimeFormatter.ofPattern(yyyy_MM_dd.getDateType());
        return IRandomDateUtils.pastSixMonthsDate().withDateFormatter(dateTimeFormatter.get());
    }
}
