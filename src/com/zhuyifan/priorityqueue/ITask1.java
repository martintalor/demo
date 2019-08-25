package com.zhuyifan.priorityqueue;

/** 
* @Author zhuyifan
* @Time 2019年5月24日 上午10:19:30 
* @Version 1.0
*/
public interface ITask1 extends Comparable<ITask1>{
	 
    void run();
 
    void setPriority(Priority priority);
 
    Priority getPriority();
    
    void setSequence(int sequence);
    
    int getSequence();
}
