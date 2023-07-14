package com.auto.rest.api.reporting;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

public final class ExtentLogger {
  private ExtentLogger() {}

  public static void logResponse(final Response response) {
    ExtentManager.getTest().info(MarkupHelper.createCodeBlock(response.asString()));
  }

  public static void logInfo(final String message) {
    ExtentManager.getTest().info(message);
  }

  public static void pass(final String logMessage) {
    ExtentManager.getTest().pass(MarkupHelper.createLabel(logMessage, ExtentColor.GREEN));
  }

  public static void fail(final String logMessage) {
    ExtentManager.getTest().fail(MarkupHelper.createLabel(logMessage, ExtentColor.RED));
  }

  public static void skip(final String logMessage) {
    ExtentManager.getTest().skip(MarkupHelper.createLabel(logMessage, ExtentColor.GREY));
  }

  public static void logRequest(final RequestSpecification requestSpecification) {
    QueryableRequestSpecification query = SpecificationQuerier.query(requestSpecification);
    ExtentManager.getTest().info("Request Body details below");
    ExtentManager.getTest().info(MarkupHelper.createCodeBlock(query.getBody()));
    ExtentManager.getTest().info("Request Headers details below");
    for (Header header : query.getHeaders())
      ExtentManager.getTest()
              .info(MarkupHelper.createCodeBlock(header.getName() + " : " + header.getValue()));
  }
}