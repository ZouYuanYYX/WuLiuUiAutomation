package com.wuliu.keywords;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.wuliu.data.FinalData;
import com.wuliu.testcase.TestCase;
import com.wuliu.utils.ExcelUtils;
import com.wuliu.utils.LogUtils;
import com.wuliu.utils.DriverInitialUtils;
import com.wuliu.utils.ScreenShotUtils;

import junit.framework.Assert;
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
    public void analyzeExcel (String mainSheetSuiteCaseId,String sheetName,int rowNum,String picturePath) {
        
    	String paramsName = null;
        Map<String,String> map = new HashMap<String,String>();
        //将每一行每一列的值指给对应的表头参数，后续通过表头参数直接get到值
        for (int i=0;i<ExcelUtils.getCellCount(sheetName);i++) {
        	paramsName = ExcelUtils.getCell(sheetName,0,i);
        	map.put(paramsName, ExcelUtils.getCell(sheetName, rowNum, i));
        }
        //跑测试步骤时，只跑与主表测试用例id一致的数据
        if (mainSheetSuiteCaseId.equals(map.get("suiteCaseId"))) {
            try {
                System.out.println("第"+rowNum+"步测试步骤执行");
                keyWordsAction(sheetName,map.get("keyWordsFunction"),map.get("testDevices"),map.get("product"),map.get("value1"),map.get("value2"),
                		map.get("value3"), map.get("value4"),map.get("elementLocation1"),map.get("elementLocation2"));
                if (TestCase.result == true) {
                    //保存截图
                    ScreenShotUtils.screenShot(picturePath,sheetName,map.get("testCaseId"),map.get("product"),map.get("function"));
                    //在日志中打印测试步骤执行完毕
                    LogUtils.info(map.get("testCaseId")+"测试步骤执行成功");
                    ExcelUtils.setCell(rowNum, 14, "测试步骤执行成功", sheetName, FinalData.PATH());
                }
            } catch (Exception e) {
                ExcelUtils.setCell(rowNum, 14, "测试步骤执行失败", sheetName, FinalData.PATH());
                //在日志中打印测试步骤执行完毕
                LogUtils.info(map.get("testCaseId")+"测试步骤执行失败");
                //测试用例执行失败就关闭浏览器或app
                DriverInitialUtils.webClose();
                DriverInitialUtils.appClose(map.get("product"));
                //调用测试方法过程中，若出现异常，则将测试设定为失败，停止测试用例执行
                Assert.fail("执行出现异常，测试用例执行失败");
                
            }
        	
        }
    }
    /**
     * 使用java反射机制分别调用app端及web端的关键字类方法
     * @param sheetName
     * @param rownum
     * @param keyWordsFunction
     * @param production
     * @param value1
     * @param value2
     * @param value3
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public void keyWordsAction (String sheetName,String keyWordsFunction,String testDevices,String production,
            String value1,String value2,String value3,String value4,String elementLocation1,
            String elementLocation2) {
        WebActionKeyWords webActionKeyWords = new WebActionKeyWords();
        AppActionKeyWords appActionKeyWords = new AppActionKeyWords();
        Class<? extends WebActionKeyWords> clazzWeb = webActionKeyWords.getClass();
        Class<? extends AppActionKeyWords> clazzApp = appActionKeyWords.getClass();
        Method[] methodsWeb = clazzWeb.getDeclaredMethods();
        Method[] methodsApp = clazzApp.getDeclaredMethods();
        if ("web".equals(testDevices)) {
        	keyWordsFunctionInvoke(methodsWeb,webActionKeyWords,keyWordsFunction,production,value1,value2,value3,value4,elementLocation1,
                    elementLocation2);
        }
        if ("app".equals(testDevices)) {
            keyWordsFunctionInvoke(methodsApp,appActionKeyWords,keyWordsFunction,production,value1,value2,value3,value4,elementLocation1,
                    elementLocation2);
        } 
    }
    /**
     * 使用java反射机制调用方法
     * @param methods
     * @param object
     * @param value1
     * @param value2
     * @param value3
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public void keyWordsFunctionInvoke (Method[] methods,Object object,String keyWordsFunction,String production,String value1,
    		String value2,String value3,String value4,String elementLocation1,String elementLocation2) {
        for (int i=0;i<methods.length;i++) {
            try {
                if (keyWordsFunction.equals(methods[i].getName())) {
                    methods[i].invoke(object, production,value1,value2,value3,value4,elementLocation1,elementLocation2); 
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
