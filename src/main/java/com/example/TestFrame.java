package com.example;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;



public class TestFrame {
    private WebDriver driver;
    private String baseUrl;
    @BeforeMethod
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/res/chromedriver");
        driver = new ChromeDriver();
        baseUrl = "null";
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); // 全局等待设置
        driver.manage().window().maximize();
    }

    @Test
    public void testUntitled() throws Exception {
        driver.navigate().to( baseUrl );

        // 针对网页嵌套处理
        System.out.println("进门:" + driver.findElement(By.id("1")).getText());

        //处理嵌套iframe
        driver.switchTo().frame(0);  //切换iframe
        System.out.println("201: " + driver.findElement(By.id("2")).getText());

        driver.switchTo().frame(0);  //切换iframe
        driver.switchTo().defaultContent(); //退出，切换到默认区域

        System.out.println("卫生间: " + driver.findElement(By.id("3")).getText());




    }

    @AfterMethod
    public void testDown() throws Exception {
        Thread.sleep( 5000 );
        driver.quit();
    }

}
