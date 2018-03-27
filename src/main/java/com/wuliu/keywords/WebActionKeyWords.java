package com.wuliu.keywords;

import java.util.Map;
import java.util.Set;

import org.openqa.selenium.Cookie;

import com.wuliu.MainFunction;
import com.wuliu.data.GetDataFromMySql;
import com.wuliu.data.enumdata.ExcelHeader;
import com.wuliu.entity.ElementObject;
import com.wuliu.entity.EmptyException;
import com.wuliu.operation.OperationObject;
import com.wuliu.utils.DriverInitialUtils;
import com.wuliu.utils.LogUtils;
import com.wuliu.utils.StringUtils;


/**
 * web端关键字，区分app端和web端，因为可以直接使用该代码应用于其他项目，
 * 不需要被小二、水泥电商等条件限制死
 * @author joy
 * @date 2017年12月14日
 */

public class WebActionKeyWords extends OperationObject{
	
	ElementObject element = new ElementObject();
	/**
	 * excel表头名称的枚举值，value1至elementLocation2均为参数名称
	 */
	private String value1 = ExcelHeader.VALUE1.getName();
	private String value2 = ExcelHeader.VALUE2.getName();
	private String value3 = ExcelHeader.VALUE3.getName();
	private String value4 = ExcelHeader.VALUE4.getName();
	private String elementLocation1 = ExcelHeader.ELEMENTLOCATION1.getName();
	/**
	 * 无参构造函数
	 */
	public WebActionKeyWords() {
		
	}	
	/**
	 * 选择浏览器打开，需要从excel的value1单元格中传入要打开的浏览器名称
	 * @param map
	 */
    public void openBrowser (Map<String,String> map) {
        try {
        	String browser = map.get(value1);
        	DriverInitialUtils.webInitial(browser);
        	LogUtils.info("浏览器打开成功");
        } catch (Exception e) {
        	MainFunction.result = false;
        	LogUtils.error("浏览器打开失败");
        	e.printStackTrace();
        }        
    }
    /**
     * 输入url，需要从excel的value1单元格中传入要输入的url地址
     * @param map
     */
    public void openURL (Map<String,String> map) {
    	String url = map.get(value1);	
    	DriverInitialUtils.webDriver.get(url);
        LogUtils.info("浏览器访问网址："+url);             
    }
    
    public void closeBrowser (Map<String,String> map) {
        DriverInitialUtils.webClose();
        LogUtils.info("浏览器关闭成功");
    }
    /**
     * 休眠，需要从excel的value1单元格中传入要休眠的时间
     * @param map
     */
    public void sleep (Map<String,String> map) {
        try {
        	String time = map.get(value1);
        	if(StringUtils.isEmpty(time)){
        		throw new EmptyException("value1为空");
        	} else {
        		Thread.sleep(Integer.parseInt(time));
                LogUtils.info("休眠"+Integer.parseInt(time)/1000+"秒成功");
        	}
        } catch (Exception e) {
        	MainFunction.result = false;
        	LogUtils.error("线程休眠时出现异常，具体异常信息："+e.getMessage());
            e.printStackTrace();
        }
    }
    /**
     * 使用cookie登陆，需要从excel的value1、value2、value3、value4单元格中传入要使用的cookie信息
     * @param map
     */
    public void addCookie (Map<String,String> map) {
    	try {
    		Cookie ck1 = new Cookie (map.get(value1),map.get(value2));
            if (StringUtils.isEmpty(map.get(value3)) || StringUtils.isEmpty(value4)) {
            	DriverInitialUtils.webDriver.manage().addCookie(ck1);
            } else {
                Cookie ck2 = new Cookie (map.get(value3),map.get(value4));
                DriverInitialUtils.webDriver.manage().addCookie(ck1);
                DriverInitialUtils.webDriver.manage().addCookie(ck2);
            }
            Set<Cookie> coo = DriverInitialUtils.webDriver.manage().getCookies();
            System.out.println("打印cookie数据："+coo);
            LogUtils.info("使用cookie"+coo+"登录");
    	} catch (Exception e) {
    		MainFunction.result = false;
        	LogUtils.error("cookie登录出现异常，具体异常信息："+e.getMessage());
            e.printStackTrace();
    	}       
    }
    /**
     * 刷新浏览器
     * @param map
     */
    public void refreshBrowser (Map<String,String> map) {
    	refreshBrowser(DriverInitialUtils.webDriver);
    }
    
