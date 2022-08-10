package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardDAO {
	

	public int insertBoard(BoardVO bv);	
	
	
	
	public boolean checkBoard(String boardTitle);
	

	
	public int updateBoard(BoardVO bv);
	
	
	
	public int deleteBoard(int boardNo);
	

	
	public List<BoardVO> getAllBoardList();

	
	
	public List<BoardVO> searchBoardList(BoardVO bv);

}
