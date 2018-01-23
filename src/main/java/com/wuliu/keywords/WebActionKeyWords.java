package com.wuliu.keywords;

import java.util.Set;

import org.openqa.selenium.Cookie;
import com.wuliu.data.GetDataFromMySql;
import com.wuliu.entity.ElementObject;
import com.wuliu.operation.element.ClickOperation;
import com.wuliu.operation.element.InputTextOperation;
import com.wuliu.operation.element.SelectOperation;
import com.wuliu.operation.page.AlertOperation;
import com.wuliu.operation.page.AssertOperation;
import com.wuliu.operation.page.IframOperation;
import com.wuliu.operation.page.SwitchWindowOperation;
import com.wuliu.testcase.TestCase;
import com.wuliu.utils.DriverInitialUtils;
import com.wuliu.utils.LogUtils;


/**
 * web端关键字，区分app端和web端，因为可以直接使用该代码应用于其他项目，
 * 不需要被小二、水泥电商等条件限制死
 * @author joy
 * @date 2017年12月14日
 */

public class WebActionKeyWords {
	
	ElementObject element = new ElementObject();
	//无参构造函数
	public WebActionKeyWords() {
		
	}	
	
    public void openBrowser (String product,String browser,String driverPath,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
        try {
        	DriverInitialUtils.webInitial(browser, driverPath);
        	LogUtils.info("浏览器打开成功");
        } catch (Exception e) {
        	TestCase.result = false;
        	LogUtils.error("浏览器打开失败");
        	e.printStackTrace();
        }        
    }
    
    public void openURL (String product,String url,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
    		DriverInitialUtils.webDriver.get(url);
        	LogUtils.info("浏览器访问网址："+url);             
    }
    
    public void closeBrowser (String product,String url,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
        DriverInitialUtils.webClose();
        LogUtils.info("浏览器关闭成功");
    }
    
