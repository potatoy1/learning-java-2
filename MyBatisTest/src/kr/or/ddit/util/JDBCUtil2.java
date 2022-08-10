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

/**
 * db.properties파일의 내용으로 DB정보를 설정하는 방법 (유지보수가 좋아짐)
 * 방법1) Properties 객체 이용하기 
 *
 */

public class JDBCUtil2 {
	
	static Properties prop; // Properties 객체변수 선언
	
	static {	
		
		prop = new Properties();
		
		
		try {
			prop.load(new FileInputStream("res/db.properties"));
			
			Class.forName(prop.getProperty("driver"));
			System.out.println("드라이버 로딩 완료!!");
			
		}catch(ClassNotFoundException ex) {
			System.out.println("드라이버 로딩 실패!!!");
		}catch(IOException ex) {
			System.out.println("해당 파일이 없거나 입출력 오류입니다.");
			ex.printStackTrace();
		}
	}
	
	/**
	 * 커넥션 객체 생성하기
	 * @return
	 */
	public static Connection getConnection() { //정상적일경우 connection을 리턴, 예외일경우  null을 리턴
		try {
			return DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("username"),prop.getProperty("password"));
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
//
//사용자가 뷰를 통해 요청함
//
//사용자가 요청한 원하는 기능을 서비스한테 처리해주는 컨트롤러
//
//처리 후 다시 뷰를 통해 사용자에게 보여짐
//
//모델->데이터 관련된 것