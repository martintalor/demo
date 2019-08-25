package com.zhuyifan.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/** 
* @Author zhuyifan
* @Time 2019年6月3日 下午4:42:44 
* @Version 1.0
* <p>Description:Text.java:</p>
*/
public class Text{ 
    public static void main(String args[]){
    	TimeUtil tt = new TimeUtil();
        String yz_time=tt.getTimeInterval(new Date());//获取本周时间
        String array[]=yz_time.split(",");
        String start_time=array[0];//本周第一天
        String end_time=array[1];  //本周最后一天 
         
		try {
			 //格式化日期     
	          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	          Date dBegin = sdf.parse(start_time);  
	          Date dEnd = sdf.parse(end_time);
	          List<Date> lDate = tt.findDates(dBegin, dEnd);//获取这周所有date
	          for (Date date : lDate)  
	          {  
	           System.out.println(sdf.format(date));  
	          }
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
          
     }
}
