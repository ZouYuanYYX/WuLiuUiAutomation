package com.wuliu.utils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.wuliu.data.FinalData;
import com.wuliu.entity.AndroidDevice;
import com.wuliu.entity.AppiumServier;
import com.wuliu.testcase.TestCase;

import io.appium.java_client.android.AndroidDriver;

/**
 * webdriver、androiddriver初始化
 * @author joy
 * @date 2017年12月14日
 */

public class DriverInitialUtils {
    public static Process pShipper;
    public static Process pCarrier;
	private static AndroidDevice appShipper = new AndroidDevice(FinalData.SHIPPER_PACKAGNAME(),FinalData.SHIPPER_DEVICE_NAME(),FinalData.SHIPPER_VERSION(),FinalData.SHIPPER_URL());
	private static AppiumServier appiumShipper = new AppiumServier(appShipper,pShipper,FinalData.SHIPPER_PORT(),FinalData.APPIUM_SHIPPER_LOG_PATH());
	    
	private static AndroidDevice appCarrier = new AndroidDevice(FinalData.CARRIER_PACKAGNAME(),FinalData.CARRIER_DEVICE_NAME(),FinalData.CARRIER_VERSION(),FinalData.CARRIER_URL());
	private static AppiumServier appiumCarrier = new AppiumServier(appCarrier,pCarrier,FinalData.CARRIER_PORT(),FinalData.APPIUM_CARRIER_LOG_PATH());
	
    public static WebDriver webDriver;
    public static AndroidDriver appShipperDriver;
    public static AndroidDriver appCarrierDriver;
    public static Actions actions;
    

    public static void webInitial(String browser,String driverPath) {
        if ("ie".equals(browser.toLowerCase().trim())) {
            System.setProperty("webdriver.ie.driver", driverPath);
            webDriver = new InternetExplorerDriver();
            actions = new Actions(webDriver);
        }
        if ("chrome".equals(browser.toLowerCase().trim())) {
            System.setProperty("webdriver.chrome.driver", driverPath);
            webDriver = new ChromeDriver();
            actions = new Actions(webDriver);
        }
        if ("firefox".equals(browser.toLowerCase().trim())) {
            System.setProperty("webdriver.firefox.bin", driverPath);
            webDriver = new FirefoxDriver();
            actions = new Actions(webDriver);
        }
    }
    
    public static void webClose() {
        if (DriverInitialUtils.webDriver != null) {
            DriverInitialUtils.webDriver.quit();
        }
    }
    
    public static void appDriverInitial(String product) {
    	if (product.trim().contains("货主")||product.trim().contains("货运站")) {
    		//开启appium服务
    		appiumShipper.startAppium(35);
    		//打开app
    		appShipperDriver = appShipper.AndroidDriverInitial();
    	}	
    	if (product.trim().contains("车主")||product.trim().contains("司机")) {
    		//开启appium服务
    		appiumCarrier.startAppium(35);
    		//打开app
    		appCarrierDriver = appCarrier.AndroidDriverInitial();
    	}
    }
    
    public static void appClose(String product) {
    	if (product.trim().contains("货主")||product.trim().contains("货运站")) {
    		if (appShipperDriver != null) {
    			appShipperDriver.quit();
    			System.out.println("货主或货运站app已关闭");
    			appiumShipper.stopAppium();
            }        
            
    	}
    	
    	if (product.trim().contains("车主")||product.trim().contains("司机")) {
    		if (appCarrierDriver != null) {
    			appCarrierDriver.quit();
    			System.out.println("车主或司机app已关闭");
    			appiumCarrier.stopAppium();
    		}        
    	}
    }

}
