package com.wuliu.entity;

import java.io.IOException;
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

public class AppiumServier {
	private AndroidDevice device; 
	private Process process;
	//开启appium服务时需要占用的端口号
	private int port;
	private String appiumLogPath;
	
	public AppiumServier(AndroidDevice device,Process process,int port,String appiumLogPath) {
		super();
		this.device = device;
		this.process = process;
		this.port = port;
		this.appiumLogPath = appiumLogPath;
	}

	public void startAppium(long milliseconds) {
		try {
			//使用cmd命令连接设备
			Runtime.getRuntime().exec("adb connect "+device.getDeviceName());
			long start = System.currentTimeMillis();
			boolean state = isRunning(port);
			while(!state) {
				//使用cmd命令开启appium服务
				process = Runtime.getRuntime().exec("cmd /c appium -a 127.0.0.1 -p "+port+" --session-override -U "+device.getDeviceName()+">"+appiumLogPath);
				System.out.println("开启的线程为："+process);
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
	
	public void stopAppium() {
        if (process != null) {
        	try {
        		//负责建立线程并等待线程结束
				process.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            process.destroy();
            System.out.println("appium服务已关闭");
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
            System.out.println("appium已经启动了，无需再次启动");
            LogUtils.info("appium已经启动了，无需再次启动");
            return status == 0;
        } catch (Exception e) {
            return false;
        }

    }

}
