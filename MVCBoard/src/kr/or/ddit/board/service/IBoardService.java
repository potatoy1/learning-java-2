package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardService {
		
		
		public int registerBoard(BoardVO bv);	
		
		

		public boolean checkBoard(String boardTitle);
		
		
		
		public int modifyBoard(BoardVO bv);
		
		
		
		public int removeBoard(int boardNo);
		
		
		
		public List<BoardVO> getAllBoardList();
		
		
		
		public List<BoardVO> searchBoardList(BoardVO bv);
	
}
