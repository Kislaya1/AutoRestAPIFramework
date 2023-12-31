package com.auto.rest.api.apiBuilder;

import com.auto.rest.api.apiBuilder.apiMethodFactory.ApiMethodType;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static com.auto.rest.api.apiBuilder.apiMethodFactory.ApiMethodFactory.apiMethodFactory;

public final class ApiBuilder {
  private final RequestSpecBuilder requestSpecBuilder;

  public ApiBuilder() {
    this.requestSpecBuilder = new RequestSpecBuilder();
  }

  public ApiBuilder baseUri(final String uri) {
    requestSpecBuilder.setBaseUri(uri);
    return this;
  }

  public ApiBuilder headers(final Map<String, String> headers) {
    requestSpecBuilder.addHeaders(headers);
    return this;
  }

  public ApiBuilder body(final Object requestBody) {
    requestSpecBuilder.setBody(requestBody);
    return this;
  }

  public Response fetchResponse(final ApiMethodType apiMethodType, final String endPoint) {
    return apiMethodFactory().get(apiMethodType).response(createRequestSpec(), endPoint);
  }

  private RequestSpecification createRequestSpec() {
    return RestAssured.given().spec(this.requestSpecBuilder.build()).log().all();
  }
}
