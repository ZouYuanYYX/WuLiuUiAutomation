package com.wuliu.entity;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

import com.wuliu.data.enumdata.Browser;
/**
 * 浏览器driver初始化
 * @author joy
 * @date 2018年3月15日
 */
public class BrowserDevice {
	/**
	 * 待测试浏览器名称
	 */
	private String name;
	public WebDriver driver;
	public Actions actions;
	
	public void initialDriver() {
		if (Browser.IE.getName().equals(name)) {
	        System.setProperty("webdriver.ie.driver", "src/main/resources/drivers/IEDriverServer.exe");
	        driver = new InternetExplorerDriver();
	        driver.manage().window().maximize();
	        actions = new Actions(driver);
	     }
	     if (Browser.CHROME.getName().equals(name)) {
	        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
	        driver = new ChromeDriver();
	        actions = new Actions(driver);
	     }
	     if (Browser.FIREFOX.getName().equals(name)) {
	        driver = new FirefoxDriver();
	        driver.manage().window().maximize();
	        actions = new Actions(driver);
	     }
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
