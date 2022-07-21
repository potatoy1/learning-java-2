package kr.or.ddit.basic;

public class Student {
	private String studentNum;		//comparable
	private String name;
	private int koreanScores;
	private int englishScores;
	private int mathScores;
	private int totalScores;			//compareTo
	private int lank;
	
	public Student(String studentNum, String name, int koreanScores, int englishScores, int mathScores) {
		super();
		this.studentNum = studentNum;
		this.name = name;
		this.koreanScores = koreanScores;
		this.englishScores = englishScores;
		this.mathScores = mathScores;
	}
	
	
}
