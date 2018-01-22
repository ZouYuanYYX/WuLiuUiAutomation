package com.wuliu.utils;

import org.apache.log4j.Logger;

/**
 * 日志文件配置类，不直接在其他类中调用日志打印的原因是：每个类都需要
 * 声明一个logger
 * @author joy
 * @date 2017年12月18日
 */

public class LogUtils {
    
	private static Logger logger = Logger.getLogger(LogUtils.class.getName());	
	public static void startTestCase(String testCaseId) {
        logger.info("********************"+testCaseId+"	测试用例开始运行	"+"*******************");
    }
    
    public static void endTestCase(String testCaseId) {
        logger.info("********************"+testCaseId+"	测试用例运行结束	"+"*******************");
    }
    //定义打印INFO级别日志的方法
    public static void info(String message) {
        logger.info(message);
    }
    //定义打印WARN级别日志的方法
    public static void warn(String message) {
        logger.warn(message);
    }
    //定义打印ERROR级别日志的方法
    public static void error(String message) {
        logger.error(message);
    }
    //定义打印DEBUG级别日志的方法
    public static void debug(String message) {
        logger.debug(message);
    }
}
