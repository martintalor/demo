
package sample;

import java.awt.Container;

import java.awt.FlowLayout;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.io.*;

import java.util.ArrayList;

import java.util.Arrays;

import java.util.List;

 

import javax.swing.JButton;

import javax.swing.JFileChooser;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.SwingUtilities;

import javax.swing.UIManager;

import javax.swing.UnsupportedLookAndFeelException;

class Filter implements FileFilter{

	public boolean accept(File file){

		if(file.getName().endsWith(".java")||file.isDirectory())

			return true;

		return false;

	}

}

public class AddPackage {

   public static final Filter FILTER=new Filter();

   public File getRootFile(){

	   File file=null;

	   try{

		   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		   JFileChooser jfc=new JFileChooser();

		   jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		   int result=jfc.showOpenDialog(null);

		   if(result==JFileChooser.APPROVE_OPTION){

			   file=jfc.getSelectedFile();

			   System.out.println(file.getName());	   

		   }

		  

	   }catch(Exception e){

		   e.printStackTrace();

		   System.out.println(e.getMessage());

	   }

	 return file;

   }

   public void start(){

	   File file=getRootFile();

	   if(file!=null){

		   addPackage(file,"");

	   }else{

		   System.out.println("the file is null");

	   }

   }

   public void addPackage(File file,String path){

	   if(file.isFile()){

		   add(file,path);

		   return;

	   }else{

		   File files[]=file.listFiles(FILTER);

		 //  System.out.println("files.size="+files.length);

		   for(File temp:files){

			   if(temp.isFile()){

				   addPackage(temp,path);

			   }else{

				   addPackage(temp,path+"."+temp.getName());

			   }

		   }

	   }

   }

   public boolean add(File file,String path){

	   if(path.equals(""))

		   return false;

	   if(path.startsWith(".")){

		   path=path.substring(1, path.length());

	   }

	   RandomAccessFile raf;

	   try{

		   raf=new RandomAccessFile(file,"rw");

		   if(isContainPackage(raf)==false){  

			   writePackage(raf,path);

			   return true;

		   }

		   raf.close();

	   }catch(Exception e){

		   e.printStackTrace();

		   System.out.println(e.getMessage());

	   }

	 

	   return false;// never reach here

   }

   public void writePackage(RandomAccessFile raf,String target) {

	   try{

		   target="package "+target+";\n";

		   raf.seek(0);

		   int length=(int)raf.length();

		   int size=200;//一次读入字节数

		   byte []array;

		   int count=length/size;

		   //System.out.println("the file length="+length);

		   //System.out.println("count="+count);

		   List<byte[]> list=new ArrayList<byte[]>();

		   for(int i=0;i<count+1;i++){

			   array=new byte[size];

			   raf.read(array);

			   list.add(array);

			  // System.out.println(new String(array));

		   }

		   raf.seek(0);

		   raf.write(target.getBytes());

		  // System.out.println("write");

		   int listSize=list.size();

		   for(int i=0;i<listSize-1;i++){

			   raf.write(list.get(i));

		   }

		   String lastLine=new String(list.get(listSize-1));

		  // System.out.println("the lastLine is"+lastLine+"end");

		   raf.write(lastLine.trim().getBytes());

		  /* for(byte[] temp:list){

			   System.out.println("toString:"+Arrays.toString(temp)+"end");

			   raf.write(temp);

		   }*/

		  // System.out.println(list.size());

		  // System.out.println("write after");

		   raf.close();

	   }catch(Exception e){

		   e.printStackTrace();

		   System.out.println(e.getMessage());

	   }

	  

   }

   public boolean isContainPackage(File file) throws FileNotFoundException{

	   return isContainPackage(new RandomAccessFile(file,"r"));

   }

   public boolean isContainPackage(RandomAccessFile raf){

	   try{

		   raf.seek(0);

		   String s="";

		   while((s=raf.readLine())!=null){ 

				  // System.out.println("read:  "+s);

				   if(s.startsWith("package")){

					   return true;

				   }

		   }

	   }catch(Exception e){

		   e.printStackTrace();

		   System.out.println(e.getMessage());

	   }

	   return false;

   }

   public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{

	   AddPackage app=new AddPackage();

	   app.start();

	   System.out.println("exit");

   } 

}


