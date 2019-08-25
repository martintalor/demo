package com.zhuyifan.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class fawenjianserver {

	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(8848);
		Socket s = ss.accept();

		InputStream is = s.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String name = br.readLine();

		FileOutputStream fos = new FileOutputStream("C:\\" + name);
		// System.out.println("2:" + br.readLine());
		byte[] bt = new byte[2048];
		while (true) {
			int read = is.read(bt);
			if (read == -1) {
				fos.close();
				s.close();
				ss.close();
				return;
			}
			fos.write(bt, 0, read);
		}

	}

}
