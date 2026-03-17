package org.example.tests;


import org.example.pages.LoginPage;
import org.example.utils.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

public class DataDrivenLoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() throws Exception {
        File file = new File("src/test/resources/LoginData.xlsx");
        FileInputStream fis = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        int rowCount = sheet.getPhysicalNumberOfRows() - 1;
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
        Object[][] data = new Object[rowCount][colCount];

        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next(); // skip header
        int i = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            for (int j = 0; j < colCount; j++) {
                data[i][j] = row.getCell(j).getStringCellValue();
            }
            i++;
        }
        workbook.close();
        fis.close();
        return data;
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String email, String password) {
        driver.get("https://qa-brains-practice-site.com/login");
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        // assert על הצלחה או כשלון בהתאם לנתונים
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}