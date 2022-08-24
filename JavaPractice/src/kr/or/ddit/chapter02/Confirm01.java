package kr.or.ddit.chapter02;

import java.util.Scanner;

public class Confirm01 {
/*
 * 실수 자료형을 이용하여 직사각형의 둘레와 면적을 구하는 프로그램을 만들어보자. 
 * 실수형 변수 width(가로), height(세로), area(넓이), perimeter(둘레)를 선언하고, 
 * Scanner 클래스를 이용하여 사용자로부터 직사각형의 가로, 세로 길이를 입력받아 다음과 같이 넓이와 둘레를 출력한다.
 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("직사각형의 가로 길이와 세로 길이를 입력하세요.");
		System.out.print("가로의 길이: ");
		double width = scanner.nextDouble();
		System.out.print("세로의 길이: ");
		double height = scanner.nextDouble();
		double area = width * height;
		double perimeter = 2*width + 2*height;

		System.out.println("직사각형 넒이: " + area);
		System.out.println("직사각형 둘레: "+ perimeter);
		
		scanner.close();
	}
}
