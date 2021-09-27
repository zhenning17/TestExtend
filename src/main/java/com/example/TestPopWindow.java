package com.example;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;


public class TestPopWindow {
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

        // 处理弹出窗口
        driver.findElement( By.id( "baidu" ) ).click();
        // 获取当前页面title
        System.out.println( driver.getTitle() );

        driver.switchTo().window( "wwww" ); // 句柄

        // 抓阄实验
        // 循环1 为了抓取效率，弹出命中，浏览器弹出有时较慢
        for (int i = 0; i <= 9; i++) {
            Set<String> windowHandles = driver.getWindowHandles(); //获得窗口句柄
            // 循环2
            for (String handler : windowHandles) {
                System.out.println( "handler+ " + handler );
                driver.switchTo().window( handler ); // 尝试切换
                String title = driver.getTitle();
                if ("百度一下，你就知道 ".equals( title )) {
                    i = 10;
                    break;
                }
            }
        }

        driver.findElement( By.id( "kw" ) ).sendKeys( "三生三世十里桃花" );
        driver.findElement( By.id( "su" ) ).click();


    }

    @AfterMethod
    public void testDown() throws Exception {
        Thread.sleep( 5000 );
        driver.quit();
    }

}
