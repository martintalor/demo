package com.zhuyifan.http;

import net.sf.json.JSONObject;

public class Test {

	public static void main(String[] args) {

		test(5);
	}

	//c成为创建几个文件夹 
	public static void test(int c) {
		for(int b=0;b<c;b++) {
			String sendGet = HttpRequest.sendGet("http://localhost:99/dep/service/dataPack/getFileDir", "APP-F1",
					"APP-G1,APP-J1");
			JSONObject jsonobject = JSONObject.fromObject(sendGet);
			String path=String.valueOf(jsonobject.get("path"));
			ForFile.test(path);
			System.out.println(path);
		}
		
	
	}
}
