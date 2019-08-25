package com.zhuyifan.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Java8Stream {
	public static void main(String[] args) {
		compareForAndStream();
		testParallelStream();
		testReduce();
		
	}
	
	public static void compareForAndStream() {
        //p1表示for性能,p2表示流处理性能
        long p1 = 0, p2 = 0;
        int n = 500000;
        ArrayList<Integer> arr = new ArrayList();
        for(int j = 0; j < 100; j ++) {
            for(int i = 0; i < n; i ++) {
                arr.add(i);
            }
            Integer sum = 0;
            long t1 = System.nanoTime();
            for(Integer v : arr) {
                sum = sum + v;
            }
            long t2 = System.nanoTime();
            arr.stream().reduce(0, (a, b) -> a + b);
            //arr.stream().parallel().reduce(0, (a, b) -> a + b);
            long t3 = System.nanoTime();
            p1 += (t2 - t1);
            p2 += (t3 - t2);
            arr.clear();
        }
        System.out.println(p1 / 100 / 1000);
        System.out.println(p2 / 100 / 1000);

    }

	public static void testParallelStream() {
        int[] arr = IntStream.range(1, 5).toArray();
        new Thread(() -> {
            Arrays.stream(arr).parallel().forEach((v) -> {
                    try {
                        System.out.println("first:" + v);
                        int sum = 0;
                        for(long i = 0; i < (1<<28); i ++) {
                            sum += i % 2;
                        }
                        System.out.println("first:" + v + ":" + sum);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        }).start();
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            Arrays.stream(arr).parallel().forEach((v) -> {
                try {
                    System.out.println("first1:" + v);
                    int sum = 0;
                    for(long i = 0; i < (1<<30); i ++) {
                        sum += i % 2;
                    }
                    System.out.println("first1:"+ v + ":" + sum);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }).start();
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
      
        Arrays.stream(arr).forEach((v)-> {
            System.out.println("second"+v);});
    }
	
	/**
     *  reduce的使用，第一个参数是种子，如果不传递将以流的第一个数据为种子
     *  第二个参数是累积器accumulator，迭代对流的数据操作，第三个参数combiner不填默认同第二个参数相同，
     *  第三个参数只在并行流下有意义，当且仅当
     *  combiner.apply(u, accumulator.apply(identity, t)) == accumulator.apply(u, t)
     *  时，计算结果与串行流相同
     */
    public static void testReduce() {
        IntStream stream = IntStream.range(1, 100);
        System.out.println(stream.sum());
        stream = IntStream.range(1, 100);
        //并行计算1-99的和，种子值为1，由于存在并行计算，因此结果将会比预期值大
        System.out.println(stream.parallel().reduce(1, (a, b) -> a + b));
        stream = IntStream.range(1, 100);
        //种子值为0，与预期相符
        System.out.println(stream.parallel().reduce(0, (a, b) -> a + b));
        //串行计算
        stream = IntStream.range(1, 100);
        System.out.println(stream.reduce(0, (a, b) -> a + b));

        //串行计算平方和
        stream = IntStream.range(1, 5);
        System.out.println(stream.reduce(0, (a, b) -> a + b * b ));
        //并行计算平方和，combiner这样写得不到想要的结果
        stream = IntStream.range(1, 5);
        System.out.println(stream.parallel().reduce(0, (a, b) -> a + b * b));
        //适合计算平方和的combiner
        System.out.println(
                Stream.of(1,2,3,4).parallel()
                        .reduce(0, (a, b) -> a + b * b, (a, b) -> a + b));
    }

}
