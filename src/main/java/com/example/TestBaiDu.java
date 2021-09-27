package com.example;

import com.util.Selenium3Proxy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestBaiDu {
    private WebDriver driver;
    private String baseUrl;

    @BeforeMethod
    public void setUp() throws Exception {
        System.setProperty( "webdriver.chrome.driver", "src/main/resources/res/chromedriver" );
        driver = new ChromeDriver();
        baseUrl = "https://www.baidu.com/";
        driver.manage().timeouts().implicitlyWait( 3, TimeUnit.SECONDS ); // 全局等待设置
        driver.manage().window().maximize();
    }

    @Test
    public void testUntitled() throws Exception {
        driver.navigate().to( baseUrl );

        driver.findElement( By.id( "kw" ) ).sendKeys( "百度一下" );
        Selenium3Proxy se = new Selenium3Proxy( driver );
        se.getKeyboard();
        se.refreshWithEnter();


    }
    @AfterMethod
    public void testDown() throws Exception {
        driver.quit();
    }
}
