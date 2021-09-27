package com.control;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WebTable {
    private WebDriver driver;

    public WebTable(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * 从一个table的单元格中得到文本值.
     * @param by
     * @param row
     * @param col
     * @return
     */
    public String getCellText(By by, int row, int col) {
        // 得到table元素对象
        WebElement table = driver.findElement(by);

        // 得到table表中所有行对象，并得到所要查询的行对象。
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        WebElement theRow = rows.get(row);
        // 调用getCell方法得到对应的列对象，然后得到要查询的文本。
        String text = getCell(theRow, col).getText();
        return text;
    }
    private WebElement getCell(WebElement Row, int col) {
        List<WebElement> cells;
        WebElement target = null;
        // 列里面有"<th>"、"<td>"两种标签，所以分开处理。
        if (Row.findElements(By.tagName("th")).size() > 0) {
            cells = Row.findElements(By.tagName("th"));
            target = cells.get(col);
        }
        if (Row.findElements(By.tagName("td")).size() > 0) {
            cells = Row.findElements(By.tagName("td"));
            target = cells.get(col);
        }
        return target;
    }
}
