package com.wuliu.utils;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.wuliu.data.enumdata.Product;

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
        	System.out.print("保存截图失败，具体失败信息为："+e.getMessage());
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
    public static void screenShot(String picturePath,String sheetName, String testCaseId,String product,String keyWords) {
        //如果关闭app或浏览器，则不做截图操作
    	if (!keyWords.trim().contains("关闭")) {
    		if (product.trim().equals(Product.SHIPPER.getName())||product.trim().equals(Product.FREIGHTSTATION.getName())) {
                if (DriverInitialUtils.appShipperDriver != null) {
                    screenShotUtil(DriverInitialUtils.appShipperDriver, picturePath, sheetName, testCaseId);
                } 
            } else if (product.trim().equals(Product.CARRIER.getName())||product.trim().equals(Product.DRIVER.getName())) {
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

}
