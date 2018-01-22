package com.wuliu.element.operation;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.wuliu.utils.LogUtils;

import junit.framework.Assert;

/**
 * 下拉框数据选择
 * @author PC
 * @date 2017年12月25日
 */

public class SelectOperation {
	
	private static Select select;
	
	/**
	 * 通过index选择数据
	 * @param driver
	 * @param by
	 * @param index index表示选中下拉框第几个选项
	 * @param text  表示选中的该项文案是什么
	 */
	public static void getSelectDataByIndex(WebElement element,int index,String text) {
		select = new Select(element);
		//selectByIndex表示选中下拉框第几个选项，从0开始
		select.selectByIndex(index);
		Assert.assertEquals(text, select.getFirstSelectedOption().getText());
		LogUtils.info("选择的第"+index+1+"个下拉选项，文案为"+text);
	}
	/**
	 * 使用下拉列表选项的value属性值进行选中操作
	 * @param driver
	 * @param by
	 * @param value  下拉列表选项的value属性
	 * @param text   表示选中的该项文案是什么
	 */
	public static void getSelectDataByValue(WebElement element,String value,String text) {
		select = new Select(element);
		//selectByValue表示使用下拉列表选项的value属性值进行选中操作
		select.selectByValue(value);
		Assert.assertEquals(text, select.getFirstSelectedOption().getText());
		LogUtils.info("选择的下拉选项，文案为"+text);
	}
	/**
	 * 通过选项的文字来进行选中
	 * @param driver
	 * @param by
	 * @param text
	 */
	public static void getSelectDataByVisibleText(WebElement element,String text) {
		select = new Select(element);
		//selectByVisibleText表示通过选项的文字来进行选中
		select.selectByVisibleText(text);
		Assert.assertEquals(text, select.getFirstSelectedOption().getText());
		LogUtils.info("选择的下拉选项，文案为"+text);
	}

}
