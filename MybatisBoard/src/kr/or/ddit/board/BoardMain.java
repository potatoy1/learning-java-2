package kr.or.ddit.board;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;


public class BoardMain {
	private Scanner scan = new Scanner(System.in); 
	private IBoardService boardService;
	
	public BoardMain() {
		boardService = new BoardServiceImpl();
	}


	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 새 글 작성");
		System.out.println("  2. 게시글 삭제");
		System.out.println("  3. 게시글 수정");
		System.out.println("  4. 전체 게시글 출력");
		System.out.println("  5. 게시글 검색.");
		System.out.println("  6. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	

	public void start(){
		int choice;
		do{
			displayMenu();
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  // 새 글 작성
					insertBoard();
					break;
				case 2 :  // 게시글 삭제 
					deleteBoard();
					break;
				case 3 :  // 게시글 수정
					updateBoard();
					break;
				case 4 :  // 전체 게시글 출력
					displayBoardAll();
					break;
				case 5 :  // 게시글 검색
					searchBoard();
					break;
				case 6 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=6);
	}
	
	
	private void searchBoard() {
	
		scan.nextLine(); // 입력버퍼 비우기
		System.out.println();
		System.out.println("검색 할 게시글 정보를 입력하세요");
		
		System.out.print("제  목>> ");
		String boardTitle =  scan.nextLine().trim();
		
		System.out.print("작성자>> ");
		String boardWriter =  scan.nextLine().trim();
		
		System.out.print("내  용>> ");
		String boardContent =  scan.nextLine().trim();
		
		BoardVO bv = new BoardVO();
		bv.setBoardTitle(boardTitle);
		bv.setBoardWriter(boardWriter);
		bv.setBoardContent(boardContent);
		
		List<BoardVO> boardList = boardService.searchBoardList(bv);
		
		System.out.println();
		System.out.println("---------------------------------------------------------------");
		System.out.println(" 번  호\t제  목\t작성자\t작성날짜\t\t내  용");
		System.out.println("---------------------------------------------------------------");
		
		if(boardList.size() == 0) {
			System.out.println("검색된 게시글정보가 없습니다.");
		}else {
			for(BoardVO mv2 : boardList) {
				System.out.println(mv2.getBoardNo() + "\t" + mv2.getBoardTitle() + "\t" + mv2.getBoardWriter() 
				+"\t " + mv2.getBoardDate() + "\t\t" + mv2.getBoardContent());
			}
		}
		System.out.println("---------------------------------------------------------------");
		System.out.println("검색 끝.");
	}

	
	private void displayBoardAll() {
		System.out.println();
		System.out.println("---------------------------------------------------------------");
		System.out.println(" 번  호\t제  목\t작성자\t작성날짜\t\t내  용");
		System.out.println("---------------------------------------------------------------");
		
		List<BoardVO> boardList = boardService.getAllBoardList();
		
		if(boardList.size() == 0) {
			System.out.println("출력할 게시글정보가 없습니다.");
		}else {
			for(BoardVO bv : boardList) {
				System.out.println(bv.getBoardNo() + "\t" + bv.getBoardTitle() + "\t" + bv.getBoardWriter() 
				+"\t " + bv.getBoardDate() + "\t\t" + bv.getBoardContent());
			}
		}
		System.out.println("---------------------------------------------------------------");
		System.out.println("출력 끝.");
	}

	
	private void deleteBoard() {
		System.out.println();
		System.out.println("삭제할 게시글 정보를 입력하세요.");
		System.out.print("게시글 번호 >> ");
		
		int boardNo = scan.nextInt();
		
		int cnt = boardService.removeBoard(boardNo);       
		
		if(cnt > 0) {
			System.out.println(boardNo + " 게시글 정보 삭제 완료!");
		}else {
			System.out.println(boardNo + " 게시글 정보 삭제 실패!");
		}
	}

	
	private void updateBoard() {
		boolean chk = false;
		
		String boardTitle ="";
		
		do {
			System.out.println();
			System.out.println("수정할 게시글 정보를 입력하세요");
			System.out.print("제  목>> ");
			
			boardTitle = scan.next();
			
			chk = checkBoard(boardTitle);
			
			if(chk == false) {
				System.out.println("제목이 " + boardTitle + "인 게시글은 이미 존재하지 않습니다.");
				System.out.println("다시 입력해 주세요.");
			}
		}while(chk == false);
		
		
		scan.nextLine();
		
		System.out.print("작성자>> ");
		String boardWriter = scan.nextLine();
		
		System.out.print("내  용>> ");
		String boardContent = scan.nextLine();
		
		BoardVO bv = new BoardVO();
		bv.setBoardTitle(boardTitle);
		bv.setBoardWriter(boardWriter);
		bv.setBoardContent(boardContent);
		
		int cnt = boardService.modifyBoard(bv);
		
		if(cnt > 0) {
			System.out.println(boardTitle + "게시글 정보 수정 완료!");
		}else {
			System.out.println(boardTitle + "게시글 정보 수정 실패!");
		}
	}


	private void insertBoard() {

		boolean chk = false;
		
		String boardTitle ="";
		
		do {
			System.out.println();
			System.out.println("작성할 새 글 정보를 입력하세요");
			System.out.print("제  목>> ");
			
			boardTitle = scan.next();
			
			chk = checkBoard(boardTitle);
			
			if(chk == true) {
				System.out.println("제목이 " + boardTitle + "인 게시글은 이미 존재합니다.");
				System.out.println("다시 입력해 주세요.");
			}
		}while(chk == true);
		
		
		System.out.print("작성자>> ");
		String boardWriter = scan.next();
		
		scan.nextLine(); 
		
		System.out.print("내  용>> ");
		String boardContent = scan.nextLine();
		
		BoardVO bv = new BoardVO();
		bv.setBoardTitle(boardTitle);
		bv.setBoardWriter(boardWriter);
		bv.setBoardContent(boardContent);
		
		
		int cnt = boardService.registerBoard(bv);
		
		if(cnt > 0) {
			System.out.println(boardTitle + " 새 글 작성 완료!");
		}else {
			System.out.println(boardTitle + " 새 글 작성 실패!!!");
		}
		
	}
	
	private boolean checkBoard(String boardTitle) {
		boolean chk = boardService.checkBoard(boardTitle);
		
		return chk;
	}
	public static void main(String[] args) {
		BoardMain boardObj = new BoardMain();
		boardObj.start();
	}
}
