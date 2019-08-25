package com.zhuyifan.queue;

public class Main {
	 
    public static void main(String... args) {
        // 这里暂时只开一个窗口。
//        TaskQueue taskQueue = new TaskQueue(1);
//        taskQueue.start();
// 
//        for (int i = 0; i < 10; i++) {
//            PrintTask task = new PrintTask(i);
//            taskQueue.add(task);
//        }
        
        // 开三个窗口。
        TaskQueue taskQueue = new TaskQueue(3);
        taskQueue.start(); // 某机构开始工作。
 
        for (int i = 0; i < 10; i++) {
            // new 10 个需要办事的人，并且进入某机构办事。
            PrintTask task = new PrintTask(i);
            taskQueue.add(task);
        }
    }
 
}
