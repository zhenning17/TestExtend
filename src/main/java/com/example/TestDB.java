package com.example;

import com.business.LoginBusiness;
import com.cases.TestBase;
import com.data.TestData;
import com.util.Selenium3Proxy;
import org.databene.benerator.anno.Database;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

// 废弃
@Database( id = "db",environment = "env.properties")
public class TestDB extends TestBase {
    LoginBusiness login = new LoginBusiness(driver);

    @Test(dataProvider = "LoginPass", dataProviderClass = TestData.class, enabled = true)
    public void test_0001_LoginPass(Integer n, String username,
                                    String password, String name, Method method) throws Exception {
        String methodName = "";
        methodName = method.getName();

        try {
            log.info( methodName + " 在运行呢 !" );
//            login.login( "admin","abc123" );
            login.login( username,password );
            String sql = "select logon_name,password from umg_user where pkid=1";
            log.info( "DB数据  --- : " + ds.getData( conn, sql, 1, 2 ) );
            captureflag = false; //断言执行成功才执行此行代码，否则跳过
        } catch (Exception e) {
            e.printStackTrace();
            captureflag = false;
            Selenium3Proxy se = new Selenium3Proxy( driver );
            //se.captureScreenshot( methodName );
            Thread.sleep( 1000 );
            Assert.fail( "fail: " + e.getMessage() );
        } catch (AssertionError e) {
            e.printStackTrace();
            captureflag = false;
            Selenium3Proxy se = new Selenium3Proxy( driver );
            Thread.sleep( 1000 );
            log.info( e.getMessage() );
            Assert.fail( "fail: " + e.getMessage() );
        } finally {
            log.info( methodName + " is end !" );
        }
    }

    @Test(dataProvider = "LoginFail", dataProviderClass = TestData.class, enabled = false)
    public void test_0002_LoginFail(Integer n, String username,
                                    String password, String name, Method method) throws Exception {
        String methodName = "";
        methodName = method.getName();
        try {
            log.info( methodName + " is running !" );
            login.login( username, password );
            Thread.sleep( 1500 );
            Assert.assertTrue( driver.getPageSource().contains( "错误代码" ) );
            captureflag = false;
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail( "fail: " + e.getMessage() );
        } catch (AssertionError e) {
            e.printStackTrace();
            captureflag = false;
            Selenium3Proxy se = new Selenium3Proxy( driver );
            //se.captureScreenshot( methodName );
            Thread.sleep( 1000 );
            Assert.fail( "fail: " + e.getMessage() );
        } finally {
            log.info( methodName + " is end !" );
        }
    }

}
