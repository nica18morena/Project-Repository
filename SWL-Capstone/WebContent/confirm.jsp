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
<title>Confirm</title>
<script src="scripts.js"></script>
</head>
<body>
<div class="container">
<h1>Profile</h1>

<%if(user.conn == null)
	user.openDB();%>
	
<%
	String uname = (String)session.getAttribute("username");
	
ResultSet rs = null;
	rs = user.view_profile(uname);
	 String email, fname, lname, name, pno, carrier, pw;
	while(rs.next()){
		email = rs.getString(1);
		fname= rs.getString(2);
		lname= rs.getString(3);
		name= rs.getString(4);
		pw= rs.getString(5);
		pno= rs.getString(6);
		carrier= rs.getString(7);%>
		
		<%
			if(carrier.equals("vtext.com"))
				carrier = "Verizon";
			else if(carrier.equals("txt.att.net"))
				carrier = "At&t";
			else if(carrier.equals("tmomail.net"))
				carrier = "T-mobile";
			else if(carrier.equals("myboostmobile.com"))
				carrier= "Boost";
			else if(carrier.equals("messaging.sprintpcs.com"))
				carrier = "Sprint";
			else if(carrier.equals("vmobl.com"))
				carrier = "Virgin";
		%>
	
		<fieldset>
		<legend>
		Personal Information
		</legend>
		<table>
		<% out.print("<tr><td>" + "First name:"+ "<td>"+ fname +"</td></td></tr>");
		out.print("<tr><td>" + "Last name:" + "<td>"+ lname + "</td></td></tr>");
		out.print("<tr><td>" + "Username:" + "<td>" + uname + "</td></td></tr>");
		out.print("<tr><td>" + "Phone number:" + "<td>" + pno + "</td></td></tr>");
		out.print("<tr><td>" + "Phone carrier:" + "<td>" + carrier + "</td></td></tr>");%>
		</table>
		<a href="p_edit_profile.jsp">Edit</a>
		</fieldset>
		<button class="buttons" style="position:relative; top:0; left: 1200px;" onclick="sendHome()">Ok</button>	
		
	<% } %>
	</div>
</body>
</html>