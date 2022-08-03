package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 문자기반 스트림을 위한 Buffered스트림 사용 예제
 * @author PC-16
 *
 */
public class T12BufferedIOTest {
	public static void main(String[] args) {
		FileReader fr = null;
		BufferedReader br = null;
		try {
			// 이클립스에서 만든 자바프로그램이 실행되는 기본 위치는
			// 해당 '프로젝트폴더'가 기본 위치가 된다.
				fr = new FileReader("src/kr/or/ddit/basic/T11BufferedIOTest.java");
				
				/*int data = 0;
				while((data = fr.read())!= -1) {
					System.out.print((char)data);
				}*/
				
				br = new BufferedReader(fr);
				
				// 한줄씩 읽을 수 있도록 해주는 readLine()을 이용한다.
				String temp =""; //한줄씩 읽기위해 잠시 넣어주기위함
				int cnt = 1;	//주소 찍어보기위함
				while((temp = br.readLine())!= null) {	//한줄씩 읽다가 읽을게 없을경우가 null인 조건
					System.out.printf("%4d : %s\n", cnt++, temp);
				}
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
//				fr.close();
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
