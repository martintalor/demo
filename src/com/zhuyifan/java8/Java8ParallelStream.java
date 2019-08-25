package com.zhuyifan.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Java8ParallelStream {

    public static void main(String[] args) throws Exception {
        //准备一个数据源集合，测试将集合元素拼接一个字符放入另一个集合
        ArrayList<String> resourceList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            resourceList.add(i + "");
        }

        //普通处理。。。。。。。。。。。。。。。。。。。。。。。。。。。
        long t1 = System.currentTimeMillis();
        ArrayList<String> s1 = new ArrayList<>();
        for (String s : resourceList) {
            Thread.sleep(500);//模拟此处有0.5s的其它操作
            s1.add(s + "普通测试");
        }
        long t2 = System.currentTimeMillis();

        //JKD8 parallelStream处理。。。。。。。。。。。。。。。。。。。
        long t3 = System.currentTimeMillis();
        List<String> s2 = resourceList.parallelStream().map(e -> {
            try {
                Thread.sleep(500);//模拟此处有0.5s的其它操作
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            return e + "Stream测试";
        }).collect(Collectors.toList());
        long t4 = System.currentTimeMillis();

        System.out.println(t2 - t1);
        System.out.println(t4 - t3);
        //结论：1.多次测量输出：5000：1500；
        //     2.当业务的集合迭代处理过程中有较耗时操作时，利用多核CPU及JDK8 并行api，能明显提升效率；
        //     3.简单集合迭代，无耗时操作不推荐stream

    }
}

