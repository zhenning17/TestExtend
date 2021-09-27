package com.util;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;

public class RobotAddin {

    /**
     * 模拟键盘事件
     * @param asc
     *          ,键位在ASCII表对应的值(Enter=10)
     * @throws AWTException
     */
    public void KeyEvent(int asc) throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(asc);
    }

    /**
     * 模拟键盘回车事件
     * @throws AWTException
     */
    public void KeyEventEnter() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress( KeyEvent.VK_ENTER);
    }

    /**
     * 复制变量值到剪贴板，并粘贴
     * @param str
     * @throws AWTException
     */
    public void KeyEventClipboard(String str) throws AWTException {
        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable t = new StringSelection(str);
        cb.setContents(t, null);

        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);

        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }

    /**
     * 模拟浏览器的新tab的操作
     * @throws AWTException
     */
    public void KeyTest() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_T);

        robot.keyPress(KeyEvent.VK_T);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }

}
