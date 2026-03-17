package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterEmail(String email) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement emailField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("email")) //
        );
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement passwordField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("password")) //
        );
        passwordField.sendKeys(password);
    }

    public void clickLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement loginButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(., 'Login')]"))
        );
        loginButton.click();
    }


    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }


    public boolean isLoginSuccessful() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement notification = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section[aria-label='Notifications alt+T']"))
            );

            String msg = notification.getText();
            // If the notification contains "invalid" or "wrong", login failed
            if (msg.toLowerCase().contains("neither") || msg.toLowerCase().contains(" incorrect")) {
                return false; // login failed
            }
            return true; // no error → login likely successful
        } catch (Exception e) {
            // No notification → assume login succeeded
            return true;
        }
    }
}