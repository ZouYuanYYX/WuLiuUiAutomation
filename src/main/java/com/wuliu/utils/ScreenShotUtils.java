package com.wuliu.utils;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * 屏幕截图工具
 * @author joy
 * @date 2018年1月2日
 */

public class ScreenShotUtils {
    
    /**
     * 屏幕截图工具，截的图以测试用例序号命名
     * @param driver
     * @param path
     * @param testCaseId
     */
    public static void screenShotUtil(WebDriver driver,String path,String sheetName,String testCaseId) {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {           
            //将截图保存，并以测试用例id命名图片
            FileOutputStream out = new FileOutputStream(path+"/"+sheetName+"_"+testCaseId+".png");
            FileUtils.copyFile(scrFile, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 判断当前截图app还是web端
     * @param picturePath
     * @param sheetName
     * @param testCaseId
     * @param product
     */
    public static void screenShot(String picturePath,String sheetName, String testCaseId,String product) {
        if (product.trim().contains("货主")||product.trim().contains("货运站")) {
            if (DriverInitialUtils.appShipperDriver != null) {
                screenShotUtil(DriverInitialUtils.appShipperDriver, picturePath, sheetName, testCaseId);
            }
        } else if (product.trim().contains("车主")||product.trim().contains("司机")) {
            if (DriverInitialUtils.appCarrierDriver != null) {
                screenShotUtil(DriverInitialUtils.appCarrierDriver, picturePath, sheetName, testCaseId);
            }
        } else {
            if (DriverInitialUtils.webDriver != null) {
                screenShotUtil(DriverInitialUtils.webDriver, picturePath, sheetName, testCaseId);
            }
        }
    }

}