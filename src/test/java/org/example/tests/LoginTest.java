package org.example.tests;

import io.qameta.allure.*;
import org.example.data.JsonDataReader;
import org.example.data.LoginData;
import org.example.pages.LoginPage;
import org.example.utils.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.List;

@Epic("Authentication")        // ← top level group
@Feature("Login")              // ← feature inside the epic
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
    @Story("Login with various credentials")       // ← story inside feature
    @Severity(SeverityLevel.CRITICAL)              // ← how important is this test
    @Description("Tests login with valid and invalid credentials from JSON")  // ← description
    public void testLogin(String email, String password, boolean expected) {
        Allure.parameter("Email", email);                    // ← shows email in report
        Allure.parameter("Expected Result", expected);       // ← shows expected in report


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