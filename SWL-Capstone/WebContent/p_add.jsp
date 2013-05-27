<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean id="user" class="db.DBConnection" scope="session" />
<%@ page import="java.sql.*"%>
<!DOCTYPE>
<html>
<head>
<meta charset=UTF-8>
<title>Process add</title>
</head>
<body>
<%if(user.conn == null)
	user.openDB();%>
	
	<%
	int rs;
	String crn, name, pref;
	crn = request.getParameter("crn_no");
	pref = request.getParameter("notify");
	name = (String)session.getAttribute("username");
	rs = user.addCourse(name, crn, pref); 
	%>
	
	<jsp:forward page="Menu.jsp"></jsp:forward>
</body>
</html>