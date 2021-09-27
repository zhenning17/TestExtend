package com.util;

import com.config.GetTestProperties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.List;

// 数据库备份、恢复服务
public class DbUnitService extends DBTestCase {
    private final Logger log = Logger.getLogger(getClass());
    //String dir_name = "dbbackup";
    String dir_name;
    public DbUnitService() {
        dir_name = GetTestProperties.getDbDir();
        String dbusername = GetTestProperties.getdbusername();
        String dbpassword = GetTestProperties.getdbpassword();
        String dbtype = GetTestProperties.getdbtype();
        String dburl = GetTestProperties.getdburl();
        try {
            PropertyConfigurator.configure(CommonMethord.getRealath()
                    + "log4j.properties");
        } catch (Exception e) {
            e.printStackTrace();
        }
        CommonMethord.createDir(dir_name);
        if (dbtype.equals("mysql")) {
            System.setProperty(
                    PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,
                    "com.mysql.jdbc.Driver");
        } else {
            log.error("undefined db type !！～～～");
        }
        System.setProperty(
                PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, dburl);
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,
                dbusername);
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,
                dbpassword);
    }

    /**
     * 给定数据集
     * @return
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
    @Override
    protected IDataSet getDataSet() throws Exception {
        log.info("init...");
        return new FlatXmlDataSet(new FileInputStream(""));
    }

    /**
     * getSetUpOperation
     * @return
     * @throws Exception
     */
    @Override
    protected DatabaseOperation getSetUpOperation() throws Exception {
        return DatabaseOperation.REFRESH;
    }
    /**
     * getTearDownOperation
     * @return
     * @throws Exception
     */
    @Override
    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.NONE;
    }

    /**
     * 给定数据集
     * @param fileName
     * @return
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
    protected IDataSet getDataSet(String fileName) throws Exception {
        log.info("init...");
        return new FlatXmlDataSet(new FileInputStream(fileName));
    }

    /**
     * 单表备份,传入表名和备份文件名
     *
     * @param tbname
     * @param xmlFileName
     * @throws Exception
     */
    public void backupTable(String tbname, String xmlFileName) throws Exception {
        IDatabaseConnection connection = getConnection();
        try {

            QueryDataSet dataSet = new QueryDataSet(connection);
            // 将表里的数据导出到 xml文件里
            dataSet.addTable(tbname);
            // 将表里符合条件的数据导出到xml文件里
            // dataSet.addTable("users", "select * from users where id < 4");
            // 导出到dbunit.xml文件里
            File f_file = new File(dir_name + File.separator + xmlFileName);
            FlatXmlDataSet.write(dataSet, new FileOutputStream(f_file));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
            }
        }
    }
    /**
     * 备份多个数据表 ,英文逗号分隔多个表名
     *
     * @param tabname
     * @param xmlFileName
     * @throws Exception
     */
    public void backupTables(String tabname, String xmlFileName) throws Exception {
        IDatabaseConnection connection = getConnection();
        try {
            String tbname;
            List<String> tbs = CommonMethord.getList(tabname);
            QueryDataSet dataSet = new QueryDataSet(connection);
            // 添加多个table
            for (int i = 0; i < tbs.size(); i++) {
                tbname = tbs.get(i);
                dataSet.addTable(tbname);
            }
            // 导出到dbunit.xml文件里
            File f_file = new File(dir_name + File.separator + xmlFileName);
            FlatXmlDataSet.write(dataSet, new FileOutputStream(f_file));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
            }
        }
    }

    /**
     * 备份全部数据表
     *
     * @param xmlFileName
     * @throws Exception
     */
    public void backupAllTables(String xmlFileName) throws Exception {
        IDatabaseConnection connection = getConnection();

        try {
            // 如果想把某个数据库里的所有表里的数据全部导出到某个xml里,又不想通过addTable一个个来添加的话。
            // 则必须通过IDatabaseConnection的createDataSet()来创建IDataSet
            IDataSet dataSet = connection.createDataSet();
            // 导出到dbunit.xml文件里
            File f_file = new File(dir_name + File.separator + xmlFileName);
            FlatXmlDataSet.write(dataSet, new FileOutputStream(f_file));
            // 也可以用FlatDtdDataSet导出一个对应的dtd文件
            // FlatDtdDataSet.write(dataSet, new FileOutputStream(
            // "dbunit_alltb.dtd"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
            }
        }
    }
    /**
     * restoreDb 恢复DB, 在Maven恢复较慢
     * @param xmlFileName
     * @throws Exception
     */
    public void restoreDb(String xmlFileName) throws Exception {
        IDatabaseConnection connection = getConnection();
        try {
            // IDataSet xmlDataSet = new FlatXmlDataSet(new FileInputStream(
            // fileName));
            File f_file = new File(CommonMethord.getRealath() + File.separator
                    + dir_name + File.separator + xmlFileName);
            IDataSet xmlDataSet = getDataSet(f_file.getAbsolutePath());
            DatabaseOperation.CLEAN_INSERT.execute(connection, xmlDataSet);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
            }
        }
    }

}
