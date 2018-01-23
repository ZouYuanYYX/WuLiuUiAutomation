package com.wuliu.entity;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class AndroidDevice {
	private AndroidDriver driver;
	//安卓包名
	private String packageName;
	//安卓设备名
	private String deviceName;
	//安卓设备版本
	private String deviceVersion;
	private String url;

	public AndroidDevice(String packageName,String deviceName,String deviceVersion,String url) {
		this.packageName = packageName;
		this.deviceName = deviceName;
		this.deviceVersion = deviceVersion;
		this.url = url;	
	}
	
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getDeviceVersion() {
		return deviceVersion;
	}
	public void setDeviceVersion(String deviceVersion) {
		this.deviceVersion = deviceVersion;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	//初始化安卓drier
	public AndroidDriver AndroidDriverInitial() {
        try {
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
	    	capabilities.setCapability("platformVersion", deviceVersion);
	    	
	    	//使用的平台
            capabilities.setCapability(CapabilityType.PLATFORM, "WINDOWS");
	    	
            //将appium超时时间改长
            capabilities.setCapability("newCommandTimeout", 180);
	    	
	    	//设置apk路径
	    	capabilities.setCapability("app", app.getAbsolutePath());
	    	
	    	//每次启动清空session,否则会报不能新建session
            capabilities.setCapability("sessionOverride", true);
            
            //设置键盘为appium的键盘
            capabilities.setCapability("unicodeKeyboard", "True");  
            capabilities.setCapability("resetKeyboard", "True");
	    	
	    	//初始化        
            this.driver = new AndroidDriver(new URL(url), capabilities);
    		return this.driver; 
        } catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.driver; 
    }

}
