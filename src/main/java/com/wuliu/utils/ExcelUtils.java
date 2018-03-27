package com.wuliu.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * 对excel操作的相关方法
 * @author 邹元
 * @date 2017年12月14
 */

public class ExcelUtils {
    
    private static XSSFWorkbook workbook;
    private static XSSFSheet sheet;
    private static XSSFRow row;
    private static XSSFCell cell;
    
    /**
     * 从excel表存储路径中读取excel
     * @param path
     */
    public static void readExcel(String path) {
        try {
            FileInputStream in = new FileInputStream(path);
            workbook = new XSSFWorkbook (in); 
            in.close();
        } catch (Exception e) {
            System.out.println("");
            e.printStackTrace();
        }         
    }
    /**
     * 获取excel表的行数    
     * @param sheetName
     * @return
     */
    public static int getRowCount(String sheetName) {
        sheet = workbook.getSheet(sheetName);
        //getLastRowNum()返回值是n-1，去掉表头，正文数据正好是n-1
        return sheet.getLastRowNum();        
    }
    /**
     * 获取excel表的列数
     * @param sheetName
     * @return
     */
    public static int getCellCount(String sheetName) {
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(0);
        //getLastCellNum()返回值是n（只有1列就返回1），但excel取数是从第0列开始
        return row.getLastCellNum();        
    }
    
    public static void setCell(int rownum,int cellnum,String result,String sheetName,String path) {
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(cellnum);
        if (cell == null) {
            cell = row.createCell(cellnum);
            cell.setCellValue(result);
        }else {
            cell.setCellValue(result);
        }
        //值设置好后再存入excel中
        try {
            FileOutputStream out = new FileOutputStream(path);
            workbook.write(out);
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("文档不存在");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("文档正在被使用");
            e.printStackTrace();
        }       
    }
    
    @SuppressWarnings("deprecation")
	public static String getCell(String sheetName,int rownum,int cellnum) {
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(cellnum);
        SimpleDateFormat sFormat = new SimpleDateFormat("MM/dd/yyyy");
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        } 
        //字符串
        else if(cell.getCellType() == Cell.CELL_TYPE_STRING) {
            cellValue = cell.getStringCellValue();
        } 
        //数值
        else if(cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
            if(HSSFDateUtil.isCellDateFormatted(cell)) {
                double d = cell.getNumericCellValue();
                Date date = HSSFDateUtil.getJavaDate(d);
                cellValue = sFormat.format(date);
            } else {                
                cellValue = decimalFormat.format((cell.getNumericCellValue()));
            }
        } 
        //布尔值
        else if(cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            cellValue = String.valueOf(cell.getBooleanCellValue());
        } 
        //除以上几种类型，其他excel里的数据都用String来接收
        else {
        	cellValue = cell.getStringCellValue();
        }
        return cellValue;
    }
    
    public static List<Map<String,String>> getMultipleCell(String sheetName,int rownum) {
    	List<Map<String,String>> list = new ArrayList<Map<String, String>>();
        Map<String,String> map = new HashMap<String,String>();
        String paramsName = null;
      //读取excel里每一行的数据，put进map
        //getCell(sheetName,0,i)获取的其实就是表头名
    	for (int i=0;i<getCellCount(sheetName);i++) {
    		paramsName = getCell(sheetName,0,i);
    		map.put(paramsName,getCell(sheetName,rownum,i));
    	}
    	list.add(map);
        return list;
    }
//    /**
//     * 将Excel表头与列的序列号一一对应起来
//     * @param sheetName
//     * @return
//     */
//    public static Map<String,Integer> getCellIdMap(String sheetName) {
//    	Map<String,Integer> map = new HashedMap();
//    	//读取excel里第一行的数据，put进map
//    	for (int i=0;i<getCellCount(sheetName);i++) {
//    		System.out.println("读取的Excel表头数据"+getCell(sheetName,0,i));
//    		map.put(getCell(sheetName,0,i), i);
//    	}
//		return map;
//    }

}
