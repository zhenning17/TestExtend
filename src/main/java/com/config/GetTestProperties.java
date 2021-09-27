package com.config;

// 根据配置文件的key获取配置文件对应的value
public class GetTestProperties {
//    static TestConfig tc = new TestConfig( "env.properties" );
    static TestConfig tc = new TestConfig( "env.properties" ); //配置文件
    // Screenshot目录
    public static String getPicDir() {
        String picdir = tc.getValue( "picdir" );
        return picdir;
    }
    // DB备份存放目录名
    public static String getDbDir() {
        String dbdir = tc.getValue( "dbdir" );
        return dbdir;
    }
    public static String getTestUrl() {
        String url = tc.getValue( "testurl" );
        return url;
    }
    // 浏览器选择配置项
    public static String getBrowserType() {
        String bt = tc.getValue( "browsertype" );
        return bt;
    }
    // 程序登录户名
    public static String getName() {
        String username = tc.getValue( "name" );
        return username;
    }
    // 程序登录密码
    public static String getPasswd() {
        String passwd = tc.getValue( "passwd" );
        return passwd;
    }

    /**
     * 程序其他用户登录名称和密码
     *
     * @return
     */
    public static String getNamePm() {
        String username = tc.getValue("namePm");
        return username;
    }
    public static String getPasswdPm() {
        String passwd = tc.getValue("passwdPm");
        return passwd;
    }


    // 数据库类型
    public static String getdbtype() {
        String dbtype = tc.getValue( "dbtype" );
        return dbtype;
    }
    // 数据库登录用户
    public static String getdbusername() {
        String dbusername = tc.getValue( "dbusername" );
        return dbusername;
    }
    // 数据库登录密码
    public static String getdbpassword() {
        String dbpasswd = tc.getValue( "dbpasswd" );
        return dbpasswd;
    }
    // 数据库连接地址
    public static String getdburl() {
        String dburl = tc.getValue( "dburl" );
        return dburl;
    }



    public static String getAfterLoginUrl() {
        String url = tc.getValue( "afterLoginUrl" );
        return url;
    }
    public static String getAfterAddPersonnelUrl() {
        String url = tc.getValue( "afterAddPersonnelUrl" );
        return url;
    }
    public static String getMySiteLinkUrl() {
        String url = tc.getValue( "mySiteLinkUrl" );
        return url;
    }
    public static String getHomeLinkUrl() {
        String url = tc.getValue( "homeLinkUrl" );
        return url;
    }
    public static String getMyToDoLinkUrl() {
        String url = tc.getValue( "myToDoLinkUrl" );
        return url;
    }
    public static int getFlag() {
        String stringFlag = tc.getValue( "flag" );
        int flag = Integer.parseInt( stringFlag );
        return flag;
    }
    public static String getNewProjectButtonUrl() {
        String url = tc.getValue( "newProjectButtonUrl" );
        return url;
    }
}