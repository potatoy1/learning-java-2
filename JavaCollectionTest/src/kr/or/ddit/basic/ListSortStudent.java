package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListSortStudent{
	public static void main(String[] args) {
		List<Student> studentList = new ArrayList<Student>();
		studentList.add(new Student("1", "홍길동", 90,90,80));
		studentList.add(new Student("5", "변학도", 80,90,90));
		studentList.add(new Student("9", "성춘향", 70,50,90));
		studentList.add(new Student("3", "이순신", 60,70,90));
		studentList.add(new Student("6", "강감찬", 80,80,70));
		studentList.add(new Student("2", "일지매", 90,40,50));
		
		Collections.sort(studentList);
		System.out.println("학번으로 오름차순 정렬:  ");
		for(Student student : studentList) {
			System.out.println(student);
		}
		
		Collections.sort(studentList, new SortDesc());
		
		System.out.println("총점의 내림차순으로 정렬(총점 같을 시 학번으로 내림차순): ");
		for(Student student : studentList) {
			System.out.println(student);
		}
		
	}
	
}

class SortDesc implements Comparator<Student>{
	
	@Override
	public int compare(Student student1, Student student2) {
		if(student1.getTotalScores() == student2.getTotalScores()) {
			return student1.getStudentNum().compareTo(student2.getStudentNum()) * -1;
		}else {
			return new Integer(student1.getTotalScores()).compareTo(student2.getTotalScores()) * -1;
		}
	}
}

class Student implements Comparable<Student>{
	private String studentNum;		
	private String name;
	private int korScores;
	private int engScores;
	private int mathScores;
	private int totalScores;		
	private int lank;
	
	public Student(String studentNum, String name, int korScores, int engScores, int mathScores) {
		super();
		this.studentNum = studentNum;
		this.name = name;
		this.korScores = korScores;
		this.engScores = engScores;
		this.mathScores = mathScores;
		this.totalScores = korScores + engScores + mathScores;
	}
	
	public String getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKorScores() {
		return korScores;
	}

	public void setKorScores(int korScores) {
		this.korScores = korScores;
	}

	public int getEngScores() {
		return engScores;
	}

	public void setEngScores(int engScores) {
		this.engScores = engScores;
	}

	public int getMathScores() {
		return mathScores;
	}

	public void setMathScores(int mathScores) {
		this.mathScores = mathScores;
	}

	public int getTotalScores() {
		return totalScores;
	}


	public void setTotalScores(int totalScores) {
		this.totalScores = totalScores;
	}


	public int getLank() {
		return lank;
	}


	public void setLank(int lank) {
		this.lank = lank;
	}

	@Override
	public String toString() {
		return "Student [studentNum=" + studentNum + ", name=" + name + ", korScores=" + korScores + ", engScores="
				+ engScores + ", mathScores=" + mathScores + ", totalScores=" + totalScores + "]";
	}

	@Override
	public int compareTo(Student student) {
		return this.getStudentNum().compareTo(student.getStudentNum());
	}
	
}
