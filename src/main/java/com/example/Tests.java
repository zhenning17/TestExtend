package com.example;

import com.util.Selenium3Proxy;
import org.databene.benerator.anno.Source;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.Target;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Tests {
    private WebDriver driver;
    private String baseUrl;

    @BeforeMethod
    public void setUp() throws Exception {
        System.setProperty( "webdriver.chrome.driver", "src/main/resources/res/chromedriver" );
        driver = new ChromeDriver();
        baseUrl = "http://sports.sina.com.cn/nba/";
        driver.manage().timeouts().implicitlyWait( 5, TimeUnit.SECONDS ); // 全局等待设置
        driver.manage().window().maximize();
    }

    @Test(description = "登录",enabled = true)
    public void testUntitled_001() throws Exception {
        driver.navigate().to( baseUrl );
        List<WebElement> list = driver.findElements( By.className( "item" ) );
        for (int i=0;i<list.size();i++){
            List<WebElement> tag = list.get( i ).findElements( By.tagName( "a" ) );
            // 遍历单条新闻
            for( WebElement lis : tag ){
                String href = tag.toString();
                // 筛选出来新闻的url
                if (href.contains( "sports.sina.com.cn/" ) && href.contains( "shtml" )){
                    System.out.println("-------"+href);
                }
            }
        }
    }


    @AfterMethod
    public void testDown() throws InterruptedException {
        Thread.sleep( 1000 );
        driver.quit();
    }
}
