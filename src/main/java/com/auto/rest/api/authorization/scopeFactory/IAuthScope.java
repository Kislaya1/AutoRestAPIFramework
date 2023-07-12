package com.auto.rest.api.authorization.scopeFactory;

import com.auto.rest.api.authorization.TokenAPI;

import java.util.Map;

import static com.auto.rest.api.authorization.IHeaderUtils.withBasicHeaders;
import static com.auto.rest.api.authorization.IHeaderUtils.withCookieHeader;

public interface IAuthScope {

    static IAuthScope admin() {
        return () -> withCookieHeader(TokenAPI.getInstance()).fetch();
    }

    static IAuthScope developer() {
        return () -> withBasicHeaders().fetch();
    }

    Map scope();
}
