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

public class Zentao {
    private WebDriver driver;
    private String baseUrl;
    @BeforeMethod
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/res/chromedriver");
        driver = new ChromeDriver();
        baseUrl = "http://172.16.87.164/pro/user-login.html";
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); // 全局等待设置
        driver.manage().window().maximize();
    }

    @Test
    public void testUntitled() throws Exception {
        driver.navigate().to( baseUrl );

        driver.findElement( By.id( "account" ) ).sendKeys( "admin" );
        driver.findElement( By.name( "password" )).sendKeys( "1qaz2wsx#" );
        driver.findElement( By.id( "submit" )).click();







    }

    @AfterMethod
    public void testDown() throws Exception {
        Thread.sleep(5000);
        driver.quit();
    }
}
