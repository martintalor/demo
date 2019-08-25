package com.zhuyifan.util;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

/** 
* @Author zhuyifan
* @Time 2019年6月4日 上午9:56:17 
* @Version 1.0
* <p>Description:ConcurrentCache.java:</p>
*/
public class ConcurrentCache {
    private static ConcurrentHashMap<String, Object> cacheMap = new ConcurrentHashMap<>();
    private ConcurrentCache(){}


    /**
     *@描述
     *@参数  [key]
     *@返回值  java.lang.Object
     *@创建人  朱一帆
     *@创建时间  2019/2/22
     *@修改人和其它信息
     */
    public static Object getFieldValue(String key) {
        // 缓存已有的tag以及根据tag查询的数据
        if (cacheMap.containsKey(key)) {
            return cacheMap.get(key);
        } else {
            return null;
        }
    }


    /**
     *@描述
     *@参数  [key, value]
     *@返回值  void
     *@创建人  朱一帆
     *@创建时间  2019/2/22
     *@修改人和其它信息
     */
    public static void setFieldValue(String key, Object value) {
        cacheMap.put(key, value);
    }

    /**
     *@描述
     *@参数  [key]
     *@返回值  void
     *@创建人  朱一帆
     *@创建时间  2019/2/22
     *@修改人和其它信息
     */
    public static void removeValue(String key) {
    	if (cacheMap.containsKey(key)) {
    		cacheMap.remove(key);
    	}
    }

    /**
     *@描述
     *@参数  [key]
     *@返回值  boolean
     *@创建人  朱一帆
     *@创建时间  2019/2/22
     *@修改人和其它信息
     */
    public static boolean contanisKey(String key){

    	if(StringUtils.isNotBlank(key)){
    		return cacheMap.containsKey(key);
    	}
    	return false;
    }
}
