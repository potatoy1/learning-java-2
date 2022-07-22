package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Lotto {
	public static void main(String[] args) {
		
		while(true){
			System.out.println("===========================");
			System.out.println("Lotto 프로그램");
			System.out.println("---------------");
			System.out.println("1. Lotto 구입");
			System.out.println("2. 프로그램 종료");
			System.out.println("===========================");
			System.out.print("메뉴선택: ");
			Scanner scan = new Scanner(System.in);
			int menuNum = scan.nextInt(); 
			
			switch(menuNum){
				case 1 : LottoBuy();
					break;
				case 2 : 
					System.out.println("감사합니다. ");
					return;
			} 
		} 
	}
	public static void LottoBuy() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Lotto 구입 시작");
		System.out.println("(1000에 로또번호 하나입니다.)");
		System.out.print("금액 입력 : ");
		int price = scan.nextInt();
		for(int i = 1; i<= price/1000; i++) {
		Set<Integer> intRnd = new HashSet<Integer>();
		while(intRnd.size() < 6) {
			int num = (int) (Math.random() * 45 + 1);
			intRnd.add(num);
		}
		System.out.println("행운의 로또번호는 아래와 같습니다.");
		System.out.println("로또번호"+i+" : " + intRnd);
		System.out.println("받은 금액은 "+ price +"원 이고 거스름돈은 " + price%1000 +"원 입니다.");
		}
	}
}