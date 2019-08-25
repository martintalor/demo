package com.zhuyifan.mythead;

import java.util.concurrent.atomic.AtomicInteger;

public class Demo implements Runnable {
    private static AtomicInteger count =new AtomicInteger(0);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Demo());
            thread.start();
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("result: " + count.get());
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000000; i++)
            count.getAndIncrement();
    }
}
