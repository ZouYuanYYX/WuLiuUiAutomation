package com.wuliu.operation.page;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

/**
 * 处理页面上的弹窗，弹出框只有一个确定按钮，为alert，弹出框有确定和取消按钮为confirm
 * @author PC
 *
 */

public class AlertOperation {
	private static Alert alert;
	/**
	 * 弹框只有一个确定按钮
	 * @param driver
	 */
	public static void alertHander(WebDriver driver) {
		alert = driver.switchTo().alert();
		alert.accept();
	}
	/**
	 * 弹框有确定、取消按钮
	 * @param driver
	 */
	public static void confirmHander(WebDriver driver,String message) {
		alert = driver.switchTo().alert();
		if ("确定".equals(message.trim())) {
			alert.accept();
		}
		if ("取消".equals(message.trim())) {
			alert.dismiss();
		}
	}
}
