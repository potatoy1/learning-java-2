package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

public class HorseRacingProgram {
	static String strRank = "";
	
	public static void main(String[] args) {
		Horse[] horses = new Horse[] {
			new Horse("1번말"),
			new Horse("2번말"),
			new Horse("3번말"),
			new Horse("4번말"),
			new Horse("5번말"),
			new Horse("6번말"),
			new Horse("7번말"),
			new Horse("8번말"),
			new Horse("9번말"),
			new Horse("10번말")
		};
	
		for(Horse h : horses) {
			h.start();
		}
		for(Horse h : horses) {
			try {
				h.join();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("경기 끝");
		System.out.println("---------------------");
		System.out.println();
		System.out.println("경기 결과");
		System.out.println("순위 : " + strRank);
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
//		for(int i=1; i<=5; i++) {
		
//			System.out.println("-");
//		System.out.print(">");
		{
			try {
				Thread.sleep((int)(Math.random() +100));
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		System.out.println(name + "");
		HorseRacingProgram.strRank += name + " ";
	}
	
	@Override
	public int compareTo(Horse o) {
		return Integer.compare(rank,o.getRank());
	}
}

