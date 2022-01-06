<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="java.util.List"%>
<%@page import="com.javaex.dao.GuestbookDao"%>

<%
	//dao 메모리 올리기
	GuestbookDao guestbookDao = new GuestbookDao();
	//파라미터 값 가져오기
	int no = Integer.parseInt(request.getParameter("no"));
	String password = request.getParameter("password");
	
	//삭제
	guestbookDao.GuestbookDelete(no,password);
	
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