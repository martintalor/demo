package com.zhuyifan.util;

import java.io.File;
import java.io.FileFilter;

public class FileTest {

	public static void main(String[] args) {
		File a=new File("D:/");
		listFile(a);

	}
	
	private static void listFile(File file) {
		File[] listFiles = file.listFiles();
		for(File fi:listFiles) {
			System.out.println(fi.getName());
		}
	}

}
