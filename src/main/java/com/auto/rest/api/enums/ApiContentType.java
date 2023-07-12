package com.auto.rest.api.enums;

public enum ApiContentType {
    JSON("application/json"),
    XML("application/xml");
    private final String contentType;

    ApiContentType(final String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }
}
