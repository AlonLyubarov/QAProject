package org.example.tests;

import org.example.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NegativeLoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testInvalidLogin() {
        driver.get("https://qa-brains-practice-site.com/login");
        loginPage.enterEmail("invalid-email@example.com");
        loginPage.enterPassword("wrong-password");
        loginPage.clickLogin();

        // כאן ניתן להוסיף assert לבדיקה שהלוגין נכשל
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}