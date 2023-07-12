package com.auto.rest.api.reporting;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.restassured.http.Header;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

public final class ExtentLogger {

  private ExtentLogger() {}

  public static ExtentLogger log() {
    return new ExtentLogger();
  }

  public void logResponse(final String response) {
    ExtentManager.manage().getTest().info("Response Body details below");
    ExtentManager.manage().getTest().info(MarkupHelper.createCodeBlock(response));
  }

  public void logInfo(final String message) {
    ExtentManager.manage().getTest().info(message);
  }

  public void pass(final String logMessage) {
    ExtentManager.manage().getTest().pass(MarkupHelper.createLabel(logMessage, ExtentColor.GREEN));
  }

  public void fail(final String logMessage) {
    ExtentManager.manage().getTest().fail(MarkupHelper.createLabel(logMessage, ExtentColor.RED));
  }

  public void skip(final String logMessage) {
    ExtentManager.manage().getTest().skip(MarkupHelper.createLabel(logMessage, ExtentColor.GREY));
  }

  public void logRequest(final RequestSpecification requestSpecification) {
    QueryableRequestSpecification query = SpecificationQuerier.query(requestSpecification);
    ExtentManager.manage().getTest().info("Request Headers details below");
    for (Header header : query.getHeaders())
      ExtentManager.manage()
          .getTest()
          .info(MarkupHelper.createCodeBlock(header.getName() + " : " + header.getValue()));
    ExtentManager.manage().getTest().info("Request Body details below");
    ExtentManager.manage().getTest().info(MarkupHelper.createCodeBlock(query.getBody()));
  }
}
