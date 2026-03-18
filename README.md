# QA Automation Project 

A Selenium-based test automation framework built with Java, TestNG, and Allure reporting.
This project is a work in progress and will be expanded with more pages and test cases over time.

---

##  Tech Stack

| Tool | Purpose |
|---|---|
| Java 21 | Programming language |
| Selenium 4.25.0 | Browser automation |
| TestNG 7.8.0 | Test framework |
| Maven | Build and dependency management |
| Jackson | JSON parsing for test data |
| Allure | Test reporting |
| AspectJ | Required for Allure @Step to work |
| Apache POI | Excel support (future use) |
| Commons IO | File utilities for screenshots |

---

##  Project Structure
```
src/
├── main/java/org/example/
│   ├── base/
│   │   └── BasePage.java          # Base class for all page objects
│   ├── pages/
│   │   └── LoginPage.java         # Login page actions and validations
│   └── utils/
│       ├── WebDriverManager.java  # Manages Chrome WebDriver lifecycle
│       └── ConfigReader.java      # Reads config.properties file
│
└── test/java/org/example/
    ├── data/
    │   ├── LoginData.java         # Data model for login test data
    │   └── JsonDataReader.java    # Reads test data from JSON files
    ├── listeners/
    │   └── TestListener.java      # Screenshots on test failure
    └── tests/
        └── LoginTest.java         # Login test cases

src/test/resources/
├── loginData.json                 # Test data for login scenarios
└── config.properties              # URLs and credentials configuration
```

---

##  What's Done So Far

### Framework Setup
- Maven project structure with all dependencies configured
- Java 21 with AspectJ for Allure @Step support
- TestNG as the test runner with testng.xml suite configuration
- config.properties for externalized URLs and credentials
- ConfigReader.java to read configuration without hardcoding values

### Base Page
- BasePage.java — reusable methods for all page objects
- Explicit waits using WebDriverWait
- Methods: clickElement(), sendKeys(), isElementDisplayed(), navigateTo(), getNotificationText()

### WebDriver Management
- WebDriverManager.java — centralized Chrome WebDriver setup
- Singleton pattern — one driver instance at a time
- getDriver() creates driver if not exists
- quitDriver() closes browser and resets instance

### Page Objects
- LoginPage.java — handles all login page interactions
- Email input, password input, login button locators
- login() method to perform login action
- isLoginSuccessful() method to verify login result via notification messages
- @Step annotations for detailed Allure report steps

### Test Data
- loginData.json — external JSON file with multiple login scenarios
- JsonDataReader.java — reads and parses JSON into Java objects
- LoginData.java — data model with email, password, expected result fields

### Test Cases
- LoginTest.java — data-driven login tests using TestNG @DataProvider
- Tests valid and invalid login credentials from JSON
- Annotated with @Epic, @Feature, @Story, @Severity, @Description
- Parameters shown in Allure report per test run
- @BeforeClass sets up driver and navigates to login URL from config
- @AfterMethod resets browser between each test
- @AfterClass quits driver after all tests

### Reporting — Allure
- Full Allure integration with TestNG
- @Step annotations show detailed timeline inside each test
- @Epic / @Feature / @Story group tests in the report
- @Severity marks test importance
- Screenshots automatically captured and embedded on test failure

### Test Listener
- TestListener.java implements ITestListener
- onTestFailure() automatically triggered by TestNG on any failure
- Screenshot captured as bytes and attached directly into Allure report
- Registered in testng.xml so TestNG calls it automatically

---

##  How to Run

### Run Tests Only
Right click testng.xml and press Run

### Run Tests + Open Allure Report
In IntelliJ Maven panel run both commands in order:
1. clean test
2. allure:serve
This will run all tests and then open the Allure report in your browser.

---

##  Allure Report Structure

Epic: Authentication
└── Feature: Login
    └── Story: Login with various credentials
        └── testLogin PASS / FAIL
             ├── Parameters:
             │    ├── Email: user@example.com
             │    └── Expected Result: true/false
             ├── Step 1: Login with email: user@example.com
             ├── Step 2: Check if login was successful
             └── 📸 Screenshot (only on failure)

---

##  Configuration

All URLs and credentials are stored in src/test/resources/config.properties:

base.url=https://practice.qabrains.com/ecommerce
login.url=https://practice.qabrains.com/ecommerce/login
valid.email=test@qabrains.com
valid.password=Password123
browser=chrome

To change the environment or credentials, just update this file — no code changes needed.

---

##  Work In Progress

The following will be added in future commits:

- [ ] DashboardPage.java — post-login page interactions
- [ ] ProductPage.java — product page interactions
- [ ] CartPage.java — cart page interactions
- [ ] DashboardTest.java — dashboard test cases
- [ ] ProductTest.java — product test cases
- [ ] CartTest.java — cart test cases
- [ ] Multi-browser support in WebDriverManager
- [ ] Parallel test execution

---

## 👤 Author
Built as a learning project to practice QA automation best practices.
