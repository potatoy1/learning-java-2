package kr.or.ddit.chapter02;

public class P01VariableExchangeExample {
	public static void main(String[] args) {
		int x = 3;
		int y = 5;
		
		int temp = x;
		x = y;
		y = temp;
		
		System.out.println("x: "+ x + ", y: " + y );
	}
}
