package com.wuliu.keywords;


import com.wuliu.data.GetDataFromMySql;
import com.wuliu.entity.ElementObject;
import com.wuliu.operation.element.ClickOperation;
import com.wuliu.operation.element.InputTextOperation;
import com.wuliu.operation.page.AssertOperation;
import com.wuliu.testcase.TestCase;
import com.wuliu.utils.AppSwipeUtils;
import com.wuliu.utils.DriverInitialUtils;
import com.wuliu.utils.LogUtils;
/**
 * app关键字处理类
 * @author joy
 * @data 2017年12月5日
 */

public class AppActionKeyWords {
	private ElementObject element = new ElementObject();
	//无参构造函数
	public AppActionKeyWords() {
		
	}

	public void openApp (String product,String value1,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
        try {
    		DriverInitialUtils.appDriverInitial(product);
    		LogUtils.info(product+"app启动成功");
        } catch (Exception e) {
        	TestCase.result = false;
        	LogUtils.info(product+"app启动异常，具体异常信息为"+e.getMessage());
        	e.printStackTrace();
        }
    }
    
    public void closeApp (String product,String value1,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {    	
    	try {
    		DriverInitialUtils.appClose(product);
    		LogUtils.info(product+"app关闭成功");
    	} catch (Exception e) {
    		TestCase.result = false;
        	LogUtils.info(product+"app关闭异常，具体异常信息为"+e.getMessage());
        	e.printStackTrace();
    	}
    }   
    
    public void sleep (String product,String time,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
        try {        
            Thread.sleep(Integer.parseInt(time));
            LogUtils.info("休眠"+Integer.parseInt(time)/1000+"秒成功");
        } catch (Exception e) {
        	TestCase.result = false;
        	LogUtils.info("线程休眠时出现异常，具体异常信息"+e.getMessage());
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
        	if ("货主".equals(product.trim())||"货运站".equals(product.trim())) {
        		InputTextOperation.inputText(element.getElement(DriverInitialUtils.appShipperDriver,elementLocation1,value1), value1);
            	LogUtils.info("app输入框成功输入"+value1);
        	}
            if ("车主".equals(product.trim())||"司机".equals(product.trim())) {
            	InputTextOperation.inputText(element.getElement(DriverInitialUtils.appCarrierDriver,elementLocation1,value1),value1);
            	LogUtils.info("app输入框成功输入"+value1);
            }
        } catch (Exception e) {
        		TestCase.result = false;
	        	LogUtils.info("往输入框里输入"+value1+"出现异常，具体异常信息为"+e.getMessage());
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
        	if ("货主".equals(product.trim())||"货运站".equals(product.trim())) {
            	//initialElement(elementLocation1,value1);
        		InputTextOperation.clearText(element.getElement(DriverInitialUtils.appShipperDriver,elementLocation1,value1));
            	LogUtils.info("app输入框成功清空");
        	}
            if ("车主".equals(product.trim())||"司机".equals(product.trim())) {
            	//initialElement(elementLocation1,value1);
            	InputTextOperation.clearText(element.getElement(DriverInitialUtils.appCarrierDriver,elementLocation1,value1));
            	LogUtils.info("app输入框成功清空");
            } 
        } catch (Exception e) {
        		TestCase.result = false;
	        	LogUtils.info("输入框清空出现异常，具体异常信息为"+e.getMessage());
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
        	if ("货主".equals(product.trim())||"货运站".equals(product.trim())) {
        		ClickOperation.buttonClick(element.getElement(DriverInitialUtils.appShipperDriver,elementLocation1,value1));
            	LogUtils.info("app端单击页面元素"+elementLocation1+"成功");
        	}
            if ("车主".equals(product.trim())||"司机".equals(product.trim())) {
            	ClickOperation.buttonClick(element.getElement(DriverInitialUtils.appCarrierDriver,elementLocation1,value1));
            	LogUtils.info("app端单击页面元素"+elementLocation1+"成功");
            } 
        } catch (Exception e) {
        		TestCase.result = false;
	        	LogUtils.info("单击页面元素"+elementLocation1+"失败，具体异常信息为"+e.getMessage());
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
            if ("货主".equals(product.trim())||"货运站".equals(product.trim())) {
                ClickOperation.buttonClick(element.getElement(DriverInitialUtils.appShipperDriver,elementLocation1,GetDataFromMySql.userTransId(cellphone))); 
                LogUtils.info("根据工单id单击成功");  
            }
            if ("车主".equals(product.trim())||"司机".equals(product.trim())) {
                ClickOperation.buttonClick(element.getElement(DriverInitialUtils.appCarrierDriver,elementLocation1,GetDataFromMySql.userTransId(cellphone))); 
                LogUtils.info("根据工单id单击成功");  
            }
        } catch (Exception e) {
            TestCase.result = false;
            LogUtils.error("根据工单id单击失败，具体异常信息为"+e.getMessage());
            e.printStackTrace();
        }       
    }
    
    /**
     * 滑动app页面
     * @param product
     * @param swapType
     * @param countStr
     * @param value3
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
	public void swapApp (String product,String swapType,String countStr,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
		try {
			int count = Integer.parseInt(countStr);
			if ("货主".equals(product.trim())||"货运站".equals(product.trim())) {
				for (int i=0;i<count;i++) {
					AppSwipeUtils.swapApp(DriverInitialUtils.appShipperDriver, swapType);
				}
				LogUtils.info(product+"页面"+swapType+count+"次成功");
			} 
			if ("车主".equals(product.trim())||"司机".equals(product.trim())) {
				for (int i=0;i<count;i++) {
					AppSwipeUtils.swapApp(DriverInitialUtils.appCarrierDriver, swapType);
				}
				LogUtils.info(product+"页面"+swapType+count+"次成功");
			}
			
		} catch (Exception e) {
			TestCase.result = false;
        	LogUtils.info(product+"滑动页面失败，具体异常信息为"+e.getMessage());
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
    		if ("货主".equals(product.trim())||"货运站".equals(product.trim())) {
        		AssertOperation.assertString(DriverInitialUtils.appShipperDriver, assertString);
                LogUtils.info("成功断言关键字“"+assertString+"”");
        	}
            if ("车主".equals(product.trim())||"司机".equals(product.trim())) {
        		AssertOperation.assertString(DriverInitialUtils.appCarrierDriver, assertString);
                LogUtils.info("成功断言关键字“"+assertString+"”");
            }
    	} catch (Exception e) {
    		TestCase.result = false;
        	LogUtils.info("出现断言失败，具体断言败信息："+e.getMessage());
        	e.printStackTrace();
    	}
	}

}
