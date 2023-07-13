package com.auto.rest.api.authorization.scopeFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

public final class ScopeFactory {
  private static final Map<UserScope, Supplier<IAuthScope>> map = new HashMap<>();

  static {
    map.put(UserScope.ADMIN, IAuthScope::admin);
    map.put(UserScope.DEVELOPER, IAuthScope::developer);
  }

  private ScopeFactory() {}

  public static ScopeFactory scopeFactory() {
    return new ScopeFactory();
  }

  public IAuthScope get(final UserScope userScope) {
    Supplier<IAuthScope> authScope = map.get(userScope);
    if (Objects.isNull(authScope))
      throw new IllegalArgumentException("No such user scope is present : " + userScope);
    return authScope.get();
  }
}
