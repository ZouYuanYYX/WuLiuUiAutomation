package com.wuliu.data;

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
	public static final String LOGPATH() {
		return properties.getProperty("LOGPATH");
	}
	public static final String SHEETNAME() {
		return properties.getProperty("SHEETNAME");
	}
	//测试截图保存的路径
	public static final String PICTURE_PATH() {
		return properties.getProperty("PICTURE_PATH");
	}
	//app相关配置
    //要安装的app包名
	public static final String SHIPPER() {
		return properties.getProperty("SHIPPER");
	}
	//要安装的app包名
	public static final String CARRIER() {
		return properties.getProperty("CARRIER");
	}
	//APP存放的路径
	public static final String SHIPPER_PATH() {
		return properties.getProperty("SHIPPER_PATH");
	}
	//APP存放的路径
	public static final String CARRIER_PATH() {
		return properties.getProperty("CARRIER_PATH");
	}
	//使用cmd命令开启appium服务
	public static final String SHIPPER_CMD() {
		return properties.getProperty("SHIPPER_CMD");
	}
	//使用cmd命令开启appium服务
	public static final String CARRIER_CMD() {
		return properties.getProperty("CARRIER_CMD");
	}
	//要连接的设备地址
	public static final String SHIPPER_DEVICE() {
		return properties.getProperty("SHIPPER_DEVICE");
	}
	//要连接的设备地址
	public static final String CARRIER_DEVICE() {
		return properties.getProperty("CARRIER_DEVICE");
	}
	//要连接的URL地址
	public static final String SHIPPER_URL() {
		return properties.getProperty("SHIPPER_URL");
	}
	//要连接的URL地址
	public static final String CARRIER_URL() {
		return properties.getProperty("CARRIER_URL");
	}
	//要连接的设备名
	public static final String SHIPPER_DEVICE_NAME() {
		return properties.getProperty("SHIPPER_DEVICE_NAME");
	}
	//要连接的设备名
	public static final String CARRIER_DEVICE_NAME() {
		return properties.getProperty("CARRIER_DEVICE_NAME");
	}
	//要测试的安卓版本
	public static final String ANDROID_SHIPPER() {
		return properties.getProperty("ANDROID_SHIPPER");
	}
	//要测试的安卓版本
	public static final String ANDROID_CARRIER() {
		return properties.getProperty("ANDROID_CARRIER");
	}
	
}
