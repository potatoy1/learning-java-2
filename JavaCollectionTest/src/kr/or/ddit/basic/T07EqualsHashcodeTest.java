package kr.or.ddit.basic;

public class T07EqualsHashcodeTest {
/*
  해시함수(hash function)는 임의의 길이의 데이터를 고정된 길이의 데이터로 매핑하는 함수이다.
  해시함수에 의해 얻어지는 값은 해시값, 해시코드, 해시체크섬 또는 간단하게 해시라고 한다.
  HashSet, HashMap, HashTable과 같은 객체들을 사용할 경우,
  객체가 서로 같은지를 비교하기 위해 equals()메서드와 hashCode()메서드를 호출한다.
  그래서 객체가 서로 같은지 여부를 결정하려면 두 메서드를 재정의 해야 한다.
  객체가 같은지 여부는 데이터를 추가할 때 검사한다.
  
  -equals()메서드와 hashCode()메서드에 관련된 규칙
  1. 두 객체가 같으면 반드시 같은 hashCode를 가져야 한다.
  2. 두 객체가 같으면 equals()호출했을 때 true를 반환해야 한다.
   즉, 객체a, b가 같다면 a.equals(b)와 b.equals(a) 둘다 true여야 한다.
  3. 두 객체의 hashCode가 같다고 해서 두 객체가 반드시 같은 객체는 아니다.
  하지만 두 객체가 같으면 반드시 hashCode가 같아야 한다. 
  4. equals()메서드를 override하면 반드시 hashCode()도 override해야 한다.
  5. hashCode()는 기본적으로 Heap메모리에 있는 각 객체에 대한 메모리 주소 매핑정보를 기반으로 한 정수값을 반환한다.
  그러므로, 클래스에서 hashCode()메서드를 override하지 않으면 절대로 두 객체가 같은 것으로 간주될 수 없다.
 */
	public static void main(String[] args) {
		String str = "홍길동";
		System.out.println(str.hashCode()); 	// 같은 값이면 같은 해시코드 계속 출력.
		System.out.println(str.contentEquals(str));
	}
}
