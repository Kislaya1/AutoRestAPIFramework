package com.auto.rest.api.enums;

public enum DatePatternTypes {
    yyyy_MM_dd("yyyy-MM-dd");

    private String dateType;

    DatePatternTypes(final String dateType) {
        this.dateType = dateType;
    }

    public String getDateType() {
        return this.dateType;
    }
}
