package com.zhuyifan.priorityqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import com.zhuyifan.priorityqueue.TaskExecutor;

/** 
* @Author zhuyifan
* @Time 2019年5月24日 上午10:19:30 
* @Version 1.0
*/
//某机构。
public class TaskQueue {

 private AtomicInteger mAtomicInteger = new AtomicInteger();
 // 某机构排的队，队里面是办事的人。
 private BlockingQueue<ITask1> mTaskQueue;
 // 好多窗口。
 private TaskExecutor[] mTaskExecutors;

 // 在开发者new队列的时候，要指定窗口数量。
 public TaskQueue(int size) {
     mTaskQueue = new PriorityBlockingQueue<>();
     mTaskExecutors = new TaskExecutor[size];
 }
 
//开始上班。
public void start() {
   stop();
   // 把各个窗口都打开，让窗口开始上班。
   for (int i = 0; i < mTaskExecutors.length; i++) {
       mTaskExecutors[i] = new TaskExecutor(mTaskQueue);
       mTaskExecutors[i].start();
   }
}

// 统一各个窗口下班。
public void stop() {
   if (mTaskExecutors != null)
       for (TaskExecutor taskExecutor : mTaskExecutors) {
           if (taskExecutor != null) taskExecutor.quit();
       }
}
public <T extends ITask1> int add(T task) {
    if (!mTaskQueue.contains(task)) {
        task.setSequence(mAtomicInteger.incrementAndGet()); // 注意这行。
        mTaskQueue.add(task);
    }
    return mTaskQueue.size();
}
}