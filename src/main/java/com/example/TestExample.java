package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestExample {
    private WebDriver driver;
    private String baseUrl;
    @BeforeMethod
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/res/chromedriver");
        driver = new ChromeDriver();
//        baseUrl = "http://172.16.87.74:8080//consumer";
        baseUrl = "http://172.16.87.74:8080/ries/";
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); // 全局等待设置

        driver.manage().window().maximize();

    }
    @Test(description = "工单录入")
    public void testUntitled() throws Exception {

        driver.navigate().to( baseUrl );

        //登录
        driver.findElement( By.id( "user_name" ) ).clear();
        driver.findElement( By.id( "user_name" ) ).sendKeys( "admin" );
        driver.findElement( By.id( "user_password" ) ).clear();
        driver.findElement( By.id( "user_password" ) ).sendKeys( "abc123" );
        driver.findElement( By.id( "logindate" ) ).sendKeys( "2020-06-02" );
        driver.findElement( By.name( "Submit" ) ).click();

        System.out.println( "当前title: " + driver.getTitle() );
        System.out.println( "当前url: " + driver.getCurrentUrl() );

        WebDriver frame = driver.switchTo().frame(0);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement body = driver.findElements(By.className("jmenumenu")).get(3);
        Thread.sleep(2000);
        String str = body.getAttribute("style");
        System.out.println("style:"+str);
        // document.getElementsByClassName('jmenumenu')[2].style.display='inline'
        Object object = js.executeScript("return document.getElementsByClassName('jmenumenu')[2].style.display='inline';");
        System.out.println("========return:"+object);
        driver.findElement(By.xpath("//*[@id=\"expmenu-freebie\"]/li/ul/li[3]/ul/li[2]/a")).click();
        driver.switchTo().defaultContent();
		driver.switchTo().frame(2);
		Thread.sleep(2000);
		js.executeScript("$('#flagOrgan1 .textbox-text').click()");
		js.executeScript("$('#_easyui_tree_1 .tree-collapsed').click()");
		js.executeScript("$('#_easyui_tree_2 .tree-collapsed').click()");
		js.executeScript("$('#_easyui_tree_3').click()");
		Thread.sleep(1000);
		js.executeScript("$(\"#flagReport1 span input[class~='textbox-text']\").get(0).click();");
		Thread.sleep(3000);
//		js.executeScript("$(\".panel.combo-p ul[class='tree'] span[class='tree-hit tree-collapsed']\")[15].click();");
		js.executeScript("$(\".tree-node span[class='tree-title'][title='投诉工单信息']\").get(0).click();");
		driver.findElement(By.xpath("//tr[4]/td[2]/a[2]/span/span[1]")).click();

		driver.findElement(By.xpath("//*[@id=\"1_ORGANID\"]")).sendKeys("222222");
		driver.findElement(By.xpath("//*[@id=\"1_USERACCOUNT\"]")).sendKeys("");
		driver.findElement(By.xpath("//*[@id=\"1_UPDATETIME\"]")).sendKeys("");
		driver.findElement(By.xpath("//*[@id=\"1_DATADATE\"]")).sendKeys("");
		driver.findElement(By.xpath("//*[@id=\"1_COMPLAINTID\"]")).sendKeys("");
		driver.findElement(By.xpath("//*[@id=\"1_COUNTRYCODE\"]")).sendKeys("中国");
		driver.findElement(By.xpath("//*[@id=\"1_PROVINCECODE\"]")).sendKeys("河北省");
		driver.findElement(By.xpath("//*[@id=\"1_CITYCODE\"]")).sendKeys("石家庄市");
		driver.findElement(By.xpath("//*[@id=\"1_AREACODE\"]")).sendKeys("新华区");
		driver.findElement(By.xpath("//*[@id=\"1_BIRTHDAY\"]")).sendKeys("");
		driver.findElement(By.xpath("//*[@id=\"1_BANKCODE\"]")).sendKeys("");
		driver.findElement(By.xpath("//*[@id=\"1_BACKCODEDESC\"]")).sendKeys("");
		driver.findElement(By.xpath("//*[@id=\"1_DONEDATE\"]")).sendKeys("");
		driver.findElement(By.xpath("//*[@id=\"1_COMPLAINTNUMBER\"]")).sendKeys("");
		driver.findElement(By.xpath("//*[@id=\"1_COMPLAINTTYPE\"]")).sendKeys("管理型");
		driver.findElement(By.xpath("//*[@id=\"1_BANKAREACODE\"]")).sendKeys("开发区");
		driver.findElement(By.xpath("//*[@id=\"1_COMPLAINTDATE\"]")).sendKeys("");
		driver.findElement(By.xpath("//*[@id=\"1_BANKCITYCODE\"]")).sendKeys("石家庄市");
		driver.findElement(By.xpath("//*[@id=\"1_COMPLAINTCHANNEL\"]")).sendKeys("");
		driver.findElement(By.xpath("//*[@id=\"1_COMPLAINTACCOUNT\"]")).sendKeys("");
		driver.findElement(By.xpath("//*[@id=\"1_BANKPROVINCECODE\"]")).sendKeys("河北省");
		driver.findElement(By.xpath("//*[@id=\"1_ID\"]")).sendKeys("0000");
		driver.findElement(By.xpath("//*[@id=\"1_COMPLAINTREQ\"]")).sendKeys("返还财产");
		driver.findElement(By.xpath("//*[@id=\"tr_1\"]/td[28]/a[1]/span/span[1]")).click();
    }
    @AfterMethod
    public void testDown() throws Exception {
        Thread.sleep(2000);
        driver.quit();
    }
}
