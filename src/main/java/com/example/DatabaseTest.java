package com.example;

import com.util.DatabaseService;
import org.databene.benerator.anno.Database;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

@Database( id = "db",environment = "env.properties")
public class DatabaseTest {

    @Test
    public void test00001( Method method) throws Exception {
        String methodName = "";
        methodName = method.getName();
        DatabaseService db = new DatabaseService();
        System.out.println(db.connectDBDriver( "mysql",
                "root",
                "123456",
                "jdbc:mysql://118.24.13.38:3308/goods?characterEncoding=utf8&amp;useSSL=false"
                ));


    }

}
