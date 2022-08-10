package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
	static {	//JDBCUtil 클래스가 vm에 클래스정보를 로딩하는데 그 시점에 static블럭이 한번만 실행된다.
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로딩 완료!!");
			
		}catch(ClassNotFoundException ex) {
			System.out.println("드라이버 로딩 실패!!!");
		}
	}
	
	/**
	 * 커넥션 객체 생성하기
	 * @return
	 */
	public static Connection getConnection() { //정상적일경우 connection을 리턴, 예외일경우  null을 리턴
		try {
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pc13", "java");
		}catch(SQLException ex){
			System.out.println("DB 연결실패!!!");
			ex.printStackTrace();
			return null;
		}
	}

	public static void close(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs) {
		if(rs != null)  try {rs.close(); }catch(SQLException ex) {}
		if(stmt != null)  try {stmt.close(); }catch(SQLException ex) {}
		if(pstmt != null)  try {pstmt.close();}catch(SQLException ex) {}
		if(conn != null) try {conn.close();}catch(SQLException ex) {}
		}
	}
