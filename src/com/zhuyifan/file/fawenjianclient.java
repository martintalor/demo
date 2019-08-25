package com.zhuyifan.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class fawenjianclient {

	public static void main(String[] args) throws IOException {
		byte[] bt = new byte[2048];
		InetAddress ip = InetAddress.getLocalHost();
	
		System.out.println(InetAddress.getByName("��һ��"));
		System.out.println(ip);
		Socket s = new Socket(ip, 8848);
		File f = new File("D:\\bd_logo1.txt");
		String name1 = f.getName();

		OutputStream os = s.getOutputStream();
		PrintWriter pw = new PrintWriter(os);
		pw.println(name1);
		pw.flush();

		FileInputStream fis = new FileInputStream(f);
		
		while (true) {
			int read = fis.read(bt);
			if (read == -1) {
				System.out.println("�������");
				// fis.close();
				s.close();
				return;
			}

			os.write(bt, 0, read);

		}

	}

}
