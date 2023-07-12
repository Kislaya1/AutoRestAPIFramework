package com.auto.rest.api.asserts;

import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;

import java.io.File;

import static com.auto.rest.api.asserts.ErrorMsg.SCHEMA_NOT_MATCHING;
import static com.auto.rest.api.asserts.ErrorMsg.STATUS_CODE_NOT_MATCHING;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public abstract class BaseAssert<SELF_TYPE extends BaseAssert<?>> {
    protected final SELF_TYPE selfType;
    protected Response response;
    protected SoftAssertions softAssertions;

    protected BaseAssert(Class<SELF_TYPE> selfType, Response response) {
        this.selfType = selfType.cast(this);
        this.response = response;
        this.softAssertions = new SoftAssertions();
    }

    public SELF_TYPE statusCodeIs(final int statusCode) {
        assertThat(response.getStatusCode())
                .withFailMessage(STATUS_CODE_NOT_MATCHING(response.statusCode(), statusCode)::getMsg)
                .isEqualTo(statusCode);
        return selfType;
    }

    public SELF_TYPE matchesSchema(String fileClassPath) {
        softAssertions
                .assertThat(response.then().body(matchesJsonSchema(new File(fileClassPath))))
                .withFailMessage(SCHEMA_NOT_MATCHING(fileClassPath)::getMsg)
                .getWritableAssertionInfo();

        return selfType;
    }

    public void assertAll() {
        softAssertions.assertAll();
    }
}
