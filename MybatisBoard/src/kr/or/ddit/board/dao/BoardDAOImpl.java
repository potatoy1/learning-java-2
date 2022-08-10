package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.util.MyBatisUtil;

public class BoardDAOImpl implements IBoardDAO{
	private static IBoardDAO boardDao;
	
	private SqlSession sqlSession;
	
	private BoardDAOImpl() {
		sqlSession = MyBatisUtil.getInstance(true);
	}
	
	public static IBoardDAO getInstance() {
		if(boardDao == null) {
			boardDao = new BoardDAOImpl();
		}
		return boardDao;
	}
	
	@Override
	public int insertBoard(BoardVO bv) {
		
		int cnt = sqlSession.insert("board.insertBoard",bv);
		
		return cnt;
	}

	@Override
	public boolean checkBoard(String boardTitle) {
		boolean chk = false;
		
		int cnt = sqlSession.selectOne("board.checkBoard",boardTitle);
		
		if(cnt >0 ) {
			chk = true;
		}
		return chk;
	}

	@Override
	public int updateBoard(BoardVO bv) {
		int cnt = sqlSession.update("board.updateBoard",bv);
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		int cnt = sqlSession.delete("board.deleteBoard", boardNo);
		return cnt;
	}

	@Override
	public List<BoardVO> getAllBoardList() {
		List<BoardVO> boardList = sqlSession.selectList("board.boardAllList");
		return boardList;
	}

	@Override
	public List<BoardVO> searchBoardList(BoardVO bv) {
		List<BoardVO> boardList = sqlSession.selectList("board.searchBoardList",bv);
		return boardList;
	}


}
