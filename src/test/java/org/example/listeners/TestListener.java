// src/test/java/org/example/listeners/TestListener.java
package org.example.listeners;

import io.qameta.allure.Allure;
import org.example.utils.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.ByteArrayInputStream;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = WebDriverManager.getDriver();

        if (driver instanceof TakesScreenshot) {
            byte[] screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);

            //  Attaches screenshot directly into the Allure report
            Allure.addAttachment(
                    "Screenshot - " + result.getName(),
                    "image/png",
                    new ByteArrayInputStream(screenshot),
                    "png"
            );
        }
    }
}