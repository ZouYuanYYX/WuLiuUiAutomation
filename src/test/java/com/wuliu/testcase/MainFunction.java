package com.wuliu.testcase;


import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.wuliu.data.FinalData;
import com.wuliu.keywords.AnalyzeExcelKeyWords;
import com.wuliu.utils.CreateFileUtils;
import com.wuliu.utils.ExcelUtils;
import com.wuliu.utils.LogUtils;

public class MainFunction {
  AnalyzeExcelKeyWords analyze = new AnalyzeExcelKeyWords();
  public static boolean result;
  
  public void analysisExcelMainSheet() {
	  
	  
	  //读取excel数据
	  ExcelUtils.readExcel(FinalData.PATH());
	  //读取日志文件
	  PropertyConfigurator.configure(FinalData.LOGPATH());
	  
	  
	  //每次跑测试用例前，先去创建一个文件，用于存储每次跑的测试截图
      String picturePath = CreateFileUtils.createDateFile(FinalData.PICTURE_PATH());
      
      
      for (int i = 1;i <= ExcelUtils.getRowCount(FinalData.SHEETNAME());i++) {
          if ("yes".equals(ExcelUtils.getCell(FinalData.SHEETNAME(), i, 3).toLowerCase().trim())) {
              String mainSheetSuiteCaseId = ExcelUtils.getCell(FinalData.SHEETNAME(), i, 0); 
              String testCaseSheetName = ExcelUtils.getCell(FinalData.SHEETNAME(), i, 2); 
              System.out.println("将要执行的测试用例id为："+mainSheetSuiteCaseId);
              System.out.println(testCaseSheetName+"测试用例执行");
              //在日志中打印测试用例开始执行
              LogUtils.startTestCase(mainSheetSuiteCaseId);
              //设定测试用例的当前结果为true
              result = true;
              for (int j = 1;j <= ExcelUtils.getRowCount(testCaseSheetName);j++) {
            	  analyze.analyzeExcel(mainSheetSuiteCaseId,testCaseSheetName,j,picturePath);                 
                  if (result == false) {                      
                      ExcelUtils.setCell(i, 4, "测试用例执行失败", FinalData.SHEETNAME(), FinalData.PATH());
                      //在日志中打印测试用例执行完毕
                      LogUtils.info(mainSheetSuiteCaseId+"测试用例执行失败");
                      LogUtils.endTestCase(mainSheetSuiteCaseId);
                      break;
                  } 
              }   
              if (result == true) {                      
                  ExcelUtils.setCell(i, 4, "测试用例执行成功", FinalData.SHEETNAME(), FinalData.PATH());           
                  //在日志中打印测试用例执行完毕
                  LogUtils.info(mainSheetSuiteCaseId+"测试用例执行成功");
                  LogUtils.endTestCase(mainSheetSuiteCaseId);
              }
          } else {
              String mainSheetSuiteCaseId = ExcelUtils.getCell(FinalData.SHEETNAME(), i, 0);
              LogUtils.info(mainSheetSuiteCaseId+"测试用例不执行");
              System.out.println(mainSheetSuiteCaseId+"测试用例不执行");
          }
      }
  }
  
  public void main(String[] args) {     
	  
	  //调用上面的方法
	  new MainFunction().analysisExcelMainSheet();
      
  }
   
}