    /**
     * 往输入框中输入值，需要从excel的value1单元格中传入要输入值
     * @param map
     */
    public void inputText (Map<String,String> map) {
        try { 
        	if(StringUtils.isEmpty(map.get(value1))){
        		throw new EmptyException("value1为空");
        	} else {
        		inputText(element.getElement(DriverInitialUtils.webDriver, map.get(elementLocation1), map.get(value1)),map.get(value1));
                LogUtils.info("app输入框成功输入"+value1);
        	}
        } catch (Exception e) {
        	MainFunction.result = false;
	        LogUtils.error("往输入框里输入"+value1+"出现异常，具体异常信息为："+e.getMessage());
	        e.printStackTrace();
        }   	
    }
    
    /**
     * 往输入框中输入工单id，需要从excel的value1单元格中传入手机号，
     * 再根据手机号从数据库里查出工单id
     * @param map
     */
    public void inputTransId (Map<String,String> map) {
    	String cellphone = map.get(value1);
    	String transId = GetDataFromMySql.userTransId(cellphone);
    	try {
    		if(StringUtils.isEmpty(cellphone)){
    			throw new EmptyException("value1为空");
        	} else {
        		inputText(element.getElement(DriverInitialUtils.webDriver,
        				map.get(elementLocation1),map.get(value2)), transId);
                LogUtils.info("app输入框成功输入"+transId);
        	}
        } catch (Exception e) {
        	MainFunction.result = false;
	        LogUtils.error("往输入框里输入"+transId+"出现异常，具体异常信息为："+e.getMessage());
	        e.printStackTrace();
        }   	
    }
    
    /**
     * 往输入框中输入水泥电商id，需要从excel的value1单元格中传入手机号，
     * 再根据手机号从数据库里查出水泥电商id
     * @param map
     */
    public void inputShuiNiId (Map<String,String> map) {
    	String cellphone = map.get(value1);
    	String shuiNiId = GetDataFromMySql.userShuiNiId(cellphone);
    	try {
    		if(StringUtils.isEmpty(cellphone)){
    			throw new EmptyException("value1为空");
        	} else {
        		inputText(element.getElement(DriverInitialUtils.webDriver,
        				map.get(elementLocation1),map.get(value2)), shuiNiId);
                LogUtils.info("app输入框成功输入"+shuiNiId);
        	}
        } catch (Exception e) {
        	MainFunction.result = false;
	        LogUtils.error("往输入框里输入"+shuiNiId+"出现异常，具体异常信息为："+e.getMessage());
	        e.printStackTrace();
        }   	
    }
    
    /**
     * 清空输入框
     * @param map
     */
    public void clearText (Map<String,String> map) {
    	try {
        	clearText(element.getElement(DriverInitialUtils.webDriver,map.get(elementLocation1),map.get(value1)));
            LogUtils.info("web输入框成功清空");
        } catch (Exception e) {
        	MainFunction.result = false;
	        LogUtils.error("输入框清空出现异常，具体异常信息为："+e.getMessage());
	        e.printStackTrace();
        }  
    }

    /**
     * 单击
     * @param map
     */
    public void buttonClick (Map<String,String> map) {
        try {
            buttonClick(element.getElement(DriverInitialUtils.webDriver,map.get(elementLocation1),map.get(value1)));
            LogUtils.info("web端单击页面元素"+map.get(elementLocation1)+"成功");
        } catch (Exception e) {
        	MainFunction.result = false;
	        LogUtils.error("单击页面元素"+map.get(elementLocation1)+"失败，具体异常信息为："+e.getMessage());
	        e.printStackTrace();
        } 
    }
    
    /**
     * 双击
     * @param map
     */
    public void doubleClick(Map<String,String> map) {
    	try {
            doubleClick(element.getElement(DriverInitialUtils.webDriver,map.get(elementLocation1),map.get(value1)), DriverInitialUtils.actions);
            LogUtils.info("web端双击页面元素"+map.get(elementLocation1)+"成功");
        } catch (Exception e) {
        	MainFunction.result = false;
	        LogUtils.error("双击页面元素"+map.get(elementLocation1)+"失败，具体异常信息为："+e.getMessage());
	        e.printStackTrace();
        } 
    }
    
    /**
     * javaScript单击,适用于某个按钮被另外一个弹层遮挡时的场景，目前仅写了web端
     * 的javaScript单击，app端的遇到后再补充
     * @param map
     */
    public void javaScriptClick (Map<String,String> map) { 
    	try {
            javaScriptClick(DriverInitialUtils.webDriver, element.getElement(DriverInitialUtils.webDriver,map.get(elementLocation1),map.get(value1)));
            LogUtils.info("web端单击页面元素"+map.get(elementLocation1)+"成功");		
    	} catch (Exception e) {
    		MainFunction.result = false;
        	LogUtils.error("单击页面元素"+map.get(elementLocation1)+"失败，具体异常信息为："+e.getMessage());
        	e.printStackTrace();
    	}
    }
    
