package com.zhuyifan.redisqueue;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;

import javax.swing.Popup;

/**
 * @Author zhuyifan
 * @Time 2019年5月29日 下午2:11:32
 * @Version 1.0
 *          <p>
 *          Description:TestLock.java:
 *          </p>
 */
public class TestLock {
	private static CountDownLatch cdl = new CountDownLatch(20);
	private static String lockKey = "lockKey";

	public static void main(String[] args) {

		for (int i = 0; i < 20; i++) {
			new MyThread("mythread" + i).start();
			cdl.countDown();
		}
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}


	// 内部类
	static class MyThread extends Thread {
		private String threadName;

		public MyThread() {
		};

		public MyThread(String threadName) {
			this.threadName = threadName;
		}

		@Override
		public void run() {
			try {
				System.out.println(threadName);
				cdl.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			String requestId = UUID.randomUUID().toString();
			if (JedisUtil.tryGetDistributedLock(lockKey, requestId, 1000*60)) {
				System.out.println("线程-" + threadName + "获得锁");
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(requestId);
				boolean releaseDistributedLock = JedisUtil.releaseDistributedLock(lockKey, requestId);
				System.out.println(releaseDistributedLock);
				if (releaseDistributedLock) {
					System.out.println("线程-" + threadName + "解放锁");
				}

			}
		}
	}
}
