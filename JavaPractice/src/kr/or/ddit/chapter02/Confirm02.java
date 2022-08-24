package kr.or.ddit.chapter02;

import java.util.Scanner;

public class Confirm02 {
/* 
 * 원기둥 밑면의 반지름과 높이를 입력 받아서 밑면의 넓이를 계산하는 프로그램을 작성하여 보자. 
 * 원주율은 Math.PI를 사용한다.
 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("원기둥의 밑면의 반지름과 높이를 입력하세요.");
		System.out.print("반지름: ");
		double radius = scanner.nextDouble();
		System.out.print("높이: ");
		double height = scanner.nextDouble();
		
		double area = Math.PI*radius*radius;
		System.out.println("부피: "+ area);
	}
}
