package com.wuliu.operation.page;

import java.text.MessageFormat;

import org.openqa.selenium.WebDriver;

import com.wuliu.MainFunction;
import com.wuliu.utils.LogUtils;

public class IframOperation {
	
	/**
     * 切入iframe（web端）
     * @param driver
     * @param iframe
    */
    public static void intoIframe (WebDriver driver,String iframe) {
        try {
            driver.switchTo().frame(iframe);
        } catch (Exception e) {
        	MainFunction.result = false;
            LogUtils.error("iframe不存在或错误");
            e.printStackTrace();
        }

    }
    
    /**
     * 切入iframe（web端）
     * @param driver
     * @param iframe
    */
    public static void intoIframeContainsParams (WebDriver driver,String iframe,String transId) {
        String frame = MessageFormat.format(iframe, transId);
        driver.switchTo().frame(frame);
    }
    /**
     * 切出iframe（web端）
     * @param driver
    */
    public static void outIframe (WebDriver driver) {
        driver.switchTo().defaultContent();
    }

}
