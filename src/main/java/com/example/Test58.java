package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Test58 {
    private WebDriver driver;
    private String baseUrl;

    @BeforeMethod
    public void setUp() throws Exception {
        System.setProperty( "webdriver.chrome.driver", "src/main/resources/res/chromedriver" );
        driver = new ChromeDriver();
        baseUrl = "https://bj.58.com/";
        driver.manage().timeouts().implicitlyWait( 2, TimeUnit.SECONDS ); // 全局等待设置
        driver.manage().window().maximize();
    }
    @Test
    public void testUntitled() {
        driver.navigate().to( baseUrl );

        driver.findElements( By.linkText( "" ) );

    }
    @AfterMethod
    public void testDown() throws Exception {
        Thread.sleep(5000);
        driver.quit();
    }
}
