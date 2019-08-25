package com.zhuyifan.test;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;

import com.zhuyifan.coding.UnicodeToChinese;
import com.zhuyifan.http.HttpGetConnect;

import net.sf.json.JSONArray;

public class SpiderQuestion {
	
	public static String url = "https://www.finerit.com/tiku/search/?s_type=erya";
	public static String url1 = "https://www.finerit.com/tiku/suggest/?s=%E6%88&s_type=erya&_=1558532992202";

	public static void main(String[] args) throws IOException {
		HttpGetConnect connect = new HttpGetConnect();
        String content = connect.connect(url, "utf-8");
        System.out.println(content);
//        String decode = URLDecoder.decode(content);
//        String decodeUnicode = UnicodeToChinese.decodeUnicode(content);
//        System.out.println(decodeUnicode);
//        JSONArray jsonArray = JSONArray.fromObject(decodeUnicode);
//        List<String> list = (List<String>)jsonArray.toCollection(jsonArray, String.class);
//        for(String a:list) {
//        	System.out.println(a);
//        }
//        System.out.println(jsonArray);
//		String encode = URLEncoder.encode("若已定义charc='a'，则变量c所占的字节数为__________。");
//		System.out.println(encode);

	}

}
