package kr.or.ddit.basic;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.JDBCUtil;

public class HotelJDBC {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in); 
	

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

	private void roomState() {
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "select * from HOTEL_MNG";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int roomNum = rs.getInt("room_num");
				String guestName = rs.getString("guest_name");

				
				System.out.println("방번호: " + roomNum + ", 투숙객: " + guestName);
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
	}

	private void checkOut() {
		System.out.println();
		System.out.println("어느방을 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		
		int roomNum = scan.nextInt();
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "delete from HOTEL_MNG where room_num =? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomNum);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("체크아웃되었습니다.");
			}else {
				System.out.println(roomNum + "방에는 체크인한 사람이 없습니다. ");
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
	}

	
	private void checkIn() {

		boolean chk = false;
		
		int roomNum = 0;
		
		do {
			System.out.println("어느방에 체크인 하시겠습니까?");
			System.out.print("방번호 입력 => ");
			roomNum = scan.nextInt();
			
			chk = checkGuest(roomNum);
			
			if(chk == true) {
				System.out.println(roomNum + "방에는 이미 사람이 있습니다.");
				System.out.println("다시 입력해 주세요.\n");
			}
		}while(chk == true);
		
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 => ");
		String guestName = scan.next();
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "INSERT INTO HOTEL_MNG ( room_num, guest_name)" + "VALUES(?, ?)";
					
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomNum);
			pstmt.setString(2, guestName);
		
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("체크인 되었습니다.");
			}else {
				System.out.println("체크인 되지 않았습니다. 다시 확인해주세요.");
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
	}
	
	
	private boolean checkGuest(int roomNum) {
		boolean chk = false;
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "select count(*) as cnt from HOTEL_MNG " + " where room_num = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomNum);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			if(cnt > 0) {
				chk = true;
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return chk;
	}

	public static void main(String[] args) {
		HotelJDBC hotelObj = new HotelJDBC();
		hotelObj.hotelOpenStart();
	}

}






