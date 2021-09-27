package com.business;

import com.config.GetTestProperties;
import com.page.LoginPage;
import com.util.LogsInit;
import org.openqa.selenium.WebDriver;

public class LoginBusiness extends LogsInit {
    private WebDriver driver;
    public LoginBusiness(WebDriver driver) {
        this.driver = driver;
    }

    // 打开测试url
    public void goToUrl() {
        driver.get( GetTestProperties.getTestUrl());
    }

    /**
     * 定义登陆业务
     *
     * @param username
     * @param password
     * @throws Exception
     */
    public void login(String username, String password) throws Exception {
        this.goToUrl(); // 打开测试网址

        LoginPage login_page = new LoginPage(driver);
        // 有顺序的调用po里元素
        login_page.setUsername(username); // 输入用户名
        login_page.setPassword(password); // 输入密码
        login_page.clickButton(); // 点击登录按钮
    }

    /**
     * 默认登录-管理员,不需传参
     *
     * @return
     * @throws Exception
     */
    public boolean defaultLogin() throws Exception {

        this.goToUrl();

        LoginPage page = new LoginPage(driver);
        // 读取默认用户密码
        page.setUsername(GetTestProperties.getName());
        page.setPassword(GetTestProperties.getPasswd());
        page.clickButton();

        Thread.sleep(500);
        // boolean result = page.waitUntil(driver);
        boolean result = driver.getTitle().contains("金融消费者投诉报送系统");
        return result;
    }
}
