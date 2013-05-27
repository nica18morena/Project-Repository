<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean id="user" class="db.DBConnection" scope="session" />
<%@ page import="java.sql.*"%>
<!DOCTYPE>
<html>
<head>
<meta charset=UTF-8>
<link rel="stylesheet" type="text/css" href="clearFormatting.css">
<link rel="stylesheet" type="text/css" href="SMAstyler.css">
<title>Update Profile</title>
</head>
<body>
<% if(user.conn == null)
user.openDB(); %>

<% int success=0;
	String email, firstn, lastn, name, pno, carriers;
	firstn = request.getParameter("fname");
	lastn = request.getParameter("lname");
	email = request.getParameter("e-mail");
	pno = request.getParameter("phone");
	carriers = request.getParameter("pcarrier");
	name = (String)session.getAttribute("username");
	System.out.printf("%s, %s, %s, %s, %s, %s", firstn, lastn, email, pno, carriers, name);%>
	
	<%	
		if(carriers.equals("Verizon"))
			carriers ="vtext.com";
		else if(carriers.equals("At&t"))
			carriers = "txt.att.net";
		else if(carriers.equals("T-mobile"))
			carriers = "tmomail.net";
		else if(carriers.equals("Boost"))
			carriers = "myboostmobile.com";
		else if(carriers.equals("Sprint"))
			carriers = "messaging.sprintpcs.com";
		else if(carriers.equals("Virgin"))
			carriers = "vmobl.com"; %>
	
	<% success = user.user_update(email, firstn, lastn, pno, carriers, name);
	if(success < 0){%>
	<script>
		alert("Could not update information, try again"); </script>
		<% }%>
		<jsp:forward page="Menu.jsp"></jsp:forward>
		<br><a href = "Menu.jsp">Back to menu</a>	
		<a href= "logout.jsp">Log out</a>
</body>
</html>