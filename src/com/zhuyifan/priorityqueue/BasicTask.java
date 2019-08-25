package com.zhuyifan.priorityqueue;

/** 
* @Author zhuyifan
* @Time 2019年5月24日 上午10:19:30 
* @Version 1.0
*/
public abstract class BasicTask implements ITask1 {
	 
    // 默认优先级。
    private Priority priority = Priority.DEFAULT;
    private int sequence;
 
    @Override
    public void setPriority(Priority priority) {
        this.priority = priority;
    }
 
    @Override
    public Priority getPriority() {
        return priority;
    }
 
    @Override
    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
 
    @Override
    public int getSequence() {
        return sequence;
    }
 
    // 做优先级比较。
    @Override
    public int compareTo(ITask1 another) {
        final Priority me = this.getPriority();
        final Priority it = another.getPriority();
        return me == it ?  this.getSequence() - another.getSequence() :
            it.ordinal() - me.ordinal();
    }
}
