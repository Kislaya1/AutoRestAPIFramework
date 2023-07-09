package com.rest.api.pro.authorization;

import com.rest.api.pro.apiBuilder.ImmutableIHeader;

import java.util.Map;

import static com.rest.api.pro.enums.ApiContentType.JSON;

public interface IHeaderUtils {

    static IHeaderUtils withBasicHeaders() {
        return () -> basicHeaders().createHeader().fetchHeader();
    }

    static IHeaderUtils withCookieHeader(final String cookieId) {
        return () -> headersWithCookie(cookieId).createHeader().fetchHeader();
    }

    private static ImmutableIHeader.Builder basicHeaders() {
        return ImmutableIHeader.builder().requestContentType(JSON.getContentType()).responseContentType(JSON.getContentType());
    }

    private static ImmutableIHeader.Builder headersWithCookie(final String tokenId) {
        return basicHeaders().cookie("token=" + tokenId);
    }

    Map fetch();
}
