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
import com.wuliu.testcase.TestCase;

import io.appium.java_client.android.AndroidDriver;

/**
 * webdriver、androiddriver初始化
 * @author joy
 * @date 2017年12月14日
 */

public class DriverInitialUtils {
    public static WebDriver webDriver;
    public static AndroidDriver appShipperDriver;
    public static AndroidDriver appCarrierDriver;
    public static Process pShipper;
    public static Process pCarrier;
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
    	    appShipperDriver = appInitial(FinalData.SHIPPER(),pShipper,FinalData.SHIPPER_CMD(),FinalData.SHIPPER_DEVICE(),
    				FinalData.SHIPPER_DEVICE_NAME(),FinalData.ANDROID_SHIPPER(),FinalData.SHIPPER_URL());
    	}	
    	if (product.trim().contains("车主")||product.trim().contains("司机")) {
    	    appCarrierDriver = appInitial(FinalData.CARRIER(),pCarrier,FinalData.CARRIER_CMD(),FinalData.CARRIER_DEVICE(),
    				FinalData.CARRIER_DEVICE_NAME(),FinalData.ANDROID_CARRIER(),FinalData.CARRIER_URL());
    	}
    }
    
    public static void appClose(String product) {
    	if (product.trim().contains("货主")||product.trim().contains("货运站")) {
    		if (appShipperDriver != null) {
    			appShipperDriver.quit();
            }        
            if (pShipper != null) {
            	pShipper.destroy();
            }
    	}
    	
    	if (product.trim().contains("车主")||product.trim().contains("司机")) {
    		if (appCarrierDriver != null) {
    			appCarrierDriver.quit();
            }        
            if (pCarrier != null) {
            	pCarrier.destroy();
            }
    	}
    }
    
    /**
     * 货主货运站app打开初始化类
     */   
    private static AndroidDriver appInitial(String packageName,Process p,
    		String cmd,String connectDevice,String deviceName,String version,String url) {
        AndroidDriver driver = null;
        try {
			Runtime.getRuntime().exec(connectDevice);
			//使用cmd命令开启appium服务
	    	p = Runtime.getRuntime().exec(cmd);
	    	//等待35s，使appium完全启动
	    	Thread.sleep(35000);        
	        if(p != null) {
	            System.out.println("appium启动成功");
	            LogUtils.info("appium启动成功");
	        }
	    	//设置apk路径
	    	File classpathRoot = new File(System.getProperty("user.dir"));
	    	File appDir = new File(classpathRoot, "apps");
	    	File app = new File(appDir, packageName);
	    	
	    	//设置自动化相关参数
	    	DesiredCapabilities capabilities = new DesiredCapabilities();
	    	capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
	    	capabilities.setCapability("platformName", "Android");
	    	capabilities.setCapability("deviceName", deviceName);
	    	
	    	//设置安卓系统版本
	    	capabilities.setCapability("platformVersion", version);
	    	
	    	//使用的平台
            capabilities.setCapability(CapabilityType.PLATFORM, "WINDOWS");
	    	
            //将appium超时时间改长
            capabilities.setCapability("newCommandTimeout", 240);
	    	
	    	//设置apk路径
	    	capabilities.setCapability("app", app.getAbsolutePath());
	    	
	    	//每次启动清空session,否则会报不能新建session
            capabilities.setCapability("sessionOverride", true);
            
            //设置键盘为appium的键盘
            capabilities.setCapability("unicodeKeyboard", "True");  
            capabilities.setCapability("resetKeyboard", "True");
	    	
	    	//初始化        
	    	driver = new AndroidDriver(new URL(url), capabilities);
	    	return driver;
		} catch (MalformedURLException e) {
		    TestCase.result = false;
			e.printStackTrace();
		} catch (IOException e) {
		    TestCase.result = false;
			e.printStackTrace();
		} catch (InterruptedException e) {
		    TestCase.result = false;
			e.printStackTrace();
		}
        return driver;
    }

}
