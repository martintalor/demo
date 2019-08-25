package com.zhuyifan.test;

import java.util.Arrays;
import java.util.Random;

/**
 * @Author zhuyifan
 * @Time 2019年6月16日 下午4:32:47
 * @Version 1.0
 *          <p>
 *          Description:Ball.java:
 *          </p>
 */
public class Ball {

	public static void main(String[] args) {
		int[] redBall = new int[6];
		int blueBall = 0;

		Random rand = new Random();
		// 1个篮球1~16
		blueBall = rand.nextInt(16) + 1;

		// 6个红球 1~33，并且不能重复
		for (int i = 0; i < redBall.length; i++) {
			redBall[i] = rand.nextInt(33) + 1;
			for (int j = 0; j < i; j++) {
				if (redBall[j] == redBall[i]) {// 判断重复
					i--;
					break;
				}
			}
		}

		Arrays.sort(redBall);// 排序

		// 输出，当小于10的时候，输出"0" + redBall
		System.out.println("随机产生的6个红球为");
		for (int i = 0; i < 6; i++) {
			if (redBall[i] < 10) {
				System.out.print("0" + redBall[i] + " ");
			} else {
				System.out.print(redBall[i] + " ");
			}

		}

		System.out.println("\n随机产生的6个红球为");

		if (blueBall < 10) {
			System.out.print("0" + blueBall + " ");
		} else {
			System.out.print(blueBall + " ");
		}

	}
	

}
