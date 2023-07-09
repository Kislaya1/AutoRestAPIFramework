package com.rest.api.pro.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import lombok.NonNull;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.File;

import static com.rest.api.pro.enums.ReportingLocation.REPORT_FOLDER;

public final class ExtentReport {
    private static ExtentReports extentReports;

    private ExtentReport() {
        createDir();
    }

    public static ExtentReport report() {
        return new ExtentReport();
    }

    public void init(ExtensionContext context) {
        String reportFile = REPORT_FOLDER.get() + context.getDisplayName() + "_Report.html";
        ExtentHtmlReporter sparkReporter = new ExtentHtmlReporter(reportFile);
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
    }

    public void createTest(@NonNull final String testName) {
        ExtentTest test = extentReports.createTest(testName);
        ExtentManager.manage().setTest(test);
    }

    public void tearDownReport() {
        extentReports.flush();
        ExtentManager.manage().removeTestThread();
    }

    private void createDir() {
        final String folderPath = REPORT_FOLDER.get();
        File reportDir = new File(folderPath);
        if (!reportDir.exists() && !reportDir.isDirectory()) reportDir.mkdir();
    }
}
