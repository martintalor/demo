package com.zhuyifan.java8;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/**
 * 
 * <pre>
 *  Java8 的 forEach方法演示实例
 *      通过遍历打印集合元素比较 新旧方式的使用区别
 * </pre>
 * @author Byron.Y.Y
 */
public class Java8ForEachDemo2 {
    public static void main(String[] args) {

                List<Integer> myList = new ArrayList<Integer>();
                for(int i=0; i<10; i++) myList.add(i);

                System.out.println("<<<<<<<<Java8之前迭代方式start...>>>>>>>");
                Iterator<Integer> it = myList.iterator();
                while(it.hasNext()){
                    Integer i = it.next();
                    System.out.println("Iterator Value::"+i);
                }
                System.out.println("<<<<<<<<Java8之前迭代方式end.>>>>>>>");

                System.out.println("<<<<<<<<Java8使用forEach新迭代方式start...>>>>>>>");
                myList.forEach(s -> {System.out.println("Iterator Value::"+s);});

                System.out.println("<<<<<<<<Java8使用forEach新迭代方式end.>>>>>>>");


                System.out.println("使用自定义的消费动作行为处理集合元素:");
                //创建自定义消费行为动作实例
                MyConsumer myConsumer = new MyConsumer();
                myList.forEach(myConsumer);
    }


    static class MyConsumer implements Consumer<Object>{

        @Override
        public void accept(Object t) {
            System.out.println("打印输出(动作执行的参数)：" + t);
        }

    }
}

