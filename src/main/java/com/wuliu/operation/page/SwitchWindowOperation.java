package com.wuliu.operation.page;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;

public class SwitchWindowOperation {
	
	/**
	 * 切换窗口（web端）
	 * @param driver
	 * @param windowPage
	 */
	public static void switchWindow (WebDriver driver,String windowPage) {
        int windowNum = Integer.parseInt(windowPage);
        //切换窗口
        Set<String> winHandles=driver.getWindowHandles();//得到当前窗口的set集合
        List<String> it=new ArrayList<String>(winHandles);//将set集合存入list对象
        driver.switchTo().window(it.get(windowNum));
    }
	
//	/**
//     * 切换至窗口并关闭（web端）
//     * @param driver
//     * @param windowPage
//     */
//    public static void switchWindowAndClose (WebDriver driver,String windowPage) {
//        int windowNum = Integer.parseInt(windowPage);
//        //切换窗口
//        Set<String> winHandles=driver.getWindowHandles();//得到当前窗口的set集合
//        List<String> it=new ArrayList<String>(winHandles);//将set集合存入list对象
//        String winHandleCurrent = driver.getWindowHandle();
//        for(String winHandle : it){
//            if (winHandleCurrent.equals(winHandle)) {
//                driver.close();
//                break;
//            } else {
//                driver.switchTo().window(it.get(windowNum));
//                driver.close();
//                break;
//            }
//        }
//    }
	/**
	 * 关闭窗口，windowNum为需要关闭的窗口个数，关闭窗口时从右往左依次关闭
	 * @param driver
	 * @param windowNum
	 */
	public static void closeWindow(WebDriver driver,String windowNum) {
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

}
