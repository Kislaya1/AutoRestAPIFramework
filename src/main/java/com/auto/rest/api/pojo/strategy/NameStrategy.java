package com.auto.rest.api.pojo.strategy;

import com.github.javafaker.Faker;
import uk.co.jemos.podam.common.AttributeStrategy;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.stream.Stream;

public class NameStrategy implements AttributeStrategy<String> {
    @Override
    public String getValue(Class<?> aClass, List<Annotation> list) {
        Faker faker = new Faker();
        return Stream.of(faker.name().fullName().split("\\s+"))
                .findAny()
                .orElseThrow();
    }
}
