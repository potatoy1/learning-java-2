package kr.or.ddit.basic;

class Util {
/*
	 제너릭 메서드<T, R> method(T t)
	 
	 파라미터 타입과 리턴타입으로 타입파라미터(타입글자)를 가지는 메서드
	 
	 선언방법 : 리턴타입 앞에 <> 기호를 추가하고 타입글자를 기술 후 사용함.
*/
	public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2){			// 제너릭이므로 k,v에 모든 타입이 올 수 있음. 
		boolean keyCompare = p1.getKey().equals(p2.getKey());					// 타입에 제한 걸기-> 제한된 타입 파라미터
		boolean valueCompare = p1.getValue().equals(p2.getValue());
		
		return keyCompare && valueCompare;
	}
}

/**
 * 멀티타입<K, V>을 가지는 제너릭 클래스
 * @author PC-16
 *
 * @param <K>
 * @param <V>
 */
class Pair<K, V>{
	private K key;
	private V value;
	
	public Pair(K key, V value) {
		super();
		this.key = key;
		this.value = value;
	}
	public K getKey() {
		return key;
	}
	public void setKey(K key) {
		this.key = key;
	}
	public V getValue() {
		return value;
	}
	public void setValue(V value) {
		this.value = value;
	}
	
	public <K, V>void displayAll(K key, V val) {		//k,v는 타입글자
		System.out.println(key + ":" + val );
	}
}

public class T04GenericMethodTest {
	public static void main(String[] args) {
	
		Pair<Integer, String> p1 = new Pair<Integer, String>(1, "홍길동");
		Pair<Integer, String> p2 = new Pair<Integer, String>(1, "홍길동");
		
		boolean result1 = Util.<Integer, String>compare(p1, p2);
		
		if(result1) {
			System.out.println("논리(의미)적으로 동일한 객체임.");
		}else {
			System.out.println("논리(의미)적으로 동일한 객체아님.");
		}
		
		Pair<String, String> p3 = new Pair<String, String>("001", "홍길동");
		Pair<String, String> p4 = new Pair<String, String>("001", "홍길동");
		
		boolean result2 = Util.compare(p3, p4);	//result1처럼 제너릭타입 안붙이고 Util.compare로 해도 가능.
		
		if(result2) {
			System.out.println("논리(의미)적으로 동일한 객체임.");
		}else {
			System.out.println("논리(의미)적으로 동일한 객체아님.");
		}
		
		p1.displayAll("키", 180); 			// 타입체크-> p1은 이미 타입이 정해져있지만 제너릭 메소드로 인해 가능함.
											// p1.<String, Integer>.displayAll 타입이 생략되어있음.
	}
}
