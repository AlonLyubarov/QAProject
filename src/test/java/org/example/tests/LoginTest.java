package org.example.tests;

import org.example.data.JsonDataReader;
import org.example.data.LoginData;
import org.example.pages.LoginPage;
import org.example.utils.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private final String URL = "https://practice.qabrains.com/ecommerce/login";

    @BeforeClass
    public void setUp() {
        // Initialize the driver
        driver = WebDriverManager.getDriver();

        // Open the login page
        driver.get(URL);

        // Initialize LoginPage object
        loginPage = new LoginPage(driver);
    }

    // ------------------------
    // DataProvider that reads JSON
    // ------------------------
    @DataProvider(name = "loginData")
    public Object[][] loginDataProvider() throws Exception {

        List<LoginData> dataList = JsonDataReader.getLoginData();
        Object[][] data = new Object[dataList.size()][3];

        for (int i = 0; i < dataList.size(); i++) {
            LoginData d = dataList.get(i);
            data[i][0] = d.email;
            data[i][1] = d.password;
            data[i][2] = d.expected;
        }

        return data;
    }

    // ------------------------
    // Single test for all login scenarios
    // ------------------------
    @Test(dataProvider = "loginData")
    public void testLogin(String email, String password, boolean expected) {

        loginPage.login(email, password);

        boolean actual = loginPage.isLoginSuccessful();

        Assert.assertEquals(actual, expected, "Login result mismatch for: " + email);
    }

    // ------------------------
    // Reset page after each test
    // ------------------------
    @AfterMethod
    public void resetDriver() {
        WebDriverManager.quitDriver();         // close browser
        driver = WebDriverManager.getDriver(); // open new browser
        driver.get(URL);
        loginPage = new LoginPage(driver);
    }
    // ------------------------
    // Quit driver after all tests
    // ------------------------
    @AfterClass
    public void tearDown() {
        WebDriverManager.quitDriver();
    }
}