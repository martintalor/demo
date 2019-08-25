package com.zhuyifan.util;

public class Compare02 {
	 public static void main(String[] args) {
	        String s1 = "BF Replacement Coils for Cubis Tank & eGo AIO (5pcs)";
	        /*String s2 = "BF Badge Replacement Coils for Cubis Tank 3pcs/pack";*/
	        
	        String s2 = "UD Athlon 25 Replacement Octuple Coil 2pcs";
	        
	        int t1 = s1.hashCode() - s2.hashCode();
	        
	        int max = Integer.MAX_VALUE;
	        
	        System.out.println((1 - (double)Math.abs(t1) / max)*100 );
	    }
}
