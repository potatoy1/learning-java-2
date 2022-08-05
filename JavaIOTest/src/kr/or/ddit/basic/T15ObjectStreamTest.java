package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class T15ObjectStreamTest {
	public static void main(String[] args) {
		
		// Member 객체 생성하기
		Member mem1 = new Member("홍길동", 20, "대전");
		Member mem2 = new Member("일지매", 30, "경기");
		Member mem3 = new Member("이몽룡", 40, "강원");
		Member mem4 = new Member("성춘향", 50, "광주");
		
		ObjectOutputStream oos = null;
		
		try {
			// 객체를 파일에 저장하기
			
			// 출력용 스트림 객체 생성하기
			oos = new ObjectOutputStream(new FileOutputStream("d:/D_Other/memObj.bin"));
			
			//쓰기 작업
			oos.writeObject(mem1);	// 직렬화
			oos.writeObject(mem2);	// 직렬화
			oos.writeObject(mem3);	// 직렬화
			oos.writeObject(mem4);	// 직렬화
			
			System.out.println("쓰기 작업 완료...");
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				oos.close(); // 보조스트림 close() 시키면 기본스트림 먼저 close()되고 보조스트림이 close()되므로 보조스트림만 close()하면됨.
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("d:/D_Other/memObj.bin")));
			
			Object obj = null;
			
			while((obj = ois.readObject()) != null) { // readObject에서 내부적으로 역직렬화가 일어나서 obj에 리턴되는것임.
				// 파일의 마지막에 다다르면 EOF Exception 발생함.
				
				//읽어온 객체를 원래의 타입으로 변환 후 사용한다.
				Member mem = (Member) obj;
				System.out.println("이름 : " + mem.getName());
				System.out.println("나이 : " + mem.getAge());
				System.out.println("주소 : " + mem.getAddr());
				System.out.println("------------------------------");
			}
			
		}catch(IOException ex) {
			// ex.printStackTrace();
			// 더이상 읽어올 객체가 없으면 예외 발생함.(EOF Exception은 IOException의 하위이므로 IOException예외처리 해주면 두가지 다 예외처리가능)
			System.out.println("출력 작업 끝...");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				ois.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}

class Member implements Serializable {		// 데이터를 담기위한 class -> vo(value object)class
	// 자바는 Serializable 인터페이스를 구현한 클래스만 직렬화 할 수 있도록 제한하고 있음.
	// Serializeable 인터페이스-> 메소드가 없는 인터페이스
	/*
	 	transient -> 직렬화가 되지 않을 멤버변수에 지정한다.
	 				 (static 필드로 직렬화가 되지 않는다.)
	 				 직렬화가 되지 않는 멤버변수는 기본값으로 저장된다.
	 				 (참조형 변수: null, 숫자형 변수: 0)
	 				 주민번호나 password같은 정보를 전달할때는 transient를 이용해서 제외가능.
	 */
	transient private String name;
	private int age;
	private String addr;
	
	public Member(String name, int age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Override
	public String toString() {
		return "Member [name=" + name + ", age=" + age + ", addr=" + addr + "]";
	}
	
	
	
}
