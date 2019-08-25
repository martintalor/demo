package com.zhuyifan.java8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 
 * <pre>
 *  Java8 的 forEach方法演示实例
 *      通过遍历打印集合元素比较 新旧方式的使用区别
 * </pre>
 * @author Byron.Y.Y
 */
public class Java8ForEachDemo {
    public static void main(String[] args) {
    	
    	Map<String, Integer> items = new HashMap<>();
    	items.put("A", 10);
    	items.put("B", 20);
    	items.put("C", 30);
    	items.put("D", 40);
    	items.put("E", 50);
    	items.put("F", 60);

    	for (Map.Entry<String, Integer> entry : items.entrySet()) {
    	    System.out.println("Item : " + entry.getKey() + " Count : " + entry.getValue());
    	}
    	
    	/*Map<String, Integer> items = new HashMap<>();
    	items.put("A", 10);
    	items.put("B", 20);
    	items.put("C", 30);
    	items.put("D", 40);
    	items.put("E", 50);
    	items.put("F", 60);

    	items.forEach((k,v)->System.out.println("Item : " + k + " Count : " + v));
*/
    	items.forEach((k,v)->{
    	    System.out.println("Item : " + k + " Count : " + v);
    	    if("E".equals(k)){
    	        System.out.println("Hello E");
    	    }
    	});

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
                myList.forEach(System.out::println);

                System.out.println("<<<<<<<<Java8使用forEach新迭代方式end.>>>>>>>");
    }
}

