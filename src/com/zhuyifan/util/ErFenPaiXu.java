package com.zhuyifan.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ErFenPaiXu {

	public static void main(String[] args) {
		//二分法查找的数组必须为由大到小有序排列
		int[] sss=new int[] {24,45,12,23,44,87} ;
		Arrays.sort(sss);
		int binarySerach = binarySerach(sss, 44);
		
		System.out.println(binarySerach);
		
		
		 List<Integer> list = new ArrayList<Integer>();
	        list.add(1);
	        list.add(55);
	        list.add(9);
	        list.add(0);
	        list.add(2);
	        Collections.sort(list);//使用Collections的sort方法
	        Collections.sort(list);
	        for(int a :list){
	            System.out.println(a);
	        }
	        
	        
	        Collections.sort(list, new Comparator<Integer>() {
	            public int compare(Integer o1, Integer o2) {
	                return o2 - o1;
	            }
	        });//使用Collections的sort方法，并且重写compare方法

	}
	
	/**
	 * 二分查找，找到该值在数组中的下标，否则为-1
	 */
	public static int binarySerach(int[] array, int key) {
	    int left = 0;
	    int right = array.length - 1;

	    // 这里必须是 <=
	    while (left <= right) {
	        int mid = (left + right) / 2;
	        if (array[mid] == key) {
	            return mid;
	        }
	        else if (array[mid] < key) {
	            left = mid + 1;
	        }
	        else {
	            right = mid - 1;
	        }
	    }

	    return -1;
	}

}
