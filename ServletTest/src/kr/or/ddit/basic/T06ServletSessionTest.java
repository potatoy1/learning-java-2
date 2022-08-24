package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class T06ServletSessionTest extends HttpServlet {
/*
 	세션(HttpSession)객체에 대하여...
 	
 	- 세션을 통해서 사용자(브라우저) 별로 구분하여 정보를 관리할 수 있다.(세션ID 이용)
 	- 쿠키를 사용 할 때보다 보안이 향상된다. (정보가 서버에 저장되기 때문에...)
 	- (response해도) 세션이 유지될 때까지 살아있음.
 	- 사용자별 세션객체를 하나씩 뽑아내므로 로그인기능에 사용하기 좋음.(세션객체는 server에 저장됨)
 	
 	- 세션객체를 가져오는 방법
 	  HttpSession session = request.getSession(boolean값);
 	  boolean값 : true인 경우 => 세션객체가 존재하지 않으면 새로 생성한다. 
 	  			  false인 경우 => 세션객체가 존재하지 않으면 null을 리턴한다.
 	  			  
 	- 세션 삭제 처리 방법
 	1. invalidate() 메서드 호출
 	2. setMaxInactiveInterval(int interval) 호출
 	   => 일정시간(초)동안 요청이 없으면 세션객체 삭제됨.
 	3. web.xml에 <session-config> 설정하기(분단위)
 */

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 세션을 가져오는데 없으면 새로 생성한다.
		HttpSession session = req.getSession();
		
		// 생성시간 가져오기 
		Date createTime = new Date(session.getCreationTime());
		
		// 마지막 접근시간 가져오기
		Date lastAccessTime = new Date(session.getLastAccessedTime());
		
		String title = "재방문을 환영합니다.";
		
		int visitCnt = 0; // 방문횟수
		String userId = "sem"; // 사용자ID
		
		if(session.isNew()) {	// 세션객체가 새로 만들어진 경우...
			title = "처음 방문을 환영합니다.";
			session.setAttribute("userId", userId);
		}else {
			visitCnt = (Integer) session.getAttribute("visitCnt");
			visitCnt++;
			userId = (String) session.getAttribute("userId");
		}	
		
		session.setAttribute("visitCnt", visitCnt);
		
//		session.invalidate(); // 세션객체 삭제

		session.setMaxInactiveInterval(10); // 
		
		// 응답헤더에 인코딩 및 Content-Type 설정
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		out.println("<!DOCTYPE html><head><title>" + title + "</title></head>" + "<body><h1 align=\"center\">" + title +"</h1>"
				 	+ "<h2 align=\"center\">세션 정보</h2>"
					+ "<table border=\"1\" align=\"center\">"
				 	+ "<tr bgcolor=\"orange\"><th>구분</th><th>값</th></tr>"
				 	+ "<tr><td>세션ID</td><td>"+ session.getId() + "</td></tr>"
				 	+ "<tr><td>생성시간ID</td><td>"+ createTime + "</td></tr>"
				 	+ "<tr><td>마지막접근시간</td><td>"+ lastAccessTime + "</td></tr>"
				 	+ "<tr><td>UserID</td><td>"+ userId + "</td></tr>"
				 	+ "<tr><td>방문횟수</td><td>"+ visitCnt + "</td></tr>"
				 	+ "</body></html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
