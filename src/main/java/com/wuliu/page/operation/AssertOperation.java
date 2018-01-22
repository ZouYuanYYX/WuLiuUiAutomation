package com.wuliu.page.operation;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;

/**
 * 断言
 * @author PC
 * @date 201年12月22日
 */

public class AssertOperation {
	/**
	 * app端断言页面是否包含某个字符串的方法
	 * @param driver
	 * @param assertString
	 */
	public static void assertString(AndroidDriver driver,String assertString) {
		Assert.assertTrue(driver.getPageSource().contains(assertString));
	}
	
	/**
	 * web端断言页面是否包含某个字符串的方法
	 * @param driver
	 * @param assertString
	 */
	public static void assertString(WebDriver driver,String assertString) {
		Assert.assertTrue(driver.getPageSource().contains(assertString));
	}

}
