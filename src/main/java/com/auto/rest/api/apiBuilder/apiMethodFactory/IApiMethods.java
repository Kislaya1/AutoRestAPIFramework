package com.auto.rest.api.apiBuilder.apiMethodFactory;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public interface IApiMethods {
  static IApiMethods getMethod() {
    return (requestSpecification, endPoint) ->
        requestSpecification.get(endPoint).then().log().all().extract().response();
  }

  static IApiMethods postMethod() {
    return (requestSpecification, endPoint) ->
        requestSpecification.post(endPoint).then().log().all().extract().response();
  }

  static IApiMethods putMethod() {
    return (requestSpecification, endPoint) ->
        requestSpecification.put(endPoint).then().log().all().extract().response();
  }

  static IApiMethods patchMethod() {
    return (requestSpecification, endPoint) ->
        requestSpecification.patch(endPoint).then().log().all().extract().response();
  }

  static IApiMethods deleteMethod() {
    return (requestSpecification, endPoint) ->
        requestSpecification.delete(endPoint).then().log().all().extract().response();
  }

  Response response(final RequestSpecification requestSpecification, final String endPoint);
}
