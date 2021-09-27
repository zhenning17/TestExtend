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


/**
 * 层级元素
 *
 */
public class TestCeng {
    private WebDriver driver;
    private String baseUrl;
    @BeforeMethod
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/res/chromedriver");
        driver = new ChromeDriver();
        baseUrl = "https://www.hao123.com/";
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS); // 全局等待设置
        driver.manage().window().maximize();
    }

    @Test
    public void testUntitled() throws Exception {
        driver.navigate().to( baseUrl );

        // 按页面层级查找link
//        WebElement a = driver.findElement( By.id("sites2_wrapper"));
//        List<WebElement> list = a.findElements(By.tagName("a"));
//        System.out.println("页面链接元素有：" + list.size());
//        System.out.println("网址:"+list.get( 3 ).getText());

        List<WebElement> list = driver.findElements(By.tagName("a"));  //a 是寻找页面的全部link元素
        System.out.println("链接元素"+ list.size());
        list.get(1).click(); //访问第一个link元素

        // 获取页面href
        list.get(0).getText();
        list.get(1).getText();
        list.get(2).getText();
        driver.findElement(By.linkText("新闻")).click();
        driver.findElement(By.linkText("hao123")).click();
        driver.findElement(By.linkText("地图")).click();
        System.out.println("link名称:"+ driver.findElement(By.id("s-top-left")).getText());
//
        // for循环遍历
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(1).getText());
        }
    }


    @AfterMethod
    public void testDown() throws Exception {
        driver.quit();
    }
}
