package com.masteringselenium;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DriverFactory {

    private static List<WebDriverThread> webDriverThreadPool =
            Collections.synchronizedList(new ArrayList<WebDriverThread>());

    private static ThreadLocal<WebDriverThread> driverThread;

    @BeforeMethod
    public static void instantiateDriverObject() {
        driverThread = new ThreadLocal<WebDriverThread>() {
            @Override
            protected WebDriverThread initialValue() {
                WebDriverThread webDriverThread = new WebDriverThread();
                webDriverThreadPool.add(webDriverThread);
                return webDriverThread;
            }
        };
    }

    public static WebDriver getDriver() throws Exception {
        return driverThread.get().getDriver();
    }

    @AfterMethod
    public static void quitDriver() throws Exception {
        getDriver().manage().deleteAllCookies();
        driverThread.get().quitDriver();
    }

    @AfterSuite
    public static void closeDriverObjects() {

        for (WebDriverThread webDriverThread : webDriverThreadPool)
            webDriverThread.quitDriver();
    }
}
