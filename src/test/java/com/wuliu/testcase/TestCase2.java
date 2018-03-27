package com.wuliu.testcase;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wuliu.MainFunction;
import com.wuliu.data.GetPropertiesData;
import com.wuliu.utils.ExcelUtils;
/**
 * 
 * @author joy
 *
 */
public class TestCase2 {
	
	/**
	 * 其中threadPoolSize表明用于调用该方法的线程池容量，该例就是同时起1个线程并行执行该方法；
	 * invocationCount表示该方法总计需要被执行的次数。该例子中1个线程同时执行，当总计执行次数
	 * 达到2次时，停止
	 */
	@Test(enabled=false, dataProvider="bingfa",invocationCount = 1,threadPoolSize=1,timeOut = 50000)
	public void test(String excelName,String mainSheetName) {
		MainFunction testCase = new MainFunction();
		testCase.analysisExcelMainSheet(excelName,mainSheetName);
	}
	
	@DataProvider(name = "bingfa")
    public static Object[][]testdp(){
        return new Object[][]{
            {"testdata.xlsx","Suite"},
            {"testdata2.xlsx","Suite"}
        };
    }
}