    /**
     * 移动至元素位置后再单击,适用于子菜单的单击，目前仅写了web端
     * 的单击，app端的遇到后再补充
     * @param map
     */
    public void moveToElementClick (Map<String,String> map) {
    	try {
            moveToElementClick(element.getElement(DriverInitialUtils.webDriver,map.get(elementLocation1),map.get(value1)),DriverInitialUtils.actions);
            LogUtils.info("web端单击页面元素"+map.get(elementLocation1)+"成功");		
    	} catch (Exception e) {
    		MainFunction.result = false;
        	LogUtils.error("单击页面元素"+map.get(elementLocation1)+"失败，具体异常信息为："+e.getMessage());
        	e.printStackTrace();
    	}
    }	
    
    /**
     * 根据工单id属性单击
     * 需要从excel的value1单元格中传入attribute属性，
     * value1单元格中传入key属性，value3单元格中传入手机号
     * @param map
     */
    public void transIdAttributeClick (Map<String,String> map) {
        String attribute = map.get(value1);
        String key = map.get(value2);
        String cellphone = map.get(value3);
    	try {
        	if(StringUtils.isEmpty(attribute)||StringUtils.isEmpty(key)||StringUtils.isEmpty(cellphone)){
        		throw new EmptyException("value1或value2或value3为空");
        	} else {
        		attributeClick(element.getElements(DriverInitialUtils.webDriver,
        				map.get(elementLocation1),map.get(value4)), attribute, key, GetDataFromMySql.userTransId(cellphone));
                LogUtils.info("根据工单属性单击成功");	
        	}
        } catch (Exception e) {
        	MainFunction.result = false;
        	LogUtils.error("根据工单id属性单击失败，具体异常信息为："+e.getMessage());
        	e.printStackTrace();
        }       
    }
    
    /**
     * 根据竞价单id属性单击
     * 需要从excel的value1单元格中传入attribute属性，
     * value1单元格中传入key属性，value3单元格中传入手机号
     * @param map
     */
    public void auctionIdAttributeClick (Map<String,String> map) {
    	String attribute = map.get(value1);
        String key = map.get(value2);
        String cellphone = map.get(value3);
    	try {
        	if(StringUtils.isEmpty(attribute)||StringUtils.isEmpty(key)||StringUtils.isEmpty(cellphone)){
        		throw new EmptyException("value1或value2或value3为空");
        	} else {
        		attributeClick(element.getElements(DriverInitialUtils.webDriver,map.get(elementLocation1),map.get(value4)), 
            	        attribute, key, GetDataFromMySql.userAuctionId(cellphone));
                LogUtils.info("根据竞价单属性单击成功");
        	}
        } catch (Exception e) {
        	MainFunction.result = false;
        	LogUtils.error("根据竞价单属性单击失败，具体异常信息为："+e.getMessage());
        	e.printStackTrace();
        }       
    }
    
    /**
     * 根据托运单id属性单击
     * 需要从excel的value1单元格中传入attribute属性，
     * value1单元格中传入key属性，value3单元格中传入手机号
     * @param map
     */
    public void itemIdAttributeClick (Map<String,String> map) {
    	String attribute = map.get(value1);
        String key = map.get(value2);
        String cellphone = map.get(value3);
    	try {
        	if(StringUtils.isEmpty(attribute)||StringUtils.isEmpty(key)||StringUtils.isEmpty(cellphone)){
        		throw new EmptyException("value1或value2或value3为空");
        	} else {
        		attributeClick(element.getElements(DriverInitialUtils.webDriver,map.get(elementLocation1),map.get(value4)),
            	        attribute, key, GetDataFromMySql.userItemId(cellphone));
                LogUtils.info("根据托运单属性单击成功");	
        	}
        } catch (Exception e) {
        	MainFunction.result = false;
        	LogUtils.error("根据托运单属性单击失败，具体异常信息为："+e.getMessage());
        	e.printStackTrace();
        }       
    }
    
    /**
     * 根据工单id单击，元素的唯一标志需要从数据库里取出工单id
     * 需要从excel的value1单元格中传入手机号
     * @param map
     */
    public void transIdClick (Map<String,String> map) {
    	try {
    		String cellphone = map.get(value1);
        	if(StringUtils.isEmpty(cellphone)){
        		throw new EmptyException("value1为空");
        	} else {
            	buttonClick(element.getElement(DriverInitialUtils.webDriver,
            			map.get(elementLocation1),GetDataFromMySql.userTransId(cellphone))); 
                LogUtils.info("根据工单id单击成功");
        	}
		
        } catch (Exception e) {
        	MainFunction.result = false;
        	LogUtils.error("根据工单id单击失败，具体异常信息为："+e.getMessage());
        	e.printStackTrace();
        }       
    }
    
