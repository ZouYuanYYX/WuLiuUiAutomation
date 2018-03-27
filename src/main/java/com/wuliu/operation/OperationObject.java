package com.wuliu.operation;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.wuliu.MainFunction;
import com.wuliu.data.GetJsonData;
import com.wuliu.utils.DriverInitialUtils;
import com.wuliu.utils.LogUtils;

import junit.framework.Assert;
/**
 * 对页面及元素的操作方法
 * @author joy
 * @date 2018.03.25
 */
public class OperationObject implements IElementOperation,IPageOperation{
	private Select select;
	private Alert alert;
	
	//*************************实现IElementOperation接口的相关方法***************************
	
	@Override
	public void buttonClick(WebElement element) {
		element.click();
	}

	@Override
	public void javaScriptClick(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();",element);
	}

	@Override
	public void doubleClick(WebElement element, Actions actions) {
		actions.doubleClick(element).build().perform();
	}

	@Override
	public void moveToElementClick(WebElement element, Actions actions) {
		actions.moveToElement(element).build().perform();
        //鼠标移动到工单工作台上后再做click操作
    	element.click();
	}

	@Override
	public void inputText(WebElement element, String sendKey) {
		element.sendKeys(sendKey);
	}

	@Override
	public void clearText(WebElement element) {
		element.clear();
	}

	@Override
	public void getSelectDataByIndex(WebElement element, int index, String text) {
		select = new Select(element);
		//selectByIndex表示选中下拉框第几个选项，从0开始
		select.selectByIndex(index);
		Assert.assertEquals(text, select.getFirstSelectedOption().getText());
		LogUtils.info("选择的第"+index+1+"个下拉选项，文案为"+text);
	}

	@Override
	public void getSelectDataByValue(WebElement element, String value, String text) {
		select = new Select(element);
		//selectByValue表示使用下拉列表选项的value属性值进行选中操作
		select.selectByValue(value);
		Assert.assertEquals(text, select.getFirstSelectedOption().getText());
		LogUtils.info("选择的下拉选项，文案为"+text);
	}

	@Override
	public void getSelectDataByVisibleText(WebElement element, String text) {
		select = new Select(element);
		//selectByVisibleText表示通过选项的文字来进行选中
		select.selectByVisibleText(text);
		Assert.assertEquals(text, select.getFirstSelectedOption().getText());
		LogUtils.info("选择的下拉选项，文案为"+text);
	}
	
	//*************************自定义的其他方法***************************
	
	/**
     * 使用adb命令输入文案，只支持app端，输入英文和数字
     * @param element
     * @param product，表示哪个app产品需要使用该方式输入文案
     * @param sendKey，表示待输入的文案
     */
	public void adbInputText(WebElement element, String product, String sendKey) {
		//先单击某个元素，再对该元素做输入操作
    	element.click();
    	DriverInitialUtils.appAdbInputText(product, sendKey);
	}
	
	/**
     * 根据属性单击单击，当数据间唯一区别字段在json串里时，可先根据某个元素的Attribute属性捞取所有数据存入list中
     * 再遍历该list，捞取符合特定条件的数据
     * @param e 根据某个元素的Attribute属性捞取的所有数据
     * @param attribute 属性
     * @param key
     * @param id 工单id
     */
    public static void attributeClick (List<WebElement> e,String attribute,String key,String id) {
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
    
    /**
     * 切入iframe（web端）
     * @param driver
     * @param iframe  iframe名称
     * @param transId 工单id
    */
    public static void intoIframeContainsParams (WebDriver driver,String iframe,String transId) {
        String frame = MessageFormat.format(iframe, transId);
        driver.switchTo().frame(frame);
    }

    //*************************实现IPageOperation接口的相关方法***************************
    
	@Override
	public void alertHander(WebDriver driver) {
		alert = driver.switchTo().alert();
		alert.accept();
	}

	@Override
	public void confirmHander(WebDriver driver, String message) {
		alert = driver.switchTo().alert();
		if ("确定".equals(message.trim())) {
			alert.accept();
		}
		if ("取消".equals(message.trim())) {
			alert.dismiss();
		}
	}

	@Override
	public void assertString(WebDriver driver, String assertString) {
		Assert.assertTrue(driver.getPageSource().contains(assertString));
	}

	@Override
	public void intoIframe(WebDriver driver, String iframe) {
		try {
            driver.switchTo().frame(iframe);
        } catch (Exception e) {
        	MainFunction.result = false;
            LogUtils.error("iframe不存在或错误");
            e.printStackTrace();
        }
	}

	@Override
	public void outIframe(WebDriver driver) {
		 driver.switchTo().defaultContent();
	}

	@Override
	public void switchWindow(WebDriver driver, String windowPage) {
		int windowNum = Integer.parseInt(windowPage);
        //切换窗口
        //得到当前窗口的set集合
        Set<String> winHandles=driver.getWindowHandles();
        //将set集合存入list对象
        List<String> it=new ArrayList<String>(winHandles);
        //isExist用来判断窗口是否已经存在
        boolean isExist = windowIsExist(it,windowNum);
        long start = System.currentTimeMillis();
        while(!isExist) {
        	long end = System.currentTimeMillis();
        	//超过10秒窗口还不存在，则直接报错
			if (end-start > 10000) {
                System.out.println("窗口不存在，无法打开");
                break;
			} 
			winHandles=driver.getWindowHandles();
			it=new ArrayList<String>(winHandles);
			isExist = windowIsExist(it,windowNum);
        }
        //窗口存在的话再切换
        if(isExist) {
        	driver.switchTo().window(it.get(windowNum));
        }
	}
	
	/**
	 * 判断窗口是否已存在
	 * @param it 存放所有窗口的list集合
	 * @param windowNum，窗口号
	 * @return
	 */
	private boolean windowIsExist(List<String> it,int windowNum) {
		//it.size()从1开始，windowNum从0开始，windowNum为1则表示有2个窗口
		//若小于等于则表示新窗口没有打开
		if( it.size()<= windowNum) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void closeWindow(WebDriver driver, String windowNum) {
		for (int i = Integer.parseInt(windowNum);i>=1;i--) {
	        for(String winHandle : driver.getWindowHandles()){   
	            //获取当前句柄
	            String winHandleBefore = driver.getWindowHandle();
	            if (winHandle.equals(winHandleBefore)) {
	                driver.close();
	                break; 
	            } 
	        } 
            switchWindow(driver,Integer.toString(i-1));
	    }
	}
	
	/**
     * 刷新页面
     * @param map
     */
	@Override
    public void refreshBrowser (WebDriver driver) {
    	driver.navigate().refresh();
    }
	
//	/**
//  * 切换至窗口并关闭（web端）
//  * @param driver
//  * @param windowPage
//  */
// public static void switchWindowAndClose (WebDriver driver,String windowPage) {
//     int windowNum = Integer.parseInt(windowPage);
//     //切换窗口
//     Set<String> winHandles=driver.getWindowHandles();//得到当前窗口的set集合
//     List<String> it=new ArrayList<String>(winHandles);//将set集合存入list对象
//     String winHandleCurrent = driver.getWindowHandle();
//     for(String winHandle : it){
//         if (winHandleCurrent.equals(winHandle)) {
//             driver.close();
//             break;
//         } else {
//             driver.switchTo().window(it.get(windowNum));
//             driver.close();
//             break;
//         }
//     }
// }

}
