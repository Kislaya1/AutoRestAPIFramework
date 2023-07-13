package com.auto.rest.api.apiBuilder.apiMethodFactory;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

import static com.auto.rest.api.apiBuilder.apiMethodFactory.ApiMethodType.*;

public final class ApiMethodFactory {
  private static final Map<ApiMethodType, Supplier<IApiMethods>> map =
      new EnumMap<>(ApiMethodType.class);

  static {
    map.put(GET, IApiMethods::getMethod);
    map.put(POST, IApiMethods::postMethod);
    map.put(PUT, IApiMethods::putMethod);
    map.put(PATCH, IApiMethods::patchMethod);
    map.put(DELETE, IApiMethods::deleteMethod);
  }

  private ApiMethodFactory() {}

  public static ApiMethodFactory apiMethodFactory() {
    return new ApiMethodFactory();
  }

  public IApiMethods get(final ApiMethodType apiMethodType) {
    Supplier<IApiMethods> apiMethods = map.get(apiMethodType);
    if (Objects.isNull(apiMethods))
      throw new IllegalArgumentException("No such api method is available " + apiMethodType);
    return apiMethods.get();
  }
}
