package com.wuliu.keywords;


import java.util.Map;


import com.wuliu.MainFunction;
import com.wuliu.data.GetDataFromMySql;
import com.wuliu.data.enumdata.ExcelHeader;
import com.wuliu.data.enumdata.Product;
import com.wuliu.entity.ElementObject;
import com.wuliu.entity.EmptyException;
import com.wuliu.operation.OperationObject;
import com.wuliu.utils.AppSwipeUtils;
import com.wuliu.utils.DriverInitialUtils;
import com.wuliu.utils.LogUtils;
import com.wuliu.utils.StringUtils;
/**
 * app关键字处理类
 * @author joy
 * @data 2017年12月5日
 */

public class AppActionKeyWords extends OperationObject{
	private ElementObject element = new ElementObject();
	
	/**
	 * excel表头名称的枚举值，value1至elementLocation2均为参数名称
	 */
	private String productName = ExcelHeader.PRODUCT.getName();
	private String value1 = ExcelHeader.VALUE1.getName();
	private String value2 = ExcelHeader.VALUE2.getName();
	private String elementLocation1 = ExcelHeader.ELEMENTLOCATION1.getName();
	
	/**
	 * 无参构造函数
	 */
	public AppActionKeyWords() {
		
	}

	public void openApp (Map<String,String> map) {
		String product = map.get(productName);
        try {
    		DriverInitialUtils.appDriverInitial(product);
    		LogUtils.info(product+"app启动成功");
        } catch (Exception e) {
        	MainFunction.result = false;
        	LogUtils.info(product+"app启动异常，具体异常信息为："+e.getMessage());
        	e.printStackTrace();
        }
    }
    
    public void closeApp (Map<String,String> map) {    	
    	try {
    		DriverInitialUtils.appClose();
    		LogUtils.info("app关闭成功");
    	} catch (Exception e) {
    		MainFunction.result = false;
        	LogUtils.info("app关闭异常，具体异常信息为"+e.getMessage());
        	e.printStackTrace();
    	}
    }   
    
