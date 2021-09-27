package com.config;

//import com.util.CommonMethord;
//import jxl.Cell;
//import jxl.Sheet;
//import jxl.Workbook;
//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;
//
//import java.io.File;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;


import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import com.util.CommonMethord;
import jxl.*;

// excel数据驱动配置
public class ExcelDataProvider implements Iterator<Object[]> {
    private final Logger log = Logger.getLogger( getClass() );
    private Workbook book = null;
    private Sheet sheet = null;
    private int rowNum = 0;
    private int curRowNo = 0;
    private int columnNum = 0;
    private String[] columnnName;

    public ExcelDataProvider(String classname, String methodname) {
        try {
            PropertyConfigurator.configure( CommonMethord.getRealath()
                    + "log4j.properties" );
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            // File directory = new File(".");
            log.info( "找到文件开始!" );
            this.book = Workbook.getWorkbook( new File( CommonMethord
                    .getRealath()
                    + "data/"
                    + classname.replaceAll( "\\.", "/" ) + ".xls" ) );
            log.info( "定位文件确定！" );
            log.info( "文件路径:"+ CommonMethord.getRealath() + "data/"
                    + classname.replaceAll( "\\.", "/" ) + ".xls" );
            this.sheet = book.getSheet( methodname );
            this.rowNum = sheet.getRows();

            Cell[] c = sheet.getRow( 0 );
            this.columnNum = c.length;
            columnnName = new String[c.length];
            for (int i = 0; i < c.length; i++) {
                //log.info( c[i].getContents().toString());
                columnnName[i] = c[i].getContents().toString()
                        .replace( "\n", "" );
            }
            this.curRowNo++;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean hasNext() {
        if (this.rowNum == 0 || this.curRowNo >= this.rowNum) {
            try {
                book.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        } else
            return true;
    }

    @Override
    public Object[] next() {
        Cell[] c = sheet.getRow( this.curRowNo );
        Map<String, String> s = new HashMap<String, String>();
        for (int i = 0; i < this.columnNum; i++) {
            String temp = "";
            try {
                temp = c[i].getContents().toString();
            } catch (ArrayIndexOutOfBoundsException ex) {
                temp = "";
            }
            s.put( this.columnnName[i], temp );
        }

        Object r[] = new Object[1];
        r[0] = s;
        this.curRowNo++;
        return r;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException( "remove unsupported." );
    }
}
