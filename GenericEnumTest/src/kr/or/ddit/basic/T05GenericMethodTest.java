package kr.or.ddit.basic;

class Util2 {
	public static <T extends Number> int compare(T t1, T t2) {	//T에 Number와 Number를 extends한 타입은 다 가능.
		
		double v1 = t1.doubleValue();
		double v2 = t2.doubleValue();
		
		return Double.compare(v1, v2);
	}
}

public class T05GenericMethodTest {
	public static void main(String[] args) {
		
		int result1 =  Util2.compare(10, 20);
		System.out.println(result1);
		
		int result2 = Util2.<Number>compare(3.14, 3);
		System.out.println(result2);
		
		// Util2.compare("C", "JAVA");  타입을 Number로 제한해놔서 String은 못들어옴.
	}
}
