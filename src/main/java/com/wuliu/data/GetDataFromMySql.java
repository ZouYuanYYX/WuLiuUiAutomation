package com.wuliu.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.wuliu.dao.CarrierDao;
import com.wuliu.dao.GoodsOwnerDao;
import com.wuliu.dao.UicDao;
import com.wuliu.utils.LogUtils;
import com.wuliu.utils.SqlSessionFactoryUtil;
/**
 * 
 * @author joy
 *
 */
public class GetDataFromMySql {
	
	private static String getUserIdFromMySql(String cellphone) {
		SqlSession sqlSession = null;
		String userId = null;
		try {
			SqlSessionFactory uicSqlSessionFactory = SqlSessionFactoryUtil.getUicSqlsessionFactory();
			sqlSession = uicSqlSessionFactory.openSession();
			UicDao uicUserIdDao = sqlSession.getMapper(UicDao.class);
			userId = uicUserIdDao.getUserId(cellphone);
			System.out.println("获取到的用户id为："+userId);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return userId;
	}
	
	private static Map<String,String> getUserTransIdFromSql(String userId) {
		SqlSession sqlSession = null;
		List<Map<String,String>> transIds = new ArrayList<Map<String,String>>();
		Map<String,String> transId = new HashMap<String, String>();
		try {
			SqlSessionFactory goodsOwnerSqlSessionFactory = SqlSessionFactoryUtil.getGoodsOwnerSqlsessionFactory();
			sqlSession = goodsOwnerSqlSessionFactory.openSession();
			GoodsOwnerDao goodsOwnerDao = sqlSession.getMapper(GoodsOwnerDao.class);
			transIds = goodsOwnerDao.getUserAllTransId(userId);
			transId = transIds.get(transIds.size()-1);
			System.out.println("获取到用户的最新工单id为："+transId.get("id"));
			System.out.println("获取到用户的最新竞价单id为："+transId.get("auction_id"));
			System.out.println("获取到用户的最新电商id为："+transId.get("out_biz_id"));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		//返回transId的map
		return transId;
		
	}
	
	private static String getItemIdFromMySql(String auctionId) {
		SqlSession sqlSession = null;
		String itemId = null;
		try {
			SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getCarrierSqlsessionFactory();
			sqlSession = sqlSessionFactory.openSession();
			CarrierDao carrierItemIdDao = sqlSession.getMapper(CarrierDao.class);
			itemId = carrierItemIdDao.getUserItemId(auctionId);
			System.out.println("获取到的用户最新托运单id为："+itemId);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return itemId;
	}
	/**
	 * 返回货主下单的工单id
	 * @param cellphone
	 * @return
	 */
	public static String userTransId(String cellphone) {
		String userId = getUserIdFromMySql(cellphone);
    	String transId = getUserTransIdFromSql(userId).get("id");
    	LogUtils.info("获取到的工单id为："+transId);
    	return transId;
	}
	
	/**
	 * 返回货主下单的竞价单id
	 * @param cellphone
	 * @return
	 */
	public static String userAuctionId(String cellphone) {
		String userId = getUserIdFromMySql(cellphone);
	    String auctionId = getUserTransIdFromSql(userId).get("auction_id");
	    LogUtils.info("获取到的竞价单id为："+auctionId);
	    return auctionId;
	}
	
	/**
	 * 返回水泥电商货主下单的水泥电商id
	 * @param cellphone
	 * @return
	 */
	public static String userShuiNiId(String cellphone) {
		String userId = getUserIdFromMySql(cellphone);
	    String shuiNiId = getUserTransIdFromSql(userId).get("out_biz_id");
	    LogUtils.info("获取到的水泥电商id为："+shuiNiId);
	    return shuiNiId;
	}
	
	/**
	 * 返回货主下单的托运单id
	 * @param cellphone
	 * @return
	 */
	public static String userItemId(String cellphone) {
	    String auctionId = userAuctionId(cellphone);
		String itemId = getItemIdFromMySql(auctionId);
		LogUtils.info("获取到的托运单id为："+itemId);
		return itemId;
	}

}
