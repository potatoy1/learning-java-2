package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.util.JDBCUtil3;

public class BoardDAOImplForJDBC implements IBoardDAO{
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	@Override
	public int insertBoard(BoardVO bv) {
		int cnt = 0;

		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = "INSERT INTO jdbc_board ( board_no, board_title , board_writer , board_date , board_content )" + "VALUES(BOARD_SEQ.nextVal, ?, ?, sysdate, ?)";
					
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bv.getBoardTitle());
			pstmt.setString(2, bv.getBoardWriter());
			pstmt.setString(3, bv.getBoardContent());
			
			cnt = pstmt.executeUpdate();
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("새 글 등록 과정중 예외발생!", ex);	
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public boolean checkBoard(String boardTitle) {
		boolean chk = false;
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = "select count(*) as cnt from jdbc_board " + " where board_title = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardTitle);
			
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
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return chk;
	}

	@Override
	public int updateBoard(BoardVO bv) {
		int cnt = 0;
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql ="UPDATE jdbc_board " 
					+ " SET  board_writer = ?" 
					+ "    , board_content  = ? " 
					+ " WHERE board_title   = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bv.getBoardWriter());
			pstmt.setString(2, bv.getBoardContent());
			pstmt.setString(3, bv.getBoardTitle());
			
			cnt = pstmt.executeUpdate();
			
		}catch(SQLException ex) {
			ex.printStackTrace();throw new RuntimeException("게시글 수정 과정중 예외발생!", ex);
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		int cnt = 0;
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = "delete from jdbc_board where board_no =? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			cnt = pstmt.executeUpdate();
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("게시글 삭제 과정중 예외발생!", ex);
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public List<BoardVO> getAllBoardList() {	
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = "select * from jdbc_board";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);		
			
			while(rs.next()) {
				BoardVO bv = new BoardVO();
				bv.setBoardNo(rs.getInt("board_no"));
				bv.setBoardTitle(rs.getString("board_title"));
				bv.setBoardWriter(rs.getString("board_writer"));
				bv.setBoardDate(rs.getDate("board_date"));
				bv.setBoardContent(rs.getString("board_content"));
				
				boardList.add(bv);
			}

		}catch(SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("전체 게시글 중 예외발생!", ex);
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return boardList;
	}

	@Override
	public List<BoardVO> searchBoardList(BoardVO bv) {
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = "select * from jdbc_board where 1=1 ";	// 동적쿼리(사용자가 조회하는것에 따라 쿼리문이 달라짐)
			
			if(bv.getBoardNo() !=0) {
				sql += " and board_no = ? ";
			}
			if(bv.getBoardTitle() !=null && !bv.getBoardTitle().equals("")) {
				sql += " and board_title = ? ";
			}
			if(bv.getBoardWriter() !=null && !bv.getBoardWriter().equals("")) {
				sql += " and board_writer = ? ";
			}
			if(bv.getBoardDate() !=null && !bv.getBoardDate().equals("")) {
				sql += " and board_date = ? ";
			}
			if(bv.getBoardContent() !=null && !bv.getBoardContent().equals("")) {
				sql += " and board_content like '%' || ?  || '%' ";
			}
			
			pstmt = conn.prepareStatement(sql);
			
			int index = 1;
			
			if(bv.getBoardNo() !=0) {
				pstmt.setInt(index++, bv.getBoardNo());
			}
			if(bv.getBoardTitle() !=null && !bv.getBoardTitle().equals("")) {
				pstmt.setString(index++, bv.getBoardTitle());
			}
			if(bv.getBoardWriter() !=null && !bv.getBoardWriter().equals("")) {
				pstmt.setString(index++, bv.getBoardWriter());
			}
			if(bv.getBoardDate() !=null && !bv.getBoardDate().equals("")) {
				pstmt.setDate(index++, bv.getBoardDate());
			}
			if(bv.getBoardContent() !=null && !bv.getBoardContent().equals("")) {
				pstmt.setString(index++, bv.getBoardContent());
			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO bv2 = new BoardVO();
				bv2.setBoardNo(rs.getInt("board_no"));
				bv2.setBoardTitle(rs.getString("board_title"));
				bv2.setBoardWriter(rs.getString("board_writer"));
				bv2.setBoardDate(rs.getDate("board_date"));
				bv2.setBoardContent(rs.getString("board_content"));
				
				boardList.add(bv2);
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return boardList;
	}

}
