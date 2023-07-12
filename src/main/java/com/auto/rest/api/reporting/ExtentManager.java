package com.auto.rest.api.reporting;

import com.aventstack.extentreports.ExtentTest;

public final class ExtentManager {
  private static ThreadLocal<ExtentTest> exTest = new ThreadLocal<>();

  private ExtentManager() {}

  public static synchronized ExtentManager manage() {
    return new ExtentManager();
  }

  ExtentTest getTest() {
    return exTest.get();
  }

  void setTest(ExtentTest test) {
    exTest.set(test);
  }

  void removeTestThread() {
    exTest.remove();
  }
}
