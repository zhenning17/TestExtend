package com.example;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 1 下拉选择框
 * 2 image上传
 * 3 单选框
 */
public class TestSelectOption{
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

        // 方式一
        Select sel1 = new Select(driver.findElement(By.id("areaID")));
        sel1.selectByVisibleText( "北京" );
        sel1.selectByVisibleText( "上海" );
        sel1.selectByIndex( 3 );

        // 方式二
        WebElement area = driver.findElement(By.id("areaID")); //获取
        List<WebElement> lis = area.findElements(By.tagName("option"));
        int num=lis.size();
        Select select = new Select(area);
        select.selectByIndex(num-1); // 选择最后一个

        // 方式三
        int s = driver.findElement(By.id("areaID")).findElements(By.tagName("option")).size();
        Select sel2 = new Select(driver.findElement(By.id("areaID")));
        sel2.selectByIndex(s-3);

        // 方式四
        List<WebElement> list = driver.findElements(By.cssSelector("#recordlist>li"));
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i).getText());
            list.get( 2 );
        }

        // image上传
        String image="C:\\Users\\admin\\Desktop\\htm\\20200410131937.jpg";
        driver.findElement(By.name("file")).sendKeys(image);

        // 单选框
        driver.findElements(By.name("u2")).get(3).click();







    }

    @AfterMethod
    public void testDown() throws Exception {
        driver.quit();
    }

}
