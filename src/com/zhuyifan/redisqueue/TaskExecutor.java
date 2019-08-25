package com.zhuyifan.redisqueue;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhuyifan
 * @Time 2019年5月24日 下午2:03:22
 * @Version 1.0
 *          <p>
 *          Description:TaskExecutor.java:
 *          </p>
 */
public class TaskExecutor {

	private ExecutorService threadPool;

	class TaskThreadFactory implements ThreadFactory {

		private int counter;
		private String name;

		public TaskThreadFactory(String name) {
			counter = 0;
			this.name = name;
		}

		@Override
		public Thread newThread(Runnable run) {
			Thread t = new Thread(run, name + "-Thread-" + counter);
			counter++;
			return t;
		}
	}

	public void doTask(byte[] redisKey) {
		int threads = Runtime.getRuntime().availableProcessors();
		this.initThreadPool(threads);
		threadPool.execute(() -> {
			try {
				while (true) {
							pop(redisKey);
				
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				shutdown();
			}
		});

	}

	/**
	 * 取任务
	 */
	private static void pop(byte[] redisKey) throws Exception {
		// byte[] bytes = JedisUtil.rpop(redisKey);
		byte[] bytes = JedisUtil.brpop(redisKey);
		Message msg = (Message) ObjectUtil.bytes2Object(bytes);
		if (msg != null) {
			System.out.println(msg.getId() + "----" + msg.getContent());
		}
	}

	/**
	 * 初始化线程池
	 */
	private void initThreadPool(int threads) {
		if(threadPool == null) {
			threadPool = new ThreadPoolExecutor(threads, threads, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
		}	
	}

	/**
	 * 关闭线程池
	 */
	private void shutdown() {
		if (!threadPool.isShutdown()) {
			threadPool.shutdown();
		}
	}

}
