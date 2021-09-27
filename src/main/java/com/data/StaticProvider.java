package com.data;

import com.config.ExcelDataProvider;
import jxl.read.biff.BiffException;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;

// excel数据驱动生成器，为case层中的测试方法提供测试数据
public class StaticProvider {
    @DataProvider(name = "dp")
    public static Iterator<Object[]> getDataByTestMethodName(Method method)
            throws BiffException, IOException {
        return new ExcelDataProvider(method.getDeclaringClass().getName(),method.getName());
    }
}
