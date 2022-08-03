package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 파일 출력 예제
 * @author PC-16
 *
 */
public class T06FileStreamTest {
	public static void main(String[] args) {
		
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream("d:/D_Other/out.txt");
			for(char ch ='a'; ch<='z'; ch++) {
				fos.write(ch);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}finally{
			try {
				fos.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		FileInputStream fis = null;
		try {
			 fis = new FileInputStream(new File("d:/D_Other/out.txt"));
			 int data = 0;
			 while((data = fis.read())!= -1) {
				 System.out.print((char) data);
			 }
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fis.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}
