package com.zhuyifan.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * ForiForeachJava8ForeachTest
 *
 * @author ascend
 * @date 2018/6/22 15:28.
 */
public class ForiForeachJava8ForeachTest {


    public static void main(String[] args) {
        // 预热
        List<Dog> tmpList = listDog(10);
        testFori(tmpList);
        testForeach(tmpList);
        testJava8ForEach(tmpList);

        List<Integer> list = Arrays.asList(10, 50, 250, 1000, 2000, 3000, 5000, 10000, 20000);
        for (int i = 0; i < list.size(); i++) {
            test(list.get(i));
        }
    }

    public static void test(int size) {
        System.out.println("-----------次数:" + size + "------------");
        List<Dog> list = listDog(size);
        long nanoTime = System.nanoTime();
        testJava8ForEach(list);
        long nanoTime3 = System.nanoTime();
        testFori(list);
        long nanoTime1 = System.nanoTime();
        testForeach(list);
        long nanoTime2 = System.nanoTime();
        

        System.out.println("fori\t\t\t\t" + (int) (nanoTime1 - nanoTime3) / 1000.0 + " ms");
        System.out.println("增强for\t\t\t\t" + (int) (nanoTime2 - nanoTime1) / 1000.0 + " ms");
        System.out.println("java8 foreach\t\t" + (int) (nanoTime3 - nanoTime) / 1000.0 + " ms");
        System.out.println();
    }

    /**
     * 初始化list
     *
     * @param size int
     * @return list
     */
    public static List<Dog> listDog(int size) {
        List<Dog> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(new Dog(i + 1, "dog " + (i + 1)));
        }
        return list;
    }

    /**
     * 测试fori
     *
     * @param list List
     */
    public static void testFori(List<Dog> list) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).hashCode();
        }
    }

    /**
     * 测试增强for循环
     *
     * @param list List
     */
    public static void testForeach(List<Dog> list) {
        for (Dog dog : list) {
            dog.hashCode();
        }
    }

    /**
     * 测试java8的foreach
     *
     * @param list List
     */
    public static void testJava8ForEach(List<Dog> list) {
        list.forEach(dog -> dog.hashCode());
    }
}

/**
 * 测试实体类，用来计算hashCode
 */
class Dog {
    private int age;
    private String name;

    public Dog(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + age;
        result = 31 * result + (name == null ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Dog)) {
            return false;
        }
        Dog dog = (Dog) obj;
        return dog.age == this.age &&
                Objects.equals(dog.name, this.name);
    }
}

