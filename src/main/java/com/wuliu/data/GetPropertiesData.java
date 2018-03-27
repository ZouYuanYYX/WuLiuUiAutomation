package com.wuliu.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

/**
 * 存放固定值(常量)，如excel文件路径等
 * @author joy
 * @date 2017年12月14日
 */

public class GetPropertiesData {
    /**
     * 读取excel_and_app_config配置文件
     */
	private static Properties properties = getProperties("src/main/resources/properties/excel_and_app_config.properties");
	
//	/**
//	 * excel的NAME
//	 * @return
//	 */
//	public static final String excelName() {
//		return properties.getProperty("excelName");
//	}
//	/**
//	 * excel的路径
//	 */
//	public static final String EXCELPATH = "src/main/resources/excelTestCase/"+excelName();
//	
	/**
	 * appium日志的path
	 * @return
	 */
	public static final String appiumShipperLogPath() {
		return properties.getProperty("appiumShipperLogPath");
	}
	
	/**
	 * appium日志的path
	 * @return
	 */
	public static final String appiumCarrierLogPath() {
		return properties.getProperty("appiumCarrierLogPath");
	}
//	/**
//	 * 存放测试用例的表sheet名
//	 * @return
//	 */
//	public static final String sheetName() {
//		return properties.getProperty("sheetName");
//	}
	/**
	 * 测试截图保存的路径
	 * @return
	 */
	public static final String picturePath() {
		return properties.getProperty("picturePath");
	}
    /**
     * app相关配置
     * 要安装的app包名
     * @return
     */
	public static final String shipperPackageName() {
		return properties.getProperty("shipperPackageName");
	}
	/**
	 * 要安装的app包名
	 * @return
	 */
	public static final String carrierPackageName() {
		return properties.getProperty("carrierPackageName");
	}
	/**
	 * 开启appium服务需要占用的端口号
	 * @return
	 */
	public static final int shipperPort() {
		return Integer.parseInt( properties.getProperty("shipperPort"));
	}
	
	/**
	 * 开启appium服务需要占用的端口号
	 * @return
	 */
	public static final int carrierPort() {
		return Integer.parseInt( properties.getProperty("carrierPort"));
	}
	
	/**
	 * 要连接的设备名
	 * @return
	 */
	public static final String shipperDeviceName() {
		return properties.getProperty("shipperDeviceName");
	}
	/**
	 * 要连接的设备名
	 * @return
	 */
	public static final String carrierDeviceName() {
		return properties.getProperty("carrierDeviceName");
	}
	

	/**
	 * 要连接的URL地址
	 * @return
	 */
	public static final String shipperUrl() {
		return MessageFormat.format(properties.getProperty("shipperUrl"),properties.getProperty("shipperPort"));
	}
	/**
	 * 要连接的URL地址
	 * @return
	 */
	public static final String carrierUrl() {
		return MessageFormat.format(properties.getProperty("carrierUrl"),properties.getProperty("carrierPort"));
	}
	
	/**
	 * 要测试的安卓版本
	 * @return
	 */
	public static final String shipperVersion() {
		return properties.getProperty("shipperVersion");
	}
	/**
	 * 要测试的安卓版本
	 * @return
	 */
	public static final String carrierVersion() {
		return properties.getProperty("carrierVersion");
	}
	
	public static Properties getProperties(String path){
		Properties prop = null;
		FileInputStream in = null;
		try {
		    prop = new Properties();
		    in = new FileInputStream(path);
		    prop.load(in);
			in.close();
			return prop;
		} catch(IOException e) {
			e.printStackTrace();
		} 
		return prop;
	}
}
