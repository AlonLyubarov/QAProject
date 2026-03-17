package org.example.tests;

import org.example.pages.LoginPage;
import org.example.utils.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;
    String URL = "https://practice.qabrains.com/ecommerce/login";
    @BeforeClass
    public void setUp() {
        // Initialize the driver
        driver = WebDriverManager.getDriver();

        // Open the login page
        driver.get(URL);

        // Initialize the LoginPage object
        loginPage = new LoginPage(driver);
    }

    // ------------------------
    // Positive Test
    // ------------------------
    @Test(priority = 2)
    public void testValidLogin() {
        String email = "test@qabrains.com";
        String password = "Password123";

        loginPage.login(email, password);

        // Assert login success
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login should succeed with valid credentials");
    }

    // ------------------------
    // Negative Test
    // ------------------------
    @Test(priority = 1)
    public void testInvalidLogin() {
        String invalidEmail = "wrong@qabrains.com";
        String invalidPassword = "WrongPass123";

        loginPage.login(invalidEmail, invalidPassword);

        // Assert login failed
        Assert.assertFalse(loginPage.isLoginSuccessful(), "Login should fail with invalid credentials");
    }


    // ------------------------
    // Reset page after each test
    // ------------------------
    @AfterMethod
    public void afterEachTest() {
        driver.get(URL); // reset page for next test
    }

    // ------------------------
    // Close driver after all tests
    // ------------------------
    @AfterClass
    public void tearDown() {
        WebDriverManager.quitDriver();
    }
}