package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class tuilpsCopy {
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		FileInputStream fis = null;
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		try {
			fis = new FileInputStream("d:/D_Other/Tulips.jpg");
			fos = new FileOutputStream("d:/D_Other/복사본_Tulips.jpg");
			
			bis = new BufferedInputStream(fis);
			bos = new BufferedOutputStream(fos);
			int data = 0;
			
			while((data = bis.read()) != -1) {
				bos.write(data);
			}
//			fis.close();
//			fos.close();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("경과 시간(ms) : " + (endTime - startTime));
	}
}
