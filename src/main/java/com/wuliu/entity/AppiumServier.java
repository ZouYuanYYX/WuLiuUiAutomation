package com.wuliu.entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.wuliu.utils.LogUtils;
/**
 * 开启appium服务的类
 * @author joy
 * @data 2018年01月19日
 */

public class AppiumServier {
	/**
	 * 一台安卓设备开启一个appium服务
	 */
	private AndroidDevice device; 
	/**
	 * 开启appium服务时需要占用的端口号
	 */
	private int port;
	private String appiumLogPath;
	
	public AppiumServier(AndroidDevice device,int port,String appiumLogPath) {
		super();
		this.device = device;
		this.port = port;
		this.appiumLogPath = appiumLogPath;
	}

	public void startAppium() {
		//开启appium服务,45000为毫秒，即45秒
		long milliseconds = 45000;
		try {
			//使用cmd命令连接安卓设备
			Runtime.getRuntime().exec("adb connect "+device.getDeviceName());
			long start = System.currentTimeMillis();
			boolean state = isRunning(port);
			//使用cmd命令开启appium服务
			String appiumCmd = "cmd /c appium -a 127.0.0.1 -p "+port+" --session-override -U "+device.getDeviceName()+">"+appiumLogPath;
			System.out.println("需要开启appium服务的命令为："+appiumCmd);
			Process process = Runtime.getRuntime().exec(appiumCmd);
			while(!state) {
				System.out.println("开启的进程为："+process);
				long end = System.currentTimeMillis();
				if (end-start > milliseconds) {
					this.stopAppium();
	                System.out.println("Appium can't be lanuched in "
	                        + milliseconds + " seconds");
	                break;
				} 
				state = isRunning(port);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void stopAppium(){
		String cmd =  "cmd /c netstat -aon | findstr "+port;
		System.out.println(cmd);
		Process p;
		try {
			p = Runtime.getRuntime().exec(cmd);
			BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream(),"gbk"));
			String line=null;
			if((line=reader.readLine())!=null){
			    System.out.println("line:"+line);
			    String[] lineArray = line.split(" ");
			    String pid = lineArray[lineArray.length-1].trim();
			    System.out.println("打印 获取的进程pid号："+pid);
			    Runtime.getRuntime().exec("taskkill -f -pid " + pid);
			    System.out.println(port+"端口对应appium服务已关闭");
				p.waitFor();
			    p.destroy();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}  catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 判断appium服务是否已经启动
	 * @return
	 */
	private boolean isRunning(int port) {
    	final HttpClient httpClient = HttpClients.createDefault();
        try {
            URI uri = new URIBuilder().setScheme("http").setHost("127.0.0.1")
                    .setPort(port).setPath("/wd/hub/status").build();
            HttpGet httpget = new HttpGet(uri);
            HttpResponse response = httpClient.execute(httpget);
            HttpEntity entity = response.getEntity();
            String rs = EntityUtils.toString(entity);
            JsonElement json = new JsonParser().parse(rs);
            int status = json.getAsJsonObject().get("status").getAsInt();
            System.out.println("appium启动成功");
            LogUtils.info("appium启动成功");
            return status == 0;
        } catch (Exception e) {
            return false;
        }

    }

}
