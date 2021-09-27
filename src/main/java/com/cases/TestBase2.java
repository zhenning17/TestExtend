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

// 供高耦合测试案例继承使用，案例模块之间，依赖关系连续操作等
public class TestBase2 extends FeedTest {
    protected WebDriver driver = null;
    protected String browser;
    protected final Logger log = Logger.getLogger(getClass());
    protected boolean captureflag;
    protected Connection conn = null;
    protected DatabaseService ds = new DatabaseService();

    String dbusername;
    String dbpassword;
    String dbtype;
    String dburl;

    /**
     * 初始化浏览器
     * @throws Exception
     */
    @BeforeMethod
    public void beforeMethod() throws Exception {
        captureflag = true; // 默认true false
    }
    /**
     * 截图和关闭浏览器
     * @throws Exception
     */
    @AfterMethod
    public void afterMethod() throws Exception {
        try {
            // 截图处理
            Selenium3Proxy se = new Selenium3Proxy(driver);
            if (captureflag) {  // 判断是否截图，
                se.captureScreenshot(); // 截图方法
            }
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            log.info("测试案例执行结束");
        }
    }

    /**
     * testNG.xml
     * @param driverType
     * @throws Exception
     */
    @Parameters({"driverType"})
    @BeforeClass
    //@Optional 不走testxml运行，参数缺省值，强制传入3，比如走单个case运行
    public void beforeClass(@Optional("3") String driverType) throws Exception {
        dbusername = GetTestProperties.getdbusername();
        dbpassword = GetTestProperties.getdbpassword();
        dbtype = GetTestProperties.getdbtype();
        dburl = GetTestProperties.getdburl();
        conn = ds.connectDBDriver(dbtype, dbusername, dbpassword, dburl);
        // browser = GetTestProperties.getBrowserType();
        browser = driverType;
        log.info("Start to run test class " + this.getClass().getName()
                + " on driverType" + browser);

        driver = DriverManage.getDriver(browser); // 浏览器初始化
        // driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS); // 等待配置
        driver.manage().window().maximize(); // 浏览器最大实例化
    }

    /**
     * 类的初始化，数据库
     * @throws Exception
     */
    @AfterClass
    public void afterClass() throws Exception {

        if (driver != null) {
            // kill 浏览器 ，1表示火狐
			CommonMethord.killMacProcess( getBrowserProcess( browser ) );
            Thread.sleep(500);
            if (!browser.equals("1")) {
                driver.quit();
            }
        }

        ds.closeDBDriver(conn);
    }

    /**
     * 类回收，数据库
     * @throws Exception
     */
    @BeforeTest
    public void beforeTest() throws Exception {
        PropertyConfigurator.configure( CommonMethord.getRealath()
                + "conf/log4j.properties");
    }

    public String getBrowserProcess(String bs) throws Exception {
        String process = null;

        switch (Integer.parseInt(bs)) {
            case 1:
                process = "firefox.exe";
                break;
            case 2:
                process = "iexplore.exe";
                break;
            case 3:
                process = "chrome.exe";
                break;
        }
        return process;
    }


}
