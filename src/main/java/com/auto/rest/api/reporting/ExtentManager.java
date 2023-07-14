package com.auto.rest.api.reporting;

import com.aventstack.extentreports.ExtentTest;

public final class ExtentManager {
  private static final ThreadLocal<ExtentTest> exTest = new ThreadLocal<>();

  private ExtentManager() {}

  static synchronized ExtentTest getTest() {
    return exTest.get();
  }

  static synchronized void setTest(ExtentTest test) {
    exTest.set(test);
  }

  static synchronized void removeTestThread() {
    exTest.remove();
  }
}