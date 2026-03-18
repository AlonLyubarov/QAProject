package org.example.pages;

import org.example.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Objects;

public class LoginPage extends BasePage {

    // ------------------------
    // Locators
    // ------------------------
    private final By emailInput = By.id("email");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.xpath("//button[text()='Login']");

    // Error / notification message
    private final By notification = By.cssSelector("section[aria-label='Notifications alt+T']");


    // ------------------------
    // Constructor
    // ------------------------
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // ------------------------
    // Perform login
    // ------------------------
    public void login(String email, String password) {
        sendKeys(emailInput, email);
        sendKeys(passwordInput, password);
        clickElement(loginButton);
    }

    // ------------------------
    // Check login result (PROFESSIONAL WAY)
    // ------------------------
    public boolean isLoginSuccessful() {

        try {
            String msg = getNotificationText(notification).toLowerCase();
            if (msg.contains("invalid") ||
                    msg.contains("incorrect") ||
                    msg.contains("wrong") ||
                    msg.contains("neither")) {

                return false;
            }
            return true; // no error → login likely successful
        } catch (Exception e) {
            // No notification → assume login succeeded
            return true;
        }
    }
}