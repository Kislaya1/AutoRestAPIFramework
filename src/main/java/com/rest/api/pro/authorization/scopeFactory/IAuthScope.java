package com.rest.api.pro.authorization.scopeFactory;

import com.rest.api.pro.authorization.TokenAPI;

import java.util.Map;

import static com.rest.api.pro.authorization.IHeaderUtils.withBasicHeaders;
import static com.rest.api.pro.authorization.IHeaderUtils.withCookieHeader;

public interface IAuthScope {

    static IAuthScope admin() {
        return () -> withCookieHeader(TokenAPI.getInstance()).fetch();
    }

    static IAuthScope developer() {
        return () -> withBasicHeaders().fetch();
    }

    Map scope();
}
