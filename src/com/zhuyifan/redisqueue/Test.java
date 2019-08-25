package com.zhuyifan.redisqueue;

import java.io.IOException;

/**
 * @Author zhuyifan
 * @Time 2019年5月24日 下午4:15:55
 * @Version 1.0
 *          <p>
 * 			Description:Test.java:
 *          </p>
 */
public class Test {
	public static byte[] redisKey = "key".getBytes();
	static {
		try {
			init();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void init() throws IOException {
		for (int i = 0; i < 1000; i++) {
			Message message = new Message(i, "这是第" + i + "个内容");
			JedisUtil.lpush(redisKey, ObjectUtil.object2Bytes(message));
		}

	}

	public static void main(String[] args) {
		try {
			while (true) {
				try {
					pop();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void pop() throws Exception {
		//byte[] bytes = JedisUtil.rpop(redisKey);
		byte[] bytes = JedisUtil.brpop(redisKey);
		Message msg = (Message) ObjectUtil.bytes2Object(bytes);
		if (msg != null) {
			System.out.println(msg.getId() + "----" + msg.getContent());
		}
	}

}
