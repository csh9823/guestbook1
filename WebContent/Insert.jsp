<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="com.javaex.dao.GuestbookDao"%>
<%@page import="com.javaex.Vo.GuestbookVo"%>

<%
	//dao 메모리 올리기
	GuestbookDao guestbookDao = new GuestbookDao();

	//파라미터 값 가져오기
	String name = request.getParameter("name"); // wirte에서 name="name" 와 같은 이름이여야함
	String password = request.getParameter("password");
	String content = request.getParameter("content"); 
	
	//전송 값을 vo 객체로 만든다
	GuestbookVo guestbookvo = new GuestbookVo(name, password, content);
	
	//저장
	guestbookDao.GuestbookInsert(guestbookvo);
	
	//리다이렉트 리스트 페이지로 돌려줌
	response.sendRedirect("./addList.jsp");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>