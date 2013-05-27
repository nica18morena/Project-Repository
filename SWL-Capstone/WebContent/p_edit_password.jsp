<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <jsp:useBean id="user" class="db.DBConnection" scope="session" />
<%@ page import="java.sql.*"%>
<!DOCTYPE>
<html>
<head>
<meta charset=UTF-8>
<title>Change password</title>
</head>
<body>
<% if(user.conn == null)
user.openDB(); %>

<% int success=0;
	ResultSet rs = null;
	String pw1, pw2, pw, name;
	pw1 = request.getParameter("oldpassword");
	pw2 = request.getParameter("newpassword");
	name = (String)session.getAttribute("username");%>
	
	<% rs = user.user(name);
	while(rs.next()){
		 pw = rs.getString("Password");
		if( pw1.equals(pw)){
		success = user.update_pw(pw2, name); 
		if(success < 0){%>
			<script>
			alert("Could not update the password, try again"); </script>
		<%}
		}
		else{ %>
			<script>
		alert("Incorrect password"); </script>
		<%}
	}%>
	<jsp:forward page="Menu.jsp"></jsp:forward>
</body>
</html>