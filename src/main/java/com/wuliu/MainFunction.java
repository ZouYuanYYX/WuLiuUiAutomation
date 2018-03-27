package com.wuliu;


import org.apache.log4j.PropertyConfigurator;

import com.wuliu.data.GetPropertiesData;
import com.wuliu.keywords.AnalyzeExcelKeyWords;
import com.wuliu.utils.CreateFileUtils;
import com.wuliu.utils.ExcelUtils;
import com.wuliu.utils.LogUtils;

import junit.framework.Assert;
/**
 * 
 * @author joy
 *
 */
public class MainFunction {
  AnalyzeExcelKeyWords analyze = new AnalyzeExcelKeyWords();
  public static boolean result;
  
  public void analysisExcelMainSheet(String excelName,String mainSheetName) {
	  
	  //读取测试文档的excel数据
	  String path = "src/main/resources/excelTestCase/"+excelName;
	  ExcelUtils.readExcel(path);
	  
	  //读取日志文件
	  PropertyConfigurator.configure("src/main/resources/properties/log4j.properties");
	  
	  //每次跑测试用例前，先去创建一个文件，用于存储每次跑的测试截图
      String picturePath = CreateFileUtils.createDateFile(GetPropertiesData.picturePath());
      
      //excel中result字段所在列号（列从0开始）
      int resultCellCount = ExcelUtils.getCellCount(mainSheetName)-1;
      
      for (int i = 1;i <= ExcelUtils.getRowCount(mainSheetName);i++) {
          if ("yes".equals(ExcelUtils.getCell(mainSheetName, i, 3).toLowerCase().trim())) {
              String mainSheetSuiteCaseId = ExcelUtils.getCell(mainSheetName, i, 0); 
              String testCaseSheetName = ExcelUtils.getCell(mainSheetName, i, 2); 
              System.out.println("将要执行的测试用例id为："+mainSheetSuiteCaseId);
              System.out.println(testCaseSheetName+"测试用例执行");
              //在日志中打印测试用例开始执行
              LogUtils.startTestCase(mainSheetSuiteCaseId);
              //设定测试用例的当前结果为true
              result = true;
              for (int j = 1;j <= ExcelUtils.getRowCount(testCaseSheetName);j++) {
            	  analyze.analyzeExcel(path,mainSheetSuiteCaseId,testCaseSheetName,j,picturePath);                 
                  if (result == false) {                      
                      ExcelUtils.setCell(i, resultCellCount, "测试用例执行失败", mainSheetName, path);
                      //在日志中打印测试用例执行完毕
                      LogUtils.info(mainSheetSuiteCaseId+"测试用例执行失败");
                      LogUtils.endTestCase(mainSheetSuiteCaseId);
                      //调用测试方法过程中，若出现异常，则将测试设定为失败，停止测试用例执行
                      Assert.fail("执行出现异常，测试用例执行失败");
                      break;
                  } 
              }   
              if (result == true) {                      
                  ExcelUtils.setCell(i, resultCellCount, "测试用例执行成功", mainSheetName, path);           
                  //在日志中打印测试用例执行完毕
                  LogUtils.info(mainSheetSuiteCaseId+"测试用例执行成功");
                  LogUtils.endTestCase(mainSheetSuiteCaseId);
              }
          } else {
              String mainSheetSuiteCaseId = ExcelUtils.getCell(mainSheetName, i, 0);
              LogUtils.info(mainSheetSuiteCaseId+"测试用例不执行");
              System.out.println(mainSheetSuiteCaseId+"测试用例不执行");
          }
      }
  }
  
  public static void main(String[] args) {     
	  
	  //调用上面的方法
	  new MainFunction().analysisExcelMainSheet("testdata.xlsx","Suite");
      
  }
   
}
