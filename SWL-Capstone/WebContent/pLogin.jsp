<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="user" class="db.DBConnection" scope="session" />
<%@ page import="java.sql.*"%>
<!DOCTYPE>
<html>
<head>
<meta charset=UTF-8>
<title>pLogin</title>
</head>
<body>
	<script src="scripts.js"></script>

	<% String uname = request.getParameter("name_field");
	String pw = request.getParameter("password_field");
	
	if(uname == null ||pw == null || uname =="" || pw == ""){ %>
	<jsp:forward page="Home.jsp"></jsp:forward>
	<%}

	else if(user.conn == null)
			user.openDB(); %>

	<% ResultSet rs = null;
	String name, p;
	rs = user.user(uname);
	
			while(rs.next()){
			 p = rs.getString("Password");
			
			 if( p.equals(pw)){ 
			session.setAttribute("username",uname); 
		
			%>
	<jsp:forward page="Menu.jsp"></jsp:forward>
	<% } 
			}%>

	<jsp:forward page="login.jsp"></jsp:forward>
</body>
</html>