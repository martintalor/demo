package com.zhuyifan.priorityqueue;

/** 
* @Author zhuyifan
* @Time 2019年5月24日 上午10:19:30 
* @Version 1.0
*/
public class Main {

	public static void main(String... args) {
		// 开一个窗口，这样会让优先级更加明显。
		TaskQueue taskQueue = new TaskQueue(1);
		

		// 为了显示出优先级效果，我们预添加3个在前面堵着，让后面的优先级效果更明显。
		taskQueue.add(new PrintTask(110));
		taskQueue.add(new PrintTask(112));
		taskQueue.add(new PrintTask(122));

		for (int i = 0; i < 10; i++) { // 从第0个人开始。
			PrintTask task = new PrintTask(i);
			if (1 == i) {
				task.setPriority(Priority.LOW); // 让第2个进入的人最后办事。
			} else if (8 == i) {
				task.setPriority(Priority.HIGH); // 让第9个进入的人第二个办事。
			} else if (9 == i) {
				task.setPriority(Priority.Immediately); // 让第10个进入的人第一个办事。
			}
			// ... 其它进入的人，按照进入顺序办事。
			taskQueue.add(task);
		}
		taskQueue.start(); // // 某机构开始工作。

		System.out.println(Priority.Immediately.name());
	}
}
