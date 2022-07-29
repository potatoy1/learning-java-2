package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

//static String strRank = "";

public class HorseRacingProgram {
	public static void main(String[] args) {
		Horse[] horses = new Horse[] {
			new Horse("1번말"),
			new Horse("1번말"),
			new Horse("1번말"),
			new Horse("1번말"),
			new Horse("1번말"),
			new Horse("1번말"),
			new Horse("1번말"),
			new Horse("1번말"),
			new Horse("1번말"),
			new Horse("1번말"),
			new Horse("1번말"),
		}
//		List<String> list = new ArrayList<String>();
//		list.add("1번말");
//		list.add("2번말");
//		list.add("3번말");
//		list.add("4번말");
//		list.add("5번말");
//		list.add("6번말");
//		list.add("7번말");
//		list.add("8번말");
//		list.add("9번말");
//		list.add("10번말");
//		System.out.println(list);
	}
}


class Horse extends Thread implements Comparable<Horse>{
	private String name;
	private int rank;
	
	public Horse(String name) {
		this.name = name;
	}
	
	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public void run() {
		for(char ch ='A'; ch<='Z';ch++) {
			System.out.println(name + "의 출력문자: " + ch);
			
			try {
				Thread.sleep((int)(Math.random() + 301 + 200));
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		System.out.println(name + "출력 끝...");
		T11DisplayCharacterTest.strRank += name + " ";
	}

	@Override
	public int compareTo(int h) {
		return this.getRank().compareTo(h.getRank());
	}
}
