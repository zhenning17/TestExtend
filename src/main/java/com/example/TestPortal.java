package com.example;

import com.sun.org.apache.bcel.internal.ExceptionConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestPortal {
    private WebDriver driver;
    private String baseUrl;

    @BeforeMethod
    public void setUp() throws Exception {
        System.setProperty( "webdriver.chrome.driver", "src/main/resources/res/chromedriver" );
        driver = new ChromeDriver();
        baseUrl = "http://172.16.87.183:8080/portal/login";
        driver.manage().timeouts().implicitlyWait( 2, TimeUnit.SECONDS ); // 全局等待设置
        driver.manage().window().maximize();
    }

    @Test
    public void testUntitled() {
        driver.navigate().to( baseUrl );

        // Login
        driver.findElement( By.id( "UserUsername" ) ).clear();
        driver.findElement( By.id( "UserUsername" ) ).sendKeys( "admin" );
        driver.findElement( By.id( "UserPassword" ) ).clear();
        driver.findElement( By.id( "UserPassword" ) ).sendKeys( "1qaz2wsx#" );
        // 显性等待
        new WebDriverWait( driver, 20 ).until( ExpectedConditions
                .presenceOfElementLocated( By.id( "captcha" ) ) );
        driver.findElement( By.className( "tooltip" ) ).click();
        driver.findElement( By.id( "loginSubmit" ) ).click();


        // 模拟鼠标操作
        Actions builder = new Actions( driver );
        builder.moveToElement( (WebElement) driver.findElement( By.linkText( "系统管理" ) ).findElements( By.linkText( "机构用户" ) ) ).perform();
        driver.findElement(By.linkText("用户管理")).click();  // 展开菜单后的后续操作


    }

    @AfterMethod
    public void testDown() throws Exception {
        Thread.sleep( 3000 );
        driver.quit();
    }
}