    /**
     * 根据竞价单id单击，元素的唯一标志需要从数据库里取出竞价单id
     * 需要从excel的value1单元格中传入手机号
     * @param map
     */
    public void auctionIdClick (Map<String,String> map) {
        try {
        	String cellphone = map.get(value1);
        	if(StringUtils.isEmpty(cellphone)){
        		throw new EmptyException("value1为空");
        	} else {
        		buttonClick(element.getElement(DriverInitialUtils.webDriver,map.get(elementLocation1),GetDataFromMySql.userAuctionId(cellphone))); 
                LogUtils.info("根据竞价单id单击成功");
        	}
        } catch (Exception e) {
        	MainFunction.result = false;
        	LogUtils.error("根据竞价单id单击失败，具体异常信息为："+e.getMessage());
        	e.printStackTrace();
        }       
    }
    
    /**
     * 根据托运单id单击，元素的唯一标志需要从数据库里取出托运单id
     * 需要从excel的value1单元格中传入手机号
     * @param map
     */
    public void itemIdClick (Map<String,String> map) {
        try {
        	String cellphone = map.get(value1);
        	if(StringUtils.isEmpty(cellphone)){
        		throw new EmptyException("value1为空");
        	} else {
        		buttonClick(element.getElement(DriverInitialUtils.webDriver,map.get(elementLocation1),GetDataFromMySql.userItemId(cellphone))); 
                LogUtils.info("根据托运单id单击成功");
        	}
        } catch (Exception e) {
        	MainFunction.result = false;
        	LogUtils.error("根据托运单id单击失败，具体异常信息为："+e.getMessage());
        	e.printStackTrace();
        }       
    }
    /**
     * 需要从excel的value1单元格中传入切换的窗口号
     * @param map
     */
    public void switchWindow (Map<String,String> map) {
    	try {
    		String windowPage = map.get(value1);
    		if(StringUtils.isEmpty(windowPage)){
    			throw new EmptyException("value1为空");
        	} else {
        		switchWindow(DriverInitialUtils.webDriver, windowPage);
                LogUtils.info("切换窗口成功");
        	}
        } catch (Exception e) {
        	MainFunction.result = false;
        	LogUtils.error("切换窗口失败，具体异常信息为："+e.getMessage());
        	e.printStackTrace();
        } 
    }
    /**
     * 需要从excel的value1单元格中传入要关闭的窗口个数，从右向左依次关闭
     * @param map
     */
    public void closeWindow (Map<String,String> map) {
        try {
    		String windowNum = map.get(value1);
        	if(StringUtils.isEmpty(windowNum)){
        		throw new EmptyException("value1为空");
        	} else {
        		closeWindow(DriverInitialUtils.webDriver,windowNum);
                LogUtils.info("窗口关闭成功");
        	}
        } catch (Exception e) {
            MainFunction.result = false;
            LogUtils.error("窗口关闭失败，具体异常信息为："+e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * 需要从excel的value1单元格中传入待切入的iframe的名称
     * @param map
     */
    public void intoIframe (Map<String,String> map) {
    	String iframe = map.get(value1);
    	try {
    		if(StringUtils.isEmpty(iframe)){
    			throw new EmptyException("value1为空");
        	} else {
        		intoIframe(DriverInitialUtils.webDriver, iframe);
                LogUtils.info("成功切入iframe"+iframe);
        	}
        } catch (Exception e) {
        	MainFunction.result = false;
        	LogUtils.error("切入"+iframe+"iframe失败，具体异常信息为："+e.getMessage());
        	e.printStackTrace();
        } 
    }
    
    /**
     * 切入含工单id的iframe,待切入的iframe名称中含有工单id，工单id需要从数据库中查出
     * 需要从excel的value1单元格中传入待切入的iframe的名称，value2单元格中传入手机号以便查询工单id
     * @param map
     */
    public void intoIframeContainsTransId (Map<String,String> map) {
    	try {
    		String iframe =map.get(value1);
    		String cellphone =map.get(value2);
    		if(StringUtils.isEmpty(iframe)||StringUtils.isEmpty(cellphone)){
    			throw new EmptyException("value1或value2为空");
        	} else {
        		intoIframeContainsParams(DriverInitialUtils.webDriver, iframe, GetDataFromMySql.userTransId(cellphone));
                LogUtils.info("成功切入iframe");	
        	}
        } catch (Exception e) {
        	MainFunction.result = false;
        	LogUtils.error("切入iframe失败，具体异常信息为："+e.getMessage());
        	e.printStackTrace();
        } 
    }
    
    /**
     * 切出iframe
     * @param map
     */
    public void outIframe (Map<String,String> map) {
    	try {
    		outIframe(DriverInitialUtils.webDriver);
            LogUtils.info("成功切出iframe");		
        } catch (Exception e) {
        	MainFunction.result = false;
        	LogUtils.error("切出iframe失败，具体异常信息为："+e.getMessage());
        	e.printStackTrace();
        } 
    }
    
    /**
     * 断言
     * @param map
     */
    public void assertString(Map<String,String> map) {
    	try {
    		String assertString = map.get(value1);
    		if(StringUtils.isEmpty(assertString)){
    			throw new EmptyException("value1为空");
        	} else {
        		assertString(DriverInitialUtils.webDriver, assertString);
                LogUtils.info("成功断言关键字“"+assertString+"”");
        	}
    	} catch (Exception e) {
    		MainFunction.result = false;
        	LogUtils.error("出现断言失败，具体断言败信息："+e.getMessage());
        	e.printStackTrace();
    	}
	}
    
    /**
     * 系统弹出的确认、取消框处理
     * @param map
     */
    public void confirmHander(Map<String,String> map) {
    	try {
    		String message = map.get(value1);
    		if(StringUtils.isEmpty(message)){
    			throw new EmptyException("value1为空");
        	} else {
        		confirmHander(DriverInitialUtils.webDriver, message);
                LogUtils.info("网页弹出确认、取消按钮框，已点击“"+message+"”按钮");
        	}
    	} catch (Exception e) {
    		MainFunction.result = false;
        	LogUtils.error("网页弹出框点击失败，具体异常信息为："+e.getMessage());
        	e.printStackTrace();
    	}
	}
    
    /**
     * 使用index选择数据
     * 需要从excel的value1单元格中传入index，value2单元格中传入下拉选项的文案
     * @param map
     */
    public void getSelectDataByIndex(Map<String,String> map) {
    	try {
    		String index = map.get(value1);
    		String text = map.get(value2);
    		if(StringUtils.isEmpty(index)||StringUtils.isEmpty(text)){
    			throw new EmptyException("value1或value2为空");
        	} else {
        		getSelectDataByIndex(element.getElement(DriverInitialUtils.webDriver,map.get(elementLocation1),index),Integer.parseInt(index), text);
                LogUtils.info("选择的下拉框数据为"+text);
        	}
    	} catch (Exception e) {
    		MainFunction.result = false;
        	LogUtils.error("下拉框数据选择失败，具体异常信息为："+e.getMessage());
        	e.printStackTrace();
    	}
    }
    /**
     * 使用select的value属性选择数据
     * 需要从excel的value1单元格中传入value，value2单元格中传入下拉选项的文案
     * @param map
     */
    public void getSelectDataByValue(Map<String,String> map) {
    	try {
    		String value = map.get(value1);
    		String text = map.get(value2);
    		if(StringUtils.isEmpty(value)||StringUtils.isEmpty(text)){
    			throw new EmptyException("value1或value2为空");
        	} else {
        		getSelectDataByValue(element.getElement(DriverInitialUtils.webDriver,map.get(elementLocation1),value),value, text);
                LogUtils.info("选择的下拉框数据为"+text);
        	}
    	} catch (Exception e) {
    		MainFunction.result = false;
        	LogUtils.error("下拉框数据选择失败，具体异常信息为："+e.getMessage());
        	e.printStackTrace();
    	}
    }
    /**
     * 使用select的选项的文字选择数据
     * 需要从excel的value1单元格中传入要选择的下拉数据的文案
     * @param map
     */
    public void getSelectDataByText(Map<String,String> map) {
    	try {
    		String text = map.get(value1);
    		if(StringUtils.isEmpty(text)){
    			throw new EmptyException("value1为空");
        	} else {
        		getSelectDataByVisibleText(element.getElement(DriverInitialUtils.webDriver,map.get(elementLocation1),text), text);
                LogUtils.info("选择的下拉框数据为"+text);
        	}
    	} catch (Exception e) {
    		MainFunction.result = false;
        	LogUtils.error("下拉框数据选择失败，具体异常信息为："+e.getMessage());
        	e.printStackTrace();
    	}
    }
}
