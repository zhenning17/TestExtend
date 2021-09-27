package com.example;

import com.util.Selenium3Proxy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Ries {
    private WebDriver driver;
    private String baseUrl;

    @BeforeMethod
    public void setUp() throws Exception {
        System.setProperty( "webdriver.chrome.driver", "src/main/resources/res/chromedriver" );
        driver = new ChromeDriver();
        baseUrl = "http://172.16.87.89:8080/payment/";
        driver.manage().timeouts().implicitlyWait( 2, TimeUnit.SECONDS ); // 全局等待设置
        driver.manage().window().maximize();
    }
    @Test
    public void testUntitled() throws Exception {
        driver.navigate().to( baseUrl );

        driver.findElement( By.id("user_name") ).clear();
        driver.findElement( By.id("user_name") ).sendKeys( "admin" );
        driver.findElement( By.id("user_password") ).clear();
        driver.findElement( By.id("user_password") ).sendKeys( "bonc" );

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript( "document.getElementById('logindate').value='2099-10-10'" );
        driver.findElement( By.name( "Submit" ) ).click();  // 点击登录
        System.out.println( "当前title: " + driver.getTitle()  +   "当前url: " + driver.getCurrentUrl());

        // 数据录入
        driver.switchTo().defaultContent();
        driver.switchTo().frame( "leftFrame" );


        WebElement body = driver.findElements(By.className("jmenumenu")).get(1);
//        Object object = js.executeScript("return document.getElementsByClassName('jmenumenu')[2].style.display='inline';");
        js.executeScript( "document.getElementsByClassName('jmenumenu')[3].style.display='inline'" );
//        Object object = js.executeScript("document.getElementsByClassName('jmenumenu')[3].style.display='inline'");
//        System.out.println("========return:"+object);





    }
    @AfterMethod
    public void testDown() throws Exception {
        Thread.sleep(5000);
        driver.quit();
    }
}
