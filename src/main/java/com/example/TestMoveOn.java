package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestMoveOn {
    private WebDriver driver;
    private String baseUrl;

    @BeforeMethod
    public void setUp() throws Exception {
        System.setProperty( "webdriver.chrome.driver", "src/main/resources/res/chromedriver" );
        driver = new ChromeDriver();
        baseUrl = "http://www.jd.com/";
        driver.manage().timeouts().implicitlyWait( 3, TimeUnit.SECONDS ); // 全局等待设置
        driver.manage().window().maximize();
    }

    @Test
    public void testUntitled() throws Exception {
        driver.navigate().to( baseUrl );

        // 模拟鼠标移动到某个对象，jd
        Actions builder = new Actions( driver );
//        Thread.sleep( 5000 );
        builder.moveToElement( driver.findElement( By.linkText( "家用电器" ) ) ).perform();
        Thread.sleep( 1000 );

        // 展开菜单后的后续操作
        driver.findElement( By.linkText( "4K超清电视" ) ).click();

    }

    @AfterMethod
    public void testDown() throws InterruptedException {
        Thread.sleep( 5000 );
//        driver.quit();
    }
}
