package com.config;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

import com.util.CommonMethord;

// 配置文件读取
public class TestConfig {
    private Properties propertie;
    private FileInputStream inputfile;
    // 读取配置文件
    public TestConfig(String filepath) {
        try {
            filepath = GetTestProperties.class.getClassLoader().getResource("")
                    .toURI().getPath()
                    + filepath;
            try {
                filepath = CommonMethord.convertFilepath(filepath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        propertie = new Properties();
        try {
            inputfile = new FileInputStream(filepath);
            propertie.load(inputfile);
            inputfile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 得到对应key的值
    public String getValue(String key) {
        if (propertie.containsKey(key)) {
            String value = propertie.getProperty(key);
            return value;
        } else
            return "";
    }
}
