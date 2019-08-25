package com.zhuyifan.redisqueue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author zhuyifan
 * @Time 2019年5月24日 上午10:31:41
 * @Version 1.0
 *          <p>
 * 			Description:TestRedisQueue.java:
 *          </p>
 */
public class TestRedisQueue {
	public static byte[] redisKey = Priority.DEFAULT.name().getBytes();
	// static {
	// try {
	// init();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// private static void init() throws IOException {
	// for (int i = 0; i < 1000; i++) {
	// Message message = new Message(i, "这是第" + i + "个内容");
	// JedisUtil.lpush(redisKey, ObjectUtil.object2Bytes(message));
	// }
	//
	// }

	public static void main(String[] args) {
		// 实验手动添加一个则消费一个
		System.out.print("输入:");
		Scanner scan = new Scanner(System.in);
		try {
			for (int i = 0; i < 10; i++) {
				Message message = new Message(i, "这是第" + i + "个内容");
				JedisUtil.lpush(Priority.LOW.name().getBytes(), ObjectUtil.object2Bytes(message));
			}
			for (int i = 10; i < 20; i++) {
				Message message = new Message(i, "这是第" + i + "个内容");
				JedisUtil.lpush(Priority.Immediately.name().getBytes(), ObjectUtil.object2Bytes(message));
			}
			for (int i = 20; i < 100; i++) {
				Message message = new Message(i, "这是第" + i + "个内容");
				JedisUtil.lpush(Priority.HIGH.name().getBytes(), ObjectUtil.object2Bytes(message));
			}

			//开始任务
			for(Priority key:Priority.values()) {
				new TaskExecutor().doTask(key.name().getBytes());
			}
			

			// new Thread(new Runnable() {
			//
			// @Override
			// public void run() {
			// while(true) {
			// try {
			// pop();
			// } catch (Exception e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			// }
			//
			//
			// }
			// }).start();
			// for(int i=0;i<100;i++){

			// //实验手动添加一个则消费一个
			// System.out.print("输入:");
			// Scanner scan = new Scanner(System.in);
			while (true) {
				int read = scan.nextInt();
				Message message = new Message(read, "这是第" + read + "个内容");
				JedisUtil.lpush(redisKey, ObjectUtil.object2Bytes(message));
			}

			// }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scan.close();
		}
	}

	private static void pop() throws Exception {
		// byte[] bytes = JedisUtil.rpop(redisKey);
		byte[] bytes = JedisUtil.brpop(redisKey);
		Message msg = (Message) ObjectUtil.bytes2Object(bytes);
		if (msg != null) {
			System.out.println(msg.getId() + "----" + msg.getContent());
		}
	}
}
