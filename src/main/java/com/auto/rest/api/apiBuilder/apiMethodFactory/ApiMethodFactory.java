package com.auto.rest.api.apiBuilder.apiMethodFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

import static com.auto.rest.api.apiBuilder.apiMethodFactory.ApiMethodType.*;


public final class ApiMethodFactory {
    private final static Map<ApiMethodType, Supplier<IApiMethods>> map = new HashMap<>();

    static {
        map.put(GET, IApiMethods::getMethod);
        map.put(POST, IApiMethods::postMethod);
        map.put(PUT, IApiMethods::putMethod);
        map.put(PATCH, IApiMethods::patchMethod);
        map.put(DELETE, IApiMethods::deleteMethod);
    }

    public ApiMethodFactory() {
    }

    public IApiMethods fetchFactoryMethod(final ApiMethodType apiMethodType) {
        Supplier<IApiMethods> apiMethods = map.get(apiMethodType);
        if (Objects.isNull(apiMethods))
            throw new IllegalArgumentException("No such api method is available " + apiMethodType);
        return apiMethods.get();
    }
}
