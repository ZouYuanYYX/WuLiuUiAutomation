package com.wuliu.operation;


import org.openqa.selenium.WebDriver;
/**
 * 页面处理，如弹窗、断言、切换窗口、frame等
 * @author joy
 * @date 2018.03.24
 */
public interface IPageOperation {
	
	/**
	 * 处理页面上的弹窗，弹出框只有一个确定按钮，为alert，弹出框有确定和取消按钮为confirm
	 * 弹框只有一个确定按钮
	 * @param driver
	 */
	public void alertHander(WebDriver driver);
	
	/**
	 * 弹框有确定、取消按钮
	 * @param driver
	 *  @param message，需要操作确定还是取消按钮
	 */
	public void confirmHander(WebDriver driver,String message);
	
	/**
	 * 断言页面是否包含某个字符串的方法
	 * @param driver
	 * @param assertString
	 */
	public void assertString(WebDriver driver,String assertString);
	
	/**
     * 切入iframe（web端）
     * @param driver
     * @param iframe,需要切入的iframe的名字
    */
    public void intoIframe (WebDriver driver,String iframe);
    
    /**
     * 切出iframe（web端）
     * @param driver
    */
    public void outIframe (WebDriver driver);
    
    /**
	 * 切换窗口（web端）
	 * @param driver
	 * @param windowPage，需要切换到第几个窗口
	 */
	public void switchWindow (WebDriver driver,String windowPage);
	
	/**
	 * 关闭窗口，windowNum为需要关闭的窗口个数，关闭窗口时从右往左依次关闭
	 * @param driver
	 * @param windowNum
	 */
	public void closeWindow(WebDriver driver,String windowNum);
	
	/**
     * 刷新页面
     * @param driver
     */
    public void refreshBrowser (WebDriver driver);

}