    public void sleep (String product,String time,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
        try {        
            Thread.sleep(Integer.parseInt(time));
            LogUtils.info("休眠"+Integer.parseInt(time)/1000+"秒成功");
        } catch (Exception e) {
        	TestCase.result = false;
        	LogUtils.error("线程休眠时出现异常，具体异常信息"+e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void addCookie (String product,String value1,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
    	try {
    		Cookie ck1 = new Cookie (value1,value2);
            if (value3 == null || value4 == null) {
            	DriverInitialUtils.webDriver.manage().addCookie(ck1);
            } else {
                Cookie ck2 = new Cookie (value3,value4);
                DriverInitialUtils.webDriver.manage().addCookie(ck1);
                DriverInitialUtils.webDriver.manage().addCookie(ck2);
            }
            Set<Cookie> coo = DriverInitialUtils.webDriver.manage().getCookies();
            System.out.println("打印cookie数据："+coo);
            LogUtils.info("使用cookie"+coo+"登录");
    	} catch (Exception e) {
    		TestCase.result = false;
        	LogUtils.error("cookie登录出现异常，具体异常信息"+e.getMessage());
            e.printStackTrace();
    	}       
    }
    
    /**
     * 输入
     * @param product
     * @param value1
     * @param value2
     * @param value3
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public void inputText (String product,String value1,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
        try { 
            InputTextOperation.inputText(element.getElement(DriverInitialUtils.webDriver, elementLocation1, value1),value1);
            LogUtils.info("app输入框成功输入"+value1);
        } catch (Exception e) {
        	TestCase.result = false;
	        LogUtils.error("往输入框里输入"+value1+"出现异常，具体异常信息为"+e.getMessage());
	        e.printStackTrace();
        }   	
    }
    
    /**
     * 输入的值需要从数据库里查出来（工单id）
     * @param product
     * @param value1
     * @param value2
     * @param value3
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public void inputTransId (String product,String cellphone,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
    	try {
            InputTextOperation.inputText(element.getElement(DriverInitialUtils.webDriver,elementLocation1,value2), GetDataFromMySql.userTransId(cellphone));
            LogUtils.info("app输入框成功输入"+GetDataFromMySql.userTransId(cellphone));
        } catch (Exception e) {
        	TestCase.result = false;
	        LogUtils.error("往输入框里输入"+GetDataFromMySql.userTransId(cellphone)+"出现异常，具体异常信息为"+e.getMessage());
	        e.printStackTrace();
        }   	
    }
    
    /**
     * 清空输入框
     * @param product
     * @param value1
     * @param value2
     * @param value3
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public void clearText (String product,String value1,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
    	try {
            InputTextOperation.clearText(element.getElement(DriverInitialUtils.webDriver,elementLocation1,value1));
            LogUtils.info("web输入框成功清空");
        } catch (Exception e) {
        	TestCase.result = false;
	        LogUtils.error("输入框清空出现异常，具体异常信息为"+e.getMessage());
	        e.printStackTrace();
        }  
    }

    /**
     * 单击
     * @param product
     * @param value1
     * @param value2
     * @param value3
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public void buttonClick (String product,String value1,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
        try {
            ClickOperation.buttonClick(element.getElement(DriverInitialUtils.webDriver,elementLocation1,value1));
            LogUtils.info("web端单击页面元素"+elementLocation1+"成功");
        } catch (Exception e) {
        	TestCase.result = false;
	        LogUtils.error("单击页面元素"+elementLocation1+"失败，具体异常信息为"+e.getMessage());
	        e.printStackTrace();
        } 
    }
    
    /**
     * 双击
     * @param product
     * @param value1
     * @param value2
     * @param value3
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public void doubleClick(String product,String value1,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
    	try {
            ClickOperation.doubleClick(element.getElement(DriverInitialUtils.webDriver,elementLocation1,value1), DriverInitialUtils.actions);
            LogUtils.info("web端双击页面元素"+elementLocation1+"成功");
        } catch (Exception e) {
        	TestCase.result = false;
	        LogUtils.error("双击页面元素"+elementLocation1+"失败，具体异常信息为"+e.getMessage());
	        e.printStackTrace();
        } 
    }
    
    /**
     * javaScript单击,适用于某个按钮被另外一个弹层遮挡时的场景，目前仅写了web端
     * 的javaScript单击，app端的遇到后再补充
     * @param product
     * @param value1
     * @param value2
     * @param value3
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public void javaScriptClick (String product,String value1,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) { 
    	try {
            ClickOperation.javaScriptClick(DriverInitialUtils.webDriver, element.getElement(DriverInitialUtils.webDriver,elementLocation1,value1));
            LogUtils.info("web端单击页面元素"+elementLocation1+"成功");		
    	} catch (Exception e) {
    		TestCase.result = false;
        	LogUtils.error("单击页面元素"+elementLocation1+"失败，具体异常信息为"+e.getMessage());
        	e.printStackTrace();
    	}
    }
    
    /**
     * 移动至元素位置后再单击,适用于子菜单的单击，目前仅写了web端
     * 的单击，app端的遇到后再补充
   	 * @param product
     * @param value1
     * @param value2
     * @param value3
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
    */
    public void moveToElementClick (String product,String value1,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
    	try {
            ClickOperation.moveToElementClick(element.getElement(DriverInitialUtils.webDriver,elementLocation1,value1),DriverInitialUtils.actions);
            LogUtils.info("web端单击页面元素"+elementLocation1+"成功");		
    	} catch (Exception e) {
    		TestCase.result = false;
        	LogUtils.error("单击页面元素"+elementLocation1+"失败，具体异常信息为"+e.getMessage());
        	e.printStackTrace();
    	}
    }	
    
    /**
     * 根据工单id多表达式单击
     * @param product
     * @param attribute
     * @param key
     * @param cellphone
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public void transIdMultipleExpressionClick (String product,String attribute,String key,String cellphone,
    		String value4,String elementLocation1,String elementLocation2) {
        try {
        	ClickOperation.multipleExpressionClick(element.getElements(DriverInitialUtils.webDriver,elementLocation1,value4), attribute, key, GetDataFromMySql.userTransId(cellphone));
            LogUtils.info("根据工单id多表达式单击成功");		
        } catch (Exception e) {
        	TestCase.result = false;
        	LogUtils.error("根据工单id多表达式单击失败，具体异常信息为"+e.getMessage());
        	e.printStackTrace();
        }       
    }
    
    /**
     * 根据竞价单id多表达式单击
     * @param product
     * @param attribute
     * @param key
     * @param cellphone
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public void auctionIdMultipleExpressionClick (String product,String attribute,String key,String cellphone,
    		String value4,String elementLocation1,String elementLocation2) {
        try {
        	ClickOperation.multipleExpressionClick(element.getElements(DriverInitialUtils.webDriver,elementLocation1,value4), 
        	        attribute, key, GetDataFromMySql.userAuctionId(cellphone));
            LogUtils.info("根据竞价单id多表达式单击成功");		
        } catch (Exception e) {
        	TestCase.result = false;
        	LogUtils.error("根据竞价单id多表达式单击失败，具体异常信息为"+e.getMessage());
        	e.printStackTrace();
        }       
    }
    
    /**
     * 根据托运单id多表达式单击
     * @param product
     * @param attribute
     * @param key
     * @param cellphone
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public void itemIdMultipleExpressionClick (String product,String attribute,String key,String cellphone,
    		String value4,String elementLocation1,String elementLocation2) {
        try {
        	ClickOperation.multipleExpressionClick(element.getElements(DriverInitialUtils.webDriver,elementLocation1,value4),
        	        attribute, key, GetDataFromMySql.userItemId(cellphone));
            LogUtils.info("根据托运单id多表达式单击成功");		
        } catch (Exception e) {
        	TestCase.result = false;
        	LogUtils.error("根据托运单id多表达式单击失败，具体异常信息为"+e.getMessage());
        	e.printStackTrace();
        }       
    }
    
    /**
     * 根据工单id单击，元素的唯一标志需要从数据库里取出工单id
     * @param product
     * @param attribute
     * @param key
     * @param cellphone
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public void transIdClick (String product,String cellphone,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
        try {
        	ClickOperation.buttonClick(element.getElement(DriverInitialUtils.webDriver,elementLocation1,GetDataFromMySql.userTransId(cellphone))); 
            LogUtils.info("根据工单id单击成功");		
        } catch (Exception e) {
        	TestCase.result = false;
        	LogUtils.error("根据工单id单击失败，具体异常信息为"+e.getMessage());
        	e.printStackTrace();
        }       
    }
    
    /**
     * 根据竞价单id单击，元素的唯一标志需要从数据库里取出竞价单id
     * @param product
     * @param attribute
     * @param key
     * @param cellphone
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public void auctionIdClick (String product,String cellphone,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
        try {
        	ClickOperation.buttonClick(element.getElement(DriverInitialUtils.webDriver,elementLocation1,GetDataFromMySql.userAuctionId(cellphone))); 
            LogUtils.info("根据竞价单id单击成功");		
        } catch (Exception e) {
        	TestCase.result = false;
        	LogUtils.error("根据竞价单id单击失败，具体异常信息为"+e.getMessage());
        	e.printStackTrace();
        }       
    }
    
    /**
     * 根据托运单id单击，元素的唯一标志需要从数据库里取出托运单id
     * @param product
     * @param attribute
     * @param key
     * @param cellphone
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public void itemIdClick (String product,String cellphone,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
        try {
        	ClickOperation.buttonClick(element.getElement(DriverInitialUtils.webDriver,elementLocation1,GetDataFromMySql.userItemId(cellphone))); 
            LogUtils.info("根据托运单id单击成功");		
        } catch (Exception e) {
        	TestCase.result = false;
        	LogUtils.error("根据托运单id单击失败，具体异常信息为"+e.getMessage());
        	e.printStackTrace();
        }       
    }
    
    public void switchWindow (String product,String windowPage,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
    	try {
        	SwitchWindowOperation.switchWindow(DriverInitialUtils.webDriver, windowPage);
            LogUtils.info("切换窗口成功");		
        } catch (Exception e) {
        	TestCase.result = false;
        	LogUtils.error("切换窗口失败，具体异常信息为"+e.getMessage());
        	e.printStackTrace();
        } 
    }
    
    public void closeWindow (String product,String windowNum,String value2,String value3,
            String value4,String elementLocation1,String elementLocation2) {
        try {
            SwitchWindowOperation.closeWindow(DriverInitialUtils.webDriver,windowNum);
            LogUtils.info("窗口关闭成功");
        } catch (Exception e) {
            TestCase.result = false;
            LogUtils.error("窗口关闭失败，具体异常信息为"+e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * 切入iframe
     * @param product
     * @param iframe
     * @param value2
     * @param value3
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public void intoIframe (String product,String iframe,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
    	try {
    		IframOperation.intoIframe(DriverInitialUtils.webDriver, iframe);
            LogUtils.info("成功切入iframe"+iframe);		
        } catch (Exception e) {
        	TestCase.result = false;
        	LogUtils.error("切入"+iframe+"iframe失败，具体异常信息为"+e.getMessage());
        	e.printStackTrace();
        } 
    }
    
    /**
     * 切入含工单id的iframe,待切入的iframe名称中含有工单id，工单id需要从数据库中查出
     * @param product
     * @param iframe
     * @param value2
     * @param value3
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public void intoIframeContainsTransId (String product,String iframe,String cellphone,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
    	try {
    		IframOperation.intoIframeContainsParams(DriverInitialUtils.webDriver, iframe, GetDataFromMySql.userTransId(cellphone));
            LogUtils.info("成功切入iframe");		
        } catch (Exception e) {
        	TestCase.result = false;
        	LogUtils.error("切入iframe失败，具体异常信息为"+e.getMessage());
        	e.printStackTrace();
        } 
    }
    
    /**
     * 切出iframe
     * @param product
     * @param iframe
     * @param value2
     * @param value3
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public void outIframe (String product,String value1,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
    	try {
    		IframOperation.outIframe(DriverInitialUtils.webDriver);
            LogUtils.info("成功切出iframe");		
        } catch (Exception e) {
        	TestCase.result = false;
        	LogUtils.error("切出iframe失败，具体异常信息为"+e.getMessage());
        	e.printStackTrace();
        } 
    }
    
    /**
     * 断言
     * @param product
     * @param assertString
     * @param value2
     * @param value3
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public void assertString(String product,String assertString,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
    	try {
    		AssertOperation.assertString(DriverInitialUtils.webDriver, assertString);
            LogUtils.info("成功断言关键字“"+assertString+"”");
    	} catch (Exception e) {
    		TestCase.result = false;
        	LogUtils.error("出现断言失败，具体断言败信息："+e.getMessage());
        	e.printStackTrace();
    	}
	}
    
    /**
     * 系统弹出的确认、取消框处理
     * @param product
     * @param assertString
     * @param value2
     * @param value3
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public void confirmHander(String product,String message,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
    	try {
    		AlertOperation.confirmHander(DriverInitialUtils.webDriver, message);
            LogUtils.info("网页弹出确认、取消按钮框，已点击“"+message+"”按钮");
    	} catch (Exception e) {
    		TestCase.result = false;
        	LogUtils.error("网页弹出框点击失败，具体异常信息为："+e.getMessage());
        	e.printStackTrace();
    	}
	}
    
    /**
     * 使用index选择数据
     * @param product
     * @param index
     * @param text
     * @param value3
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public void getSelectDataByIndex(String product,String index,String text,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
    	try {
    		SelectOperation.getSelectDataByIndex(element.getElement(DriverInitialUtils.webDriver,elementLocation1,index),Integer.parseInt(index), text);
            LogUtils.info("选择的下拉框数据为"+text);
    	} catch (Exception e) {
    		TestCase.result = false;
        	LogUtils.error("下拉框数据选择失败，具体异常信息为："+e.getMessage());
        	e.printStackTrace();
    	}
    }
    /**
     * 使用select的value属性选择数据
     * @param product
     * @param index
     * @param text
     * @param value3
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public void getSelectDataByValue(String product,String value,String text,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
    	try {
    		SelectOperation.getSelectDataByValue(element.getElement(DriverInitialUtils.webDriver,elementLocation1,value),value, text);
            LogUtils.info("选择的下拉框数据为"+text);
    	} catch (Exception e) {
    		TestCase.result = false;
        	LogUtils.error("下拉框数据选择失败，具体异常信息为："+e.getMessage());
        	e.printStackTrace();
    	}
    }
    /**
     * 使用select的选项的文字选择数据
     * @param product
     * @param index
     * @param text
     * @param value3
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public void getSelectDataByText(String product,String text,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
    	try {
    		SelectOperation.getSelectDataByVisibleText(element.getElement(DriverInitialUtils.webDriver,elementLocation1,text), text);
            LogUtils.info("选择的下拉框数据为"+text);
    	} catch (Exception e) {
    		TestCase.result = false;
        	LogUtils.error("下拉框数据选择失败，具体异常信息为："+e.getMessage());
        	e.printStackTrace();
    	}
    }
}
