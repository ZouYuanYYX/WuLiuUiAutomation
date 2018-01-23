package com.wuliu.operation.element;

import org.openqa.selenium.WebElement;


/**
 * H5页面上存在的各种操作，如输入文本框、单击按钮等
 * @author PC
 * @date 2017年12月20
 */

public class InputTextOperation {
	
	/**
     * 输入
     * @param driver
     * @param by
     * @param sendKey
    */
    public static void inputText (WebElement element,String sendKey) {
        element.sendKeys(sendKey);
    }
        
    /**
     * 清空输入框
     * @param driver
     * @param by
    */
    public static void clearText (WebElement element) {
    	element.clear();
    }
    
}
