<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   List<MemberVO> memList = (List<MemberVO>)request.getAttribute("memList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록</title>
</head>
<body>
<table border="1"	>
	<tr>
		<td>ID</td>
		<td>이름</td>
		<td>전화번호</td>
		<td>주소</td>
	</tr> 
<%
 int memSize = memList.size();
 if(memSize > 0) {
	 for(int i = 0; i < memSize; i++){
%>	
	<tr>
		<td><%= memList.get(i).getMemId() %></td>
		<td><%= memList.get(i).getMemName() %></td>
		<td><%= memList.get(i).getMemTel() %></td>
		<td><%= memList.get(i).getMemAddr() %></td>
	</tr>
<%
	 }
 }else{
%>
	<tr>
		<td colspan="4">회원정보가 없습니다.</td>
		
	</tr>

<%
 }
%>	 
</table>
</body>
</html>