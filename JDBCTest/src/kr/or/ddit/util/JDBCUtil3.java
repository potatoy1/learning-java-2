package kr.or.ddit.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * db.properties파일의 내용으로 DB정보를 설정하는 방법 (유지보수가 좋아짐)
 * 방법2) ResourceBundle 객체 이용하기 
 *
 */

public class JDBCUtil3 {
	
	static ResourceBundle bundle; // ResourceBundle 객체변수 선언
	
	static {	
		
		bundle = ResourceBundle.getBundle("db");
		
		
		try {
			Class.forName(bundle.getString("driver"));
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
			return DriverManager.getConnection(bundle.getString("url"),bundle.getString("username"),bundle.getString("password"));
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
