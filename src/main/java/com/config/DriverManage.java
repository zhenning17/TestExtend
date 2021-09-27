package com.config;

import com.util.CommonMethord;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

// driver浏览器驱动配置

public class DriverManage {

    public static WebDriver driver = null;
    private static Logger log = Logger.getLogger("DriverManage");

    public static WebDriver getDriver(String runDriver) throws Exception {
        PropertyConfigurator.configure( CommonMethord.getRealath()
                +"log4j.properties");

        switch (Integer.parseInt(runDriver)) {
            case 1:
                System.setProperty("webdriver.firefox.bin",
                        "C:\\Program Files\\Mozilla Firefox\\firefox.exe");
                driver = new FirefoxDriver();
                log.info("启动Firefox浏览器......");
                break;
            case 2:
                System.setProperty("webdriver.ie.driver",
                        CommonMethord.getRealath() + "res/IEDriverServer_32.exe");
                driver = new InternetExplorerDriver();
                log.info("启动IE浏览器......");
                break;
            case 3:
                System.setProperty("webdriver.chrome.driver",
                        CommonMethord.getRealath() + "res/chromedriver.exe");
                driver = new ChromeDriver();
                log.info("启动Chrome浏览器......");
                break;
            case 4:
                System.setProperty("webdriver.chrome.driver",
                        CommonMethord.getRealath() + "res/chromedriver" );
                driver = new ChromeDriver();
                log.info("启动Mac环境下Chrome浏览器......");
                break;
        }
        return driver;
    }
}
