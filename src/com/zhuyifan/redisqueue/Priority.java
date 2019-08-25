package com.zhuyifan.redisqueue;

/** 
* @Author zhuyifan
* @Time 2019年5月24日 上午10:19:30 
* @Version 1.0
*/
public enum Priority {
    Immediately, // 立刻执行。
    HIGH, // 高于默认级别。   
    DEFAULT, // 默认级别。
    LOW // 最低。

}
