package com.zhuyifan.http;
/** 
* @Author zhuyifan
* @Time 2019年6月13日 下午9:57:14 
* @Version 1.0
* <p>Description:RestUtil.java:</p>
*/

import org.springframework.web.client.RestTemplate;

public class RestUtil {
	
	public static void post(String url,String request) {
		
		//发送post请求
		RestTemplate rest =new RestTemplate();
		rest.postForObject(url, request, String.class);
	}

}
