<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="com.javaex.dao.GuestbookDao"%>
<%@page import="com.javaex.Vo.GuestbookVo"%>

<%
	int no = Integer.parseInt(request.getParameter("no"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="./delete.jsp?" method="get">
		<input type="hidden" name="no" value="<%= no %>">
 비밀번호 <input type="password" name="password" value=""> <button>확인</button>
 <br>
 <a href="http://localhost:8088/guestbook1/addList.jsp">리스트로 돌아가기</a>
 </form>
</body>
</html>