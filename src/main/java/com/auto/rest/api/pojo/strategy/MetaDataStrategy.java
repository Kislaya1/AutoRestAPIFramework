package com.auto.rest.api.pojo.strategy;

import uk.co.jemos.podam.common.AttributeStrategy;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.stream.Stream;

public class MetaDataStrategy implements AttributeStrategy<String> {
    @Override
    public String getValue(Class<?> attrType, List<Annotation> attrAnnotations) {
        return Stream.of("Writing", "Blogging", "Learning Languages", "Photography", "Sports", "Travel", "Reading", "Making Music", "Art", "Dance")
                .findAny()
                .orElseThrow();
    }
}