    public void sleep (Map<String,String> map) {
        try {
        	String time = map.get(value1);
        	if(StringUtils.isEmpty(time)) {
        		throw new EmptyException("value1为空");
        	} else {
        		Thread.sleep(Integer.parseInt(time));
                LogUtils.info("休眠"+Integer.parseInt(time)/1000+"秒成功");
        	}
        } catch (Exception e) {
        	MainFunction.result = false;
        	LogUtils.info("线程休眠时出现异常，具体异常信息："+e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * 输入
     * @param map
     */
    public void inputText (Map<String,String> map) {
    	//产品
    	String product = map.get(productName);
    	//待输入的文案
    	String sendKeys = map.get(value1);
    	
        try {
        	if(StringUtils.isEmpty(sendKeys)) {
        		throw new EmptyException("value1为空");
        	} else {
        		if (isShipper(product)) {
            		inputText(element.getElement(DriverInitialUtils.appShipperDriver,map.get(elementLocation1),sendKeys), sendKeys);
                	LogUtils.info("app输入框成功输入："+sendKeys);
            	}
                if (isCarrier(product)) {
                	inputText(element.getElement(DriverInitialUtils.appCarrierDriver,map.get(elementLocation1),sendKeys),sendKeys);
                	LogUtils.info("app输入框成功输入："+sendKeys);
                }
        	}
        } catch (Exception e) {
        		MainFunction.result = false;
	        	LogUtils.info("往输入框里输入"+sendKeys+"出现异常，具体异常信息为："+e.getMessage());
	        	e.printStackTrace();
        }   	
    }
    /**
     * adb命令方式输入值，只支持英文和数字，输入速度比较快
     * @param map
     */
    public void adbInputText (Map<String,String> map) {
    	//产品
    	String product = map.get(productName);
    	//待输入的文案
    	String sendKeys = map.get(value1);
    	
    	try {
        	if(StringUtils.isEmpty(sendKeys)) {
        		throw new EmptyException("value1为空");
        	} else {
        		if (isShipper(product)) {
        			adbInputText(element.getElement(DriverInitialUtils.appShipperDriver,map.get(elementLocation1),sendKeys), product,sendKeys);
                	LogUtils.info("app输入框成功输入："+sendKeys);
            	}
                if (isCarrier(product)) {
        			adbInputText(element.getElement(DriverInitialUtils.appCarrierDriver,map.get(elementLocation1),sendKeys), product,sendKeys);
                	LogUtils.info("app输入框成功输入："+sendKeys);
                }
        	}
        } catch (Exception e) {
        		MainFunction.result = false;
	        	LogUtils.info("往输入框里输入"+sendKeys+"出现异常，具体异常信息为："+e.getMessage());
	        	e.printStackTrace();
        }   
    }
    
    /**
     * 清空输入框
     * @param map
     */
    public void clearText (Map<String,String> map) {
    	//产品
    	String product = map.get(productName);
    	try {
        	if (isShipper(product)) {
        		clearText(element.getElement(DriverInitialUtils.appShipperDriver,map.get(elementLocation1),map.get(value1)));
            	LogUtils.info("app输入框成功清空");
        	}
            if (isCarrier(product)) {
            	clearText(element.getElement(DriverInitialUtils.appCarrierDriver,map.get(elementLocation1),map.get(value1)));
            	LogUtils.info("app输入框成功清空");
            } 
        } catch (Exception e) {
        		MainFunction.result = false;
	        	LogUtils.info("输入框清空出现异常，具体异常信息为："+e.getMessage());
	        	e.printStackTrace();
        }  
    }

    /**
     * 单击
     * @param map
     */
    public void buttonClick (Map<String,String> map) {
    	//产品
    	String product = map.get(productName);
    	//元素定位表达式
    	String elementLocation = map.get(elementLocation1);
    	
    	try {
        	if (isShipper(product)) {
        		buttonClick(element.getElement(DriverInitialUtils.appShipperDriver,elementLocation, map.get(value1) ));
            	LogUtils.info("app端单击页面元素"+elementLocation+"成功");
        	}
            if (isCarrier(product)) {
            	buttonClick(element.getElement(DriverInitialUtils.appCarrierDriver,elementLocation, map.get(value1) ));
            	LogUtils.info("app端单击页面元素"+elementLocation+"成功");
            } 
        } catch (Exception e) {
        		MainFunction.result = false;
	        	LogUtils.info("单击页面元素"+elementLocation+"失败，具体异常信息为："+e.getMessage());
	        	e.printStackTrace();
        } 
    }
    
    /**
     * 根据工单id单击，元素的唯一标志需要从数据库里取出工单id
     * @param map
     */
    public void transIdClick (Map<String,String> map) {
    	
    	try {
    		//产品
        	String product = map.get(productName);
        	String cellphone = map.get(value1);
        	
        	if(StringUtils.isEmpty(cellphone)) {
        		throw new EmptyException("value1为空");
        	} else {
        		if (isShipper(product)) {
                    buttonClick(element.getElement(DriverInitialUtils.appShipperDriver,map.get(elementLocation1),GetDataFromMySql.userTransId(cellphone))); 
                    LogUtils.info("根据工单id单击成功");  
                }
                if (isCarrier(product)) {
                    buttonClick(element.getElement(DriverInitialUtils.appCarrierDriver,map.get(elementLocation1),GetDataFromMySql.userTransId(cellphone))); 
                    LogUtils.info("根据工单id单击成功");  
                }
        	}
        } catch (Exception e) {
            MainFunction.result = false;
            LogUtils.error("根据工单id单击失败，具体异常信息为："+e.getMessage());
            e.printStackTrace();
        }       
    }
    
    /**
     * 滑动app页面
     * @param map
     */
	public void swapApp (Map<String,String> map) {
		//产品
    	String product = map.get(productName);
    	String swapType = map.get(value1);
    	String countStr = map.get(value2);
    	
		try {
			if(StringUtils.isEmpty(swapType)||StringUtils.isEmpty(countStr)) {
				throw new EmptyException("value1或value2为空");
        	} else {
        		int count = Integer.parseInt(countStr);
    			if (isShipper(product)) {
    				for (int i=0;i<count;i++) {
    					AppSwipeUtils.swapApp(DriverInitialUtils.appShipperDriver, swapType);
    				}
    				LogUtils.info(product+"页面"+swapType+count+"次成功");
    			} 
    			if (isCarrier(product)) {
    				for (int i=0;i<count;i++) {
    					AppSwipeUtils.swapApp(DriverInitialUtils.appCarrierDriver, swapType);
    				}
    				LogUtils.info(product+"页面"+swapType+count+"次成功");
    			}
        	}
		} catch (Exception e) {
			MainFunction.result = false;
        	LogUtils.info(product+"滑动页面失败，具体异常信息为："+e.getMessage());
        	e.printStackTrace();
		}
	}
	
	 /**
     * 断言
     * @param map
     */
    public void assertString(Map<String,String> map) {
    	try {
    		
    		//产品
        	String product = map.get(productName);
        	//待断言的文案信息
        	String assertString = map.get(value1);
        	
    		if(StringUtils.isEmpty(assertString)) {
    			throw new EmptyException("value1为空");
        	} else {
        		if (isShipper(product)) {
            		assertString(DriverInitialUtils.appShipperDriver, assertString);
                    LogUtils.info("成功断言关键字“"+assertString+"”");
            	}
                if (isCarrier(product)) {
            		assertString(DriverInitialUtils.appCarrierDriver, assertString);
                    LogUtils.info("成功断言关键字“"+assertString+"”");
                }
        	}
    	} catch (Exception e) {
    		MainFunction.result = false;
        	LogUtils.info("出现断言失败，具体断言败信息："+e.getMessage());
        	e.printStackTrace();
    	}
	}
    
    public boolean isShipper(String product) {
    	if (Product.SHIPPER.getName().equals(product.trim())||Product.FREIGHTSTATION.getName().equals(product.trim())) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public boolean isCarrier(String product) {
    	if (Product.CARRIER.getName().equals(product.trim())||Product.DRIVER.getName().equals(product.trim())) {
    		return true;
    	} else {
    		return false;
    	}
    }

}
