<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <jsp:useBean id="user" class="db.DBConnection" scope="session" />
<%@ page import="java.sql.*"%>
<!DOCTYPE>
<html>
<head>
<meta charset=UTF-8>
<title>Delete</title>
</head>
<body>
<% if(user.conn == null)
	user.openDB();%>

	<% String usersname = (String)session.getAttribute("username");
		user.removestudent(usersname);
		session.removeAttribute("username"); %>
		<jsp:forward page="Home.jsp"></jsp:forward>
</body>
</html>