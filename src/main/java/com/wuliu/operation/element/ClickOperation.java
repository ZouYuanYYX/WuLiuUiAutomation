package com.wuliu.operation.element;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.wuliu.data.GetJsonData;
import com.wuliu.utils.LogUtils;


public class ClickOperation {
	
	/**
     * 单击(app端)
     * @param driver
     * @param by
    */
    public static void buttonClick (WebElement element) {
    	element.click();
    }
    
    /**
     * javaScript单击（web端）
     * @param driver
     * @param by
    */
    public static void javaScriptClick (WebDriver driver,WebElement element) {        
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();",element);
    }
    
    /**
     * 移动到元素位置处后再单击（web端）
     * @param driver
     * @param actions
     * @param by
    */
    public static void moveToElementClick (WebElement element,Actions actions) {
    	actions.moveToElement(element).build().perform();
        //鼠标移动到工单工作台上后再做click操作
    	element.click();
    }
    
    /**
     * 多表达式单击，当数据间唯一区别字段在json串里时，可先根据某个元素的Attribute属性捞取所有数据存入list中
     * 再遍历该list，捞取符合特定条件的数据
     * @param driver
     * @param attribute
     * @param key
     * @param transId
     * @param byOne
     * @param byTwo
     */
    public static void multipleExpressionClick (List<WebElement> e,String attribute,String key,String id) {
        for(WebElement el:e){
            String jsonMessage=el.getAttribute(attribute);    
            System.out.println("获取"+attribute+"的属性值"+jsonMessage);            
            //String转为JSON对象
            String actualTransId = GetJsonData.getMultipleMemberJsonData(jsonMessage, key); 
            if(id.equals(actualTransId)){
                System.out.println("找到了元素");
                LogUtils.info("找到了元素");
                el.click();
                break;
            }
            else{
                System.out.println("找不到元素");
                LogUtils.info("找不到元素");
                continue;
            }   
        }        
    }
//    public static void multipleExpressionClick (List<WebElement> e,WebElement element,String attribute,String key,String id) {
//        for(WebElement el:e){
//            String jsonMessage=el.getAttribute(attribute);    
//            System.out.println("获取"+attribute+"的属性值"+jsonMessage);            
//            //String转为JSON对象
//            String actualTransId = GetJsonData.getMultipleMemberJsonData(jsonMessage, key); 
//            if(id.equals(actualTransId)){
//                System.out.println("找到了元素");
//                LogUtils.info("找到了元素");
//                el.click();
//                break;
//            }
//            else{
//                System.out.println("找不到元素");
//                LogUtils.info("找不到元素");
//                continue;
//            }   
//        }        
//    }
    /**
     * 对某个元素做双击操作，目前只写了web端
     * @param driver
     * @param by
     * @param action
     */
    public static void doubleClick(WebElement element,Actions action) {
    	action.doubleClick(element).build().perform();
    }

}
