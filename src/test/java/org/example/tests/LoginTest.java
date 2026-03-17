package org.example.tests;

import org.example.pages.LoginPage;
import org.example.utils.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        driver = WebDriverManager.getDriver();
        driver.get("https://practice.qabrains.com/ecommerce/login"); // אתר ה-QA Brains
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testValidLogin() {
        loginPage.login("test@qabrains.com", "Password123");
        // כאן אפשר להוסיף assert לבדוק שהלוגין הצליח
    }

    @AfterClass
    public void tearDown() {
        WebDriverManager.quitDriver();
    }
}