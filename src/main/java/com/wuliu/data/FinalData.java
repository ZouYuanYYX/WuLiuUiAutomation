package com.wuliu.data;

import java.text.MessageFormat;
import java.util.Properties;

/**
 * 存放固定值(常量)，如excel文件路径等
 * @author joy
 * @date 2017年12月14日
 */

public class FinalData {
    //读取excel_and_app_config配置文件
	private static Properties properties = GetPropertiesData.getProperties("recourses/properties/excel_and_app_config.properties");
	//excel的path
	public static final String PATH() {
		return properties.getProperty("PATH");
	}
	//日志的path
	public static final String LOGPATH() {
		return properties.getProperty("LOGPATH");
	}
	
	//appium日志的path
	public static final String APPIUM_SHIPPER_LOG_PATH() {
		return properties.getProperty("APPIUM_SHIPPER_LOG_PATH");
	}
	
	//appium日志的path
	public static final String APPIUM_CARRIER_LOG_PATH() {
		return properties.getProperty("APPIUM_CARRIER_LOG_PATH");
	}
	//存放测试用例的表sheet名
	public static final String SHEETNAME() {
		return properties.getProperty("SHEETNAME");
	}
	//测试截图保存的路径
	public static final String PICTURE_PATH() {
		return properties.getProperty("PICTURE_PATH");
	}
	//app相关配置
    //要安装的app包名
	public static final String SHIPPER_PACKAGNAME() {
		return properties.getProperty("SHIPPER_PACKAGNAME");
	}
	//要安装的app包名
	public static final String CARRIER_PACKAGNAME() {
		return properties.getProperty("CARRIER_PACKAGNAME");
	}
	//开启appium服务需要占用的端口号
	public static final int SHIPPER_PORT() {
		return Integer.parseInt( properties.getProperty("SHIPPER_PORT"));
	}
	
	//开启appium服务需要占用的端口号
	public static final int CARRIER_PORT() {
		return Integer.parseInt( properties.getProperty("CARRIER_PORT"));
	}
	
	//要连接的设备名
	public static final String SHIPPER_DEVICE_NAME() {
		return properties.getProperty("SHIPPER_DEVICE_NAME");
	}
	//要连接的设备名
	public static final String CARRIER_DEVICE_NAME() {
		return properties.getProperty("CARRIER_DEVICE_NAME");
	}
	
	//使用cmd命令开启appium服务
	public static final String SHIPPER_CMD() {
		String SHIPPER_CMD =  MessageFormat.format(properties.getProperty("SHIPPER_CMD"),SHIPPER_PORT(),SHIPPER_DEVICE_NAME());
		return SHIPPER_CMD;
	}
	//使用cmd命令开启appium服务
	public static final String CARRIER_CMD() {
		String CARRIER_CMD =  MessageFormat.format(properties.getProperty("CARRIER_CMD"),CARRIER_PORT(),CARRIER_DEVICE_NAME());
		return CARRIER_CMD;
	}

	//要连接的URL地址
	public static final String SHIPPER_URL() {
		return MessageFormat.format(properties.getProperty("SHIPPER_URL"),properties.getProperty("SHIPPER_PORT"));
	}
	//要连接的URL地址
	public static final String CARRIER_URL() {
		return MessageFormat.format(properties.getProperty("CARRIER_URL"),properties.getProperty("CARRIER_PORT"));
	}
	
	//要测试的安卓版本
	public static final String SHIPPER_VERSION() {
		return properties.getProperty("SHIPPER_VERSION");
	}
	//要测试的安卓版本
	public static final String CARRIER_VERSION() {
		return properties.getProperty("CARRIER_VERSION");
	}
	
}
