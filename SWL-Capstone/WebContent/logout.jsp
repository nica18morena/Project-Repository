<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <jsp:useBean id="user" class="db.DBConnection" scope="session" />
<%@ page import="java.sql.*"%>
<!DOCTYPE>
<html>
<head>
<meta charset=UTF-8>
<title>logout</title>
</head>
<body>
<%if(user.conn == null)
	user.openDB();%>
	
	<% session.removeAttribute("username");
	user.closeDB(); %>
	
	<jsp:forward page="Home.jsp"></jsp:forward>
</body>
</html>