package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

// 数据库服务，提供从数据库抽取数据到脚本中
public class DatabaseService extends LogsInit {
    /**
     * Create Connection
     * @param dbtype
     * @param username
     * @param password
     * @param url
     * @return
     * @throws Exception
     */
    public Connection connectDBDriver(String dbtype, String username,
                                      String password, String url) throws Exception {
        Connection conn = null;
        try {
            if (dbtype.equals("mysql")) {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } else if (dbtype.equals("oracle")) {
                Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
            } else {
                log.error("undefined db type !");
            }
            conn = DriverManager.getConnection(url, username, password);
            log.info("Database connection established");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Cannot connect to database server");
        }
        return conn;
    }

    /**
     * close DB
     * @param conn
     * @throws Exception
     */
    public void closeDBDriver(Connection conn) throws Exception {
        try {
            conn.close();
            log.info("数据库连接已终止");
        } catch (Exception e) {   /* ignore close errors */
            e.printStackTrace();
            log.error(e.toString());
        }
    }

    /**
     *get Result Set
     * @param conn
     * @param sql
     * @return
     * @throws Exception
     */
    private ResultSet getResultSet(Connection conn, String sql)
            throws Exception {
        ResultSet resultSet = null;
        try {
            // PreparedStatement pstmt;
            // ResultSet rset;
            Statement statement = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            // pstmt = conn.prepareStatement(sql);
            resultSet = statement.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
        }
        return resultSet;
    }

    /**
     * get Column Count
     * @param resultSet
     * @return
     * @throws Exception
     */
    private int getColumnCount(ResultSet resultSet) throws Exception {
        int columnCount = 0;
        try {
            // ResultSet resultSet = this.getResultSet(conn, sql);
            columnCount = resultSet.getMetaData().getColumnCount();
            if (columnCount == 0) {
                log.info("sql error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
        }
        return columnCount;
    }

    /**
     * get Column Count
     * @param conn
     * @param sql
     * @return
     * @throws Exception
     */
    public int getColumnCount(Connection conn, String sql) throws Exception {
        int columnCount = 0;
        try {
            // ResultSet resultSet = this.getResultSet(conn, sql);
            columnCount = getResultSet(conn, sql).getMetaData()
                    .getColumnCount();
            if (columnCount == 0) {
                log.info("sql error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
        }
        return columnCount;
    }

    /**
     * get Row Count
     * @param conn
     * @param sql
     * @return
     * @throws Exception
     */
    public int getRowCount(Connection conn, String sql) throws Exception {
        int rowCount = 0;
        try {
            ResultSet resultSet = getResultSet(conn, sql);
            resultSet.last();
            rowCount = resultSet.getRow();
            if (rowCount == 0) {
                log.info("sql query no data!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
        }
        return rowCount;
    }

    /**
     * get Row Count
     * @param resultSet
     * @return
     * @throws Exception
     */
    private int getRowCount(ResultSet resultSet) throws Exception {
        int rowCount = 0;
        try {
            resultSet.last();
            rowCount = resultSet.getRow();
            if (rowCount == 0) {
                log.info( "sql query no data!" );
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error( e.toString() );
        }
        return rowCount;
    }

    /**
     * 根据 sql 和 结果坐标获取查询结果
     * get data by row index and col index
     *
     *
     * @param conn
     * @param sql
     * @param row
     * @param col
     * @return
     * @throws Exception
     */
    public String getData(Connection conn, String sql, int row, int col)
            throws Exception {
        String data = null;
        int rownum = 0;
        int rowcount = 0;
        int colcount = 0;
        try {
            ResultSet resultSet = getResultSet(conn, sql);
            colcount = getColumnCount(resultSet);
            rowcount = getRowCount(resultSet);
            resultSet.beforeFirst();
            if (rowcount > 0) {
                if (row <= 0 || row > rowcount) {
                    log.error("error row index!");
                } else {
                    if (col <= 0 || col > colcount) {
                        log.error("error col index!");
                    } else {
                        while (resultSet.next()) {
                            rownum++;
                            if (rownum == row) {
                                data = resultSet.getString(col);
                                break;
                            }
                        }
                    }
                }
            } else {
                log.info("sql query no data!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
        }
        return data;
    }

    /**
     * get data by row index and col index
     * @param conn
     * @param sql
     * @param row
     * @param field
     * @return
     * @throws Exception
     */
    public String getData(Connection conn, String sql, int row, String field)
            throws Exception {
        String data = null;
        int rownum = 0;
        int rowcount = 0;
        // int colcount = 0;
        try {
            ResultSet resultSet = getResultSet(conn, sql);
            // colcount = getColumnCount(resultSet);
            rowcount = getRowCount(resultSet);
            resultSet.beforeFirst();
            if (rowcount > 0) {
                if (row <= 0 || row > rowcount) {
                    log.error("error row index!");
                } else {
                    while (resultSet.next()) {
                        rownum++;
                        if (rownum == row) {
                            data = resultSet.getString(field);
                            break;
                        }
                    }
                }
            } else {
                log.info("sql query no data!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
        }
        return data;
    }

    public void getColumnCount() {
    }
}
