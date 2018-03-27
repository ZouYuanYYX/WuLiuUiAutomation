package com.wuliu.keywords;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


import com.wuliu.MainFunction;
import com.wuliu.data.enumdata.ExcelHeader;
import com.wuliu.data.enumdata.TestDevices;
import com.wuliu.utils.ExcelUtils;
import com.wuliu.utils.LogUtils;
import com.wuliu.utils.DriverInitialUtils;
import com.wuliu.utils.ScreenShotUtils;

/**
 * 解析excel测试步骤中的关键字
 * @author joy
 * @date 2017年12月15日
 */
public class AnalyzeExcelKeyWords {
	
    /**
     * 读取excel测试步骤中每一行的数据并存储到list集合中
     * @param mainSheetSuiteCaseId
     * @param sheetName
     * @param rownum
     */
    public void analyzeExcel (String path,String mainSheetSuiteCaseId,String sheetName,int rowNum,String picturePath) {
        
    	/**
    	 * excel表头名称的枚举值，value1至elementLocation2均为参数名称
    	 */
    	String suiteCaseId = ExcelHeader.SUITECASEID.getName();
    	String keyWordsFunction = ExcelHeader.KEYWORDSFUNCTION.getName();
    	String testDevices = ExcelHeader.TESTDEVICES.getName();
    	String testCaseId = ExcelHeader.TESTCASEID.getName();
    	String product = ExcelHeader.PRODUCT.getName();
    	String keyWords = ExcelHeader.KEYWORDS.getName();
    	
    	//excel中result字段所在列号（列从0开始）
        int resultCellCount = ExcelUtils.getCellCount(sheetName)-1;
    	
    	String paramsName = null;
        Map<String,String> map = new HashMap<String,String>();
        
        //将每一行每一列的值指给对应的表头参数，后续通过表头参数直接get到值
        for (int i=0;i<ExcelUtils.getCellCount(sheetName);i++) {
        	paramsName = ExcelUtils.getCell(sheetName,0,i);
        	map.put(paramsName, ExcelUtils.getCell(sheetName, rowNum, i));
        }
        
        //跑测试步骤时，只跑与主表测试用例id一致的数据
        if (mainSheetSuiteCaseId.equals(map.get(suiteCaseId))) {
            try {
                System.out.println("第"+rowNum+"步测试步骤执行");
                keyWordsAction(sheetName,map.get(keyWordsFunction),map.get(testDevices),map);
                System.out.println("要跑的关键字方法："+map.get(keyWordsFunction));
                if (MainFunction.result == true) {
                	
                    //保存截图
                    ScreenShotUtils.screenShot(picturePath,sheetName,map.get(testCaseId),map.get(product),map.get(keyWords));
                    
                    //在日志中打印测试步骤执行完毕
                    LogUtils.info(map.get(testCaseId)+"测试步骤执行成功");
                    ExcelUtils.setCell(rowNum, resultCellCount, "测试步骤执行成功", sheetName, path);
                } else {
                	ExcelUtils.setCell(rowNum, resultCellCount, "测试步骤执行失败", sheetName, path);
                    
                	//在日志中打印测试步骤执行完毕
                    LogUtils.info(map.get(testCaseId)+"测试步骤执行失败");
                    
                    //测试用例执行失败就关闭浏览器或app
                    DriverInitialUtils.appClose();
                    DriverInitialUtils.webClose();
                }
            } catch (Exception e) {
                ExcelUtils.setCell(rowNum, resultCellCount, "测试步骤执行失败", sheetName, path);
                
                //在日志中打印测试步骤执行完毕
                LogUtils.info(map.get(testCaseId)+"测试步骤执行失败");
                
                //测试用例执行失败就关闭浏览器或app
                DriverInitialUtils.webClose();
                DriverInitialUtils.appClose();
            }
        }
    }
    /**
     * 使用java反射机制分别调用app端及web端的关键字类方法
     * @param sheetName
     * @param keyWordsFunction
     * @param testDevices
     * @param map
     */
     
    public void keyWordsAction (String sheetName,String keyWordsFunction,String testDevices,Map<String,String> map) {
        WebActionKeyWords webActionKeyWords = new WebActionKeyWords();
        AppActionKeyWords appActionKeyWords = new AppActionKeyWords();
        Class<? extends WebActionKeyWords> clazzWeb = webActionKeyWords.getClass();
        Class<? extends AppActionKeyWords> clazzApp = appActionKeyWords.getClass();
        Method[] methodsWeb = clazzWeb.getDeclaredMethods();
        Method[] methodsApp = clazzApp.getDeclaredMethods();
        if (TestDevices.WEB.getName().equals(testDevices)) {
        	keyWordsFunctionInvoke(methodsWeb,webActionKeyWords,keyWordsFunction,map);
        }
        if (TestDevices.APP.getName().equals(testDevices)) {
            keyWordsFunctionInvoke(methodsApp,appActionKeyWords,keyWordsFunction,map);
        } 
    }
    /**
     * 使用java反射机制调用方法
     * @param methods
     * @param object
     * @param keyWordsFunction
     * @param map
     */
    public void keyWordsFunctionInvoke (Method[] methods,Object object,String keyWordsFunction,Map<String,String> map) {
        for (int i=0;i<methods.length;i++) {
            try {
                if (keyWordsFunction.equals(methods[i].getName())) {
                    methods[i].invoke(object, map); 
                    break;
                } else {
                    continue;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }       
    }
}
