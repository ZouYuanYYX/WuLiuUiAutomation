package com.wuliu.element.operation;

import java.text.MessageFormat;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ElementObject {
	
	private String type ;
	private String expression;
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}
	
	public String getExpression() {
		return expression;
	}
	/**
	 * 返回元素(web端)
	 * @param driver
	 * @return
	 */
	public WebElement getElement (WebDriver driver,String expression,String params) {
	    initialElement(expression,params);
	    //显示等待，等待10秒，超过10秒找不到数据就报错
	    WebElement element = (new WebDriverWait(driver,10))
	            .until(new ExpectedCondition<WebElement>(){
	                @Override
                    public WebElement apply(WebDriver d) {
                        return d.findElement(selectElementLocationWays());
                    }
	            });
        return element;
	}

	/**
	 * 返回元素集合
	 * @param driver
	 * @return
	 */
	public List<WebElement> getElements (WebDriver driver,String expression,String params) {
	    initialElement(expression,params);
	    List<WebElement> elements = driver.findElements(selectElementLocationWays());
		return elements;
	}
	
	public void initialElement(String expression,String params) {
    setType(expression.split(",")[0]); 
    String temp = null;
    try {
        String trueValue = MessageFormat.format(expression.split(",")[1], params);
        temp = trueValue;
    } catch(Exception e) {
        temp = expression.split(",")[1];
        e.printStackTrace();
    }finally {
        setExpression(temp);
    }
  }
	
	/**
     * 根据参数key值判断，将什么定位表达式返回，返回值是By类型
     * @param key
     * @param value
     * @return
     */
    private By selectElementLocationWays () {
        By by = null;  
        switch (type.toLowerCase().trim()) {
		case "id" : 
			by = By.id(expression);
			break;
		case "name" : 
			by = By.name(expression);
			break;	
		case "css" : 
			by = By.cssSelector(expression);
			break;
		case "xpath" : 
			by = By.xpath(expression);
			break;
		case "classname" : 
			by = By.className(expression);
			break;
		case "linktext" : 
			by = By.linkText(expression);
			break;
		case "partiallinktext" : 
			by = By.partialLinkText(expression);
			break;
		default:
			by = By.id(expression);
		}
    	return by;
    }

}
