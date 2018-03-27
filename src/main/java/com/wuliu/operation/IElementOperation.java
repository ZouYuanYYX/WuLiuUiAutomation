package com.wuliu.operation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
/**
 * 对页面元素做相应操作的接口，如单击、双击等
 * @author joy
 * @date 2018.03.24
 */

public interface IElementOperation {
	
	/**
	 * 单击
	 * @param element
	 * @return
	 */
	
	public void buttonClick(WebElement element);
	
	/**
	 * javascript单击
	 * @param driver
	 * @param element
	 * @return
	 */
	public void javaScriptClick (WebDriver driver,WebElement element);
	
	/**
	 * 双击
	 * @param element
	 * @param actions
	 */
	public void doubleClick(WebElement element,Actions actions);
	
	/**
	 * 移动至元素后再单击
	 * @param element
	 * @param actions
	 */
	public void moveToElementClick (WebElement element,Actions actions);
	
	/**
	 * 输入
	 * @param element
	 * @param sendKey，待输入的文案
	 */
	public void inputText (WebElement element,String sendKey);
	
	/**
     * 清空输入框
     * @param element
    */
    public void clearText (WebElement element);
    
    /**
	 * 通过index选择数据
	 * @param element元素
	 * @param index index表示选中下拉框第几个选项
	 * @param text  通过index获取的下拉数据文案与text是否一致
	 */
	public void getSelectDataByIndex(WebElement element,int index,String text);
	
	/**
	 * 使用下拉列表选项的value属性值进行选中操作
	 * @param element元素
	 * @param value  下拉列表选项的value属性
	 * @param text   通过value选项获取的下拉数据文案与text是否一致
	 */
	public void getSelectDataByValue(WebElement element,String value,String text);
	
	/**
	 * 通过选项的文字来进行选中
	 * @param element元素
	 * @param text，表示选项的文字
	 */
	public void getSelectDataByVisibleText(WebElement element,String text);

}
