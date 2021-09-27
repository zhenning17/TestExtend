package com.cases;

import com.config.DriverManage;
import com.config.GetTestProperties;
import com.util.CommonMethord;
import com.util.DatabaseService;
import com.util.Selenium3Proxy;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.databene.feed4testng.FeedTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.sql.Connection;
import java.util.concurrent.TimeUnit;

public class TestBase extends FeedTest {
    protected WebDriver driver = null;
    protected String browser;
    protected final Logger log = Logger.getLogger( getClass() );
    protected boolean captureflag;
    protected Connection conn = null;
    protected DatabaseService ds = new DatabaseService();

    String dbusername;
    String dbpassword;
    String dbtype;
    String dburl;

    /**
     * 使用这个注解的方法会在每个 test 方法运行之后运行
     *
     * @throws Exception
     */
    @BeforeMethod
    public void beforeMethod() throws Exception {
        captureflag = true; //截图标记位

        // 强制按进程回收，防止任务管理器drive死机,由env.properties控制
//        CommonMethord.killMacProcess( getBrowserProcess( browser ) );
//        CommonMethord.killMacProcess("Chrome");

        driver = DriverManage.getDriver( browser );
        //driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS); //设置页面加载超时时间
        driver.manage().timeouts().implicitlyWait( 2, TimeUnit.SECONDS ); //隐示等待
        driver.manage().window().maximize(); // 最大窗口化
    }

    /**
     * 使用这个注解的方法会在每个 test 方法运行之后运行
     *
     * @throws Exception
     */
    @AfterMethod
    public void afterMethod() throws Exception {
        try {
            // 截图处理，判断案例是否通过
            Selenium3Proxy se = new Selenium3Proxy( driver );
            // 判断是否截图
            if (captureflag) {
                se.captureScreenshot(); // 截图方法

            }
            Thread.sleep( 1000 );
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                // kill 浏览器 ，1表示火狐
                //CommonMethord.killProcess(getBrowserProcess(browser));
//                CommonMethord.killMacProcess(getBrowserProcess(browser));
                Thread.sleep( 500 );
                if (!browser.equals( "1" )) {
                    driver.quit();
                }
            }
        }
    }

    /**
     * 使用这个注解的方法会在当前这个类的第一个 test 方法运行之前运行
     *
     * @param driverType
     * @throws Exception
     */
    @Parameters({"driverType"})
    @BeforeClass
    public void beforeClass(@Optional("4") String driverType) throws Exception {
        dbusername = GetTestProperties.getdbusername();
        dbpassword = GetTestProperties.getdbpassword();
        dbtype = GetTestProperties.getdbtype();
        dburl = GetTestProperties.getdburl();
        conn = ds.connectDBDriver( dbtype, dbusername, dbpassword, dburl );
        //browser = GetTestProperties.getBrowserType(); //读取配置文件
        browser = driverType;
        log.info( "Start to run test class========== " + this.getClass().getName()
                + "on driverType" + browser );

    }

    /**
     * 使用这个注解的方法会在当前这个类的最后一个 test 方法运行之后运行
     *
     * @throws Exception
     */
    @AfterClass
    public void afterClass() throws Exception {
        ds.closeDBDriver( conn );
    }


    /**
     * 使用这个注解的方法会在 xml 文件中的 test 标签中的每个 test 方法运行之前运行
     *
     * @throws Exception
     */
    @BeforeTest
    public void beforeTest() throws Exception {
        PropertyConfigurator.configure( CommonMethord.getRealath()
                + "log4j.properties" );
    }

    public String getBrowserProcess(String bs) throws Exception {
        String process = null;
        switch (Integer.parseInt( bs )) {
            case 1:
                process = "firefox.exe";
                break;
            case 2:
                process = "iexplore.exe";
                break;
            case 3:
                process = "chrome.exe";
                break;
            // Mac环境选择4
            case 4:
                process = "Chrome";
                System.out.println( "==打印getBrowserProcess=========" + process );
                break;
        }
        return process;
    }
}
