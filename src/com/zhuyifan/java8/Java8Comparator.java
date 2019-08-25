package com.zhuyifan.java8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Java8Comparator {
	public static void main(String[] args) {
		/*List<Human> humans = new ArrayList<>();
		humans.add(new Human("Sarah", 10));
		humans.add(new Human("Jack", 12));

		humans.sort((Human h1, Human h2) -> h1.getName().compareTo(h2.getName()));
		//humans.sort((h1, h2) -> h1.getName().compareTo(h2.getName()));//简化
		humans.forEach(System.out::println);*/
		
		/*List<Human> humans = new ArrayList<>();
		humans.add(new Human("Sarah", 10));
		humans.add(new Human("Jack", 12));

		humans.sort(Human::compareByNameThenAge);
		humans.forEach(System.out::println);*/
		
		/*List<Human> humans = new ArrayList<>();
		humans.add(new Human("Sarah", 10));
		humans.add(new Human("Jack", 12));

		Collections.sort(humans, Comparator.comparing(Human::getName));
		Collections.sort(humans, Comparator.comparing(Human::getName).reversed());
		humans.forEach(System.out::println);*/
	
	/*List<Human> humans = new ArrayList<>();
	humans.add(new Human("Sarah", 10));
	humans.add(new Human("Jack", 12));
	humans.add(new Human("Jack", 10));

	humans.sort((lhs, rhs) -> {
	            if (lhs.getName().equals(rhs.getName())) {
	                return Integer.compare(lhs.getAge(), rhs.getAge());
	            } else {
	                return lhs.getName().compareTo(rhs.getName());
	            }
	        });
	humans.forEach(System.out::println);*/
		List<Human> humans = new ArrayList<>();
		humans.add(new Human("Sarah", 10));
		humans.add(new Human("Jack", 12));
		humans.add(new Human("Jack", 10));

		humans.sort(Comparator.comparing(Human::getName).thenComparing(Human::getAge));
		humans.forEach(System.out::println);
	}
	
}
