package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestTable {
    private WebDriver driver;
    private String baseUrl;
    @BeforeMethod
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/res/chromedriver");
        driver = new ChromeDriver();
        baseUrl = "";
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); // 全局等待设置
        driver.manage().window().maximize();
    }

    @Test
    public void testUntitled() throws Exception {
        driver.navigate().to( baseUrl );

        // 网页表格处理
        WebElement a = driver.findElement(By.tagName("table"));
        List<WebElement> rows =a.findElements(By.tagName("tr"));
        List<WebElement> cells=rows.get(1).findElements(By.tagName("td"));

        System.out.println(cells.get(2).getText());
        System.out.println();

    }

    @AfterMethod
    public void testDown() throws Exception {
        driver.quit();
    }
}
