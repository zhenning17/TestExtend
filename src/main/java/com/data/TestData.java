package com.data;

import org.testng.annotations.DataProvider;

// 数据驱动测试
public class TestData {
    // Java 静态方法作为 dataProvider，数据直接写到 java 文件中
    @DataProvider(name = "LoginPass")
    public static Object[][] LoginPass() {
        return new Object[][]{new Object[]{1, "admin", "abc123", "系统管理员"},
                new Object[]{2, "1815", "abc123", "曹娟"},

        };
    }

    @DataProvider(name = "LoginFail")
    public static Object[][] LoginFail() {
        return new Object[][]{new Object[]{1, "admin", "admin1", "系统管理员"},
                new Object[]{2, "abc123", "1111", "异常用户"},
        };
    }

}
