package kr.or.ddit.basic;

import java.io.FileReader;
import java.io.IOException;

/**
 * 문자기반 스트림 예제
 * 
 * @author PC-16
 *
 */
public class T08FileReaderTest {
	public static void main(String[] args) {
		FileReader fr = null;

		try {
			fr = new FileReader("d:/D_Other/testChar.txt");
			int data = 0;
			while ((data = fr.read()) != -1) {
				System.out.print(data);
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
