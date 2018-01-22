package com.wuliu.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 读取配置文件
 * @author joy
 * @data 2017年12月14日
 */

public class GetPropertiesData {
	@SuppressWarnings("finally")
    public static Properties getProperties(String path){
		Properties prop = null;
		FileInputStream in = null;
		try {
		    prop = new Properties();
		    in = new FileInputStream(path);
		    prop.load(in);
			in.close();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
		    return prop;
		}
	}

}
