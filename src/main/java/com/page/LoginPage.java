package com.page;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.util.LogsInit;

// 页面层
public class LoginPage extends LogsInit {
    private WebDriver driver;

    // 构造方法
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * 设置用户名
     *
     * @param userText
     * @throws NotFoundException
     */
    public void setUsername(String userText) throws NotFoundException {
        // driver.findElement(By.id("username")).clear();
        // driver.findElement(By.id("username")).sendKeys(userText);
        this.setText(driver.findElement( By.id("account")), userText);
    }

    /**
     * 设置密码
     *
     * @param passText
     * @throws NotFoundException
     */
    public void setPassword(String passText) throws NotFoundException {
        // driver.findElement(By.id("password")).clear();
        // driver.findElement(By.id("password")).sendKeys(userText);
        this.setText(driver.findElement(By.name("password")), passText);
    }

    /**
     * 点击登录按钮
     *
     * @throws NotFoundException
     */
    public void clickButton() throws NotFoundException {
        driver.findElement(By.id("submit")).click();
    }

    private void setText(WebElement e, String text) {
        e.clear();
        e.sendKeys(text);
    }

}
