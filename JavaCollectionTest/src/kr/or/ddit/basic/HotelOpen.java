package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class HotelOpen {
	private Scanner scan;
	private Map<Integer, Hotel> hotelMap;
	 public HotelOpen() {
		 scan = new Scanner(System.in);
		 hotelMap = new HashMap<Integer, Hotel>();
	 }
	 public void displayMenu() {
		 System.out.println();
		 System.out.println("메뉴를 선택하세요.");
		 System.out.println("1.체크인");
		 System.out.println("2.체크아웃");
		 System.out.println("3.객실상태");
		 System.out.println("4.업무종료");
		 System.out.print("메뉴선택 >>");
	 }
	 public void hotelOpenStart() {
		 System.out.println("*********************");
		 System.out.println("호텔 문을 열었습니다.");
		 System.out.println("*********************");
		 
		 while(true) {
			 System.out.println("**************************************");
			 System.out.println("1.체크인2.체크아웃3.객실상태4.업무종료");
			 System.out.println("**************************************");
			 
			 displayMenu();
			 int menuNum = scan.nextInt();
			 
		switch(menuNum) {
			case 1 : checkIn();
				break;
			case 2 : checkOut();
				break;
			case 3 : roomState();
				break;
			case 4 : 
				System.out.println("**********************");
				System.out.println("호텔 문을 닫았습니다.");
				System.out.println("**********************");
				return;
			}
		 }
	 }
	private void displayAll() {
		
	}
	private void roomState() {
		System.out.println();
		Set<Integer> keySet = hotelMap.keySet();
		if(keySet.size() == 0) {
			System.out.println("체크인 된 방이 없습니다.");
		}else {
			Iterator<Integer> it = keySet.iterator();
			int cnt = 0;
			while(it.hasNext()) {
				cnt++;
				int roomNum = it.next();
				Hotel h = hotelMap.get(roomNum);
				System.out.println("방번호 :"+ h.getRoomNum() +", " + "투숙객: "+ h.getName());
			}
		}
	}
	
	private void checkOut() {
		System.out.println();
		System.out.println("어느방을 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		int roomNum = scan.nextInt();
		
		if(hotelMap.remove(roomNum) == null) {
			System.out.println(roomNum + "방에는 체크인한 사람이 없습니다. ");
		}else {
			System.out.println("체크아웃되었습니다.");
		}
	}
	private void checkIn() {
		System.out.println();
		System.out.println("어느방에 체크인 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		int roomNum = scan.nextInt();
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 => ");
		String name = scan.next();
		if(hotelMap.get(roomNum) != null) {
			System.out.println(roomNum + "방에는 이미 사람이 있습니다.");
			return;
		}
		
		hotelMap.put(roomNum, new Hotel(roomNum, name));
		System.out.println("체크인 되었습니다.");
		
	}
	public static void main(String[] args) {
		new HotelOpen().hotelOpenStart();
	}
}
class Hotel{
	private int roomNum;
	private String name;
	public Hotel(int roomNum, String name) {
		super();
		this.roomNum = roomNum;
		this.name = name;
	}
	public int getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Hotel [roomNum=" + roomNum + ", name=" + name + "]";
	}
	
		
}
