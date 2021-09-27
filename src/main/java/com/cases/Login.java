package com.cases;

import com.business.LoginBusiness;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

@Test(description = "登录")
public class Login extends TestBase {
    public void Test_0001_LoginPass(Method method) throws Exception {
        // logs
        String methodName = "";
        methodName = method.getName();
        log.info(methodName + " 运行中！");

        // 测试案例主体，按顺序调用bo
        LoginBusiness login = new LoginBusiness(driver);
        //login.defaultLogin();
        login.login( "admin","1qaz2wsx#" );
        Thread.sleep(1000);
        // 断言失败,执行截图, 默认captureflag=false
        //Assert.assertTrue(driver.getTitle().contains("产品主页 - 禅道"));
    }

}
