package com.wuliu.utils;

import java.io.IOException;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class SqlSessionFactoryUtil {
	
	public static SqlSessionFactory getSqlSessionFactory(String resource) throws IOException {
		//getResourceAsStream：classes目录下的相对路径
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory	= new SqlSessionFactoryBuilder().build(inputStream);
		System.out.println(sqlSessionFactory);
		return sqlSessionFactory;
	}
	
	public static SqlSessionFactory getUicSqlsessionFactory() throws IOException {
		return getSqlSessionFactory("config/uic-config.xml");
	}
	
	public static SqlSessionFactory getGoodsOwnerSqlsessionFactory() throws IOException {
		return getSqlSessionFactory("config/goodsowner-config.xml");
	}
	
	public static SqlSessionFactory getCarrierSqlsessionFactory() throws IOException {
		return getSqlSessionFactory("config/carrier-config.xml");
	}

}
