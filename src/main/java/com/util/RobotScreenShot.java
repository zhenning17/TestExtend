package com.util;

import com.config.GetTestProperties;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


// 截图
public class RobotScreenShot {
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    private String fileName; // 文件的前缀
    private String imageFormat; // 图像文件的格式
    private String defaultImageFormat = "png";

    // 构造函数
    public RobotScreenShot() {
        fileName = "";
        imageFormat = defaultImageFormat;

    }
    // 对屏幕进行拍照
    public void snapShot() {
        // 定义截图存放目录名
        //String dir_name = "screenshot";
        String dir_name = GetTestProperties.getPicDir();
        // 判断是否存在该目录
        if (!(new File( dir_name ).isDirectory())) {
            // 如果不存在则新建一个目录
            new File( dir_name ).mkdir();
        }
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMdd-HHmmss" );
        // 格式化当前时间，例如20200711-165210
        String time = sdf.format( new Date() );
        try {
            // 拷贝屏幕到一个BufferedImage对象screenshot
            BufferedImage screenshot = (new Robot())
                    .createScreenCapture(new Rectangle(0, 0,
                            (int) d.getWidth(), (int) d.getHeight()));
            // serialNum++;
            // 根据文件前缀变量和文件格式变量，自动生成文件名
            String name = dir_name + File.separator + fileName + time + "."
                    + imageFormat;
            File f = new File(name);
            // 将screenshot对象写入图像文件
            ImageIO.write(screenshot, imageFormat, f);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 对屏幕进行拍照,追加方法名
     *
     * @param methodname
     */
    public void snapShot(String methodname) {
        // 定义了截图存放目录名
        // String dir_name = "screenshot";
        String dir_name = GetTestProperties.getPicDir();
        // 判断是否存在该目录
        if (!(new File(dir_name).isDirectory())) {
            // 如果不存在则新建一个目录
            new File(dir_name).mkdir();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
        // 格式化当前时间，例如20200711-165210
        String time = sdf.format(new Date());
        try {
            // 拷贝屏幕到一个BufferedImage对象screenshot
            BufferedImage screenshot = (new Robot())
                    .createScreenCapture(new Rectangle(0, 0,
                            (int) d.getWidth(), (int) d.getHeight()));

            // serialNum++;
            // 根据文件前缀变量和文件格式变量，自动生成文件名
            String name = dir_name + File.separator + fileName + time + "."
                    + imageFormat;
            File f = new File(name);
            // 将screenshot对象写入图像文件
            ImageIO.write(screenshot, imageFormat, f);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
