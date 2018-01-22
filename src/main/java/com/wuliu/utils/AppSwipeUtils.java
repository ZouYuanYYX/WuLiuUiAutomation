package com.wuliu.utils;

import org.openqa.selenium.Dimension;

import io.appium.java_client.android.AndroidDriver;

/**
 * app页面滑动工具
 * @author PC
 * @date 2017年12月20日
 */

public class AppSwipeUtils {
	
	public static void swapApp(AndroidDriver driver, String swapType) {
    	Dimension d=driver.manage().window().getSize();
		int x = d.getWidth();
		int y = d.getHeight();
		if (swapType.contains("左往右")) {
			swipeLeftToRight(driver,x,y) ;			
    	}
    	if (swapType.contains("右往左")) {
    		swipeRightToLeft(driver,x,y) ;			
    	}
    	if (swapType.contains("上往下")) {
    		swipeUpToDown(driver,x,y) ;			
    	}
    	if (swapType.contains("下往上")) {
    		swipeDownToUp(driver,x,y) ;			
    	}
	}
	
	//从左往右滑
	private static void swipeLeftToRight(AndroidDriver appDriver,int x,int y){
		appDriver.swipe(x*9/10, y/2, x/10, y/2, 500);
	}
	//从右往左滑
	private static void swipeRightToLeft (AndroidDriver appDriver,int x,int y) {
		appDriver.swipe(x/10, y/2, x*9/10, y/2, 500);
	}
 	
	//从上往下滑
	private static void swipeUpToDown(AndroidDriver appDriver,int x,int y){
		appDriver.swipe(x/2, y/10, x/2, y*9/10, 500);
	}
	//从下往上滑
	private static void swipeDownToUp(AndroidDriver appDriver,int x,int y){
		appDriver.swipe(x/2, y*9/10, x/2, y/10, 500);
	}	
}
