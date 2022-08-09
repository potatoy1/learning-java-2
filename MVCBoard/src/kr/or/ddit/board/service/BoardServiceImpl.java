package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.BoardDAOImpl;
import kr.or.ddit.board.dao.IBoardDAO;
import kr.or.ddit.board.vo.BoardVO;

public class BoardServiceImpl implements IBoardService{
	private IBoardDAO boardDao;
	
	public BoardServiceImpl() {
		boardDao = new BoardDAOImpl();
	}

	@Override
	public int registerBoard(BoardVO bv) {
		int cnt = boardDao.insertBoard(bv);
		return cnt;
	}

	@Override
	public boolean checkBoard(String boardTitle) {
		boolean chk = boardDao.checkBoard(boardTitle);
		return chk;
	}

	@Override
	public int modifyBoard(BoardVO bv) {
		int cnt = boardDao.updateBoard(bv);
		return cnt;
	}

	@Override
	public int removeBoard(int boardNo) {
		int cnt = boardDao.deleteBoard(boardNo);
		return cnt;
	}

	@Override
	public List<BoardVO> getAllBoardList() {
		List<BoardVO> boardList = boardDao.getAllBoardList();
		return boardList;
	}

	@Override
	public List<BoardVO> searchBoardList(BoardVO bv) {
		List<BoardVO> boardList = boardDao.searchBoardList(bv);
		return boardList;
	}

}
