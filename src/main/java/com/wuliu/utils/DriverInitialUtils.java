package com.wuliu.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.wuliu.data.GetPropertiesData;
import com.wuliu.data.enumdata.Product;
import com.wuliu.entity.AndroidDevice;
import com.wuliu.entity.AppiumServier;
import com.wuliu.entity.BrowserDevice;

import io.appium.java_client.android.AndroidDriver;

/**
 * webdriver、androiddriver初始化
 * @author joy
 * @date 2017年12月14日
 */

public class DriverInitialUtils {
	private static AndroidDevice appShipper = new AndroidDevice(GetPropertiesData.shipperPackageName(),GetPropertiesData.shipperDeviceName(),GetPropertiesData.shipperVersion(),GetPropertiesData.shipperUrl());
	private static AppiumServier appiumShipper = new AppiumServier(appShipper,GetPropertiesData.shipperPort(),GetPropertiesData.appiumShipperLogPath());
	    
	private static AndroidDevice appCarrier = new AndroidDevice(GetPropertiesData.carrierPackageName(),GetPropertiesData.carrierDeviceName(),GetPropertiesData.carrierVersion(),GetPropertiesData.carrierUrl());
	private static AppiumServier appiumCarrier = new AppiumServier(appCarrier,GetPropertiesData.carrierPort(),GetPropertiesData.appiumCarrierLogPath());
	
	private static BrowserDevice browserDevice;
	
    public static WebDriver webDriver;
    public static AndroidDriver<?> appShipperDriver;
    public static AndroidDriver<?> appCarrierDriver;
    
    public static Actions actions;
    

    public static void webInitial(String browser) {
    	browserDevice = new BrowserDevice();
    	browserDevice.setName(browser);
    	browserDevice.initialDriver();
    	webDriver = browserDevice.driver;
    	actions = browserDevice.actions;
    }
    
    public static void webClose() {
        if (DriverInitialUtils.webDriver != null) {
            DriverInitialUtils.webDriver.quit();
        }
    }
    
    public static void appDriverInitial(String product) {
    	System.out.println("获取到的枚举类型名称："+Product.SHIPPER.getName());
    	if ( isShipper(product) ) {
    		appiumShipper.startAppium();
    		//打开app
    		appShipperDriver = appShipper.androidDriverInitial();
    	}	
    	if ( isCarrier(product) ) {
    		//开启appium服务
    		appiumCarrier.startAppium();
    		//打开app
    		appCarrierDriver = appCarrier.androidDriverInitial();
    	}
    }
    
    public static void appAdbInputText(String product,String sendKeys) {
    	if ( isShipper(product) ) {
    		appShipper.adbInputText(sendKeys);
    	}	
    	if ( isCarrier(product) ) {
    		appCarrier.adbInputText(sendKeys);
    	}
    }
    
    /**
	 * ((AppiumDriver)driver).closeApp(); // Close the app which was provided in the capabilities at session creation   
     *((AppiumDriver)driver).close(); // from *RemoteWebDriver.java*, used to close the current browser page  
     *((AppiumDriver)driver).quit(); // quits the session created between the client and the server
	 */
    public static void appClose() {
    	if (appShipperDriver != null) {
			appShipperDriver.closeApp();
			System.out.println("货主或货运站app已关闭");
			appiumShipper.stopAppium();
        }
    	if (appCarrierDriver != null) {
			appCarrierDriver.closeApp();
			System.out.println("车主或司机app已关闭");
			appiumCarrier.stopAppium();
		} 
    }
    
    public static boolean isShipper(String product) {
    	if (Product.SHIPPER.getName().equals(product.trim())||Product.FREIGHTSTATION.getName().equals(product.trim())) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public static boolean isCarrier(String product) {
    	if (Product.CARRIER.getName().equals(product.trim())||Product.DRIVER.getName().equals(product.trim())) {
    		return true;
    	} else {
    		return false;
    	}
    }

}
