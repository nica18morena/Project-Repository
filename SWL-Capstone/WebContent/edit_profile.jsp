<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <jsp:useBean id="user" class="db.DBConnection" scope="session" />
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<link rel="stylesheet" type="text/css" href="clearFormatting.css">
<link rel="stylesheet" type="text/css" href="SMAstyler.css">
<title>Edit Profile</title>
<script src="scripts.js"></script>
</head>
<body>
<% if(user.conn == null)
user.openDB(); %>
<div class= "container">
<div class="header">
<h1>Student Major Assistant</h1></div>
<!-- <div class="menuBar"><nav>
	<ul class="menuBar">
		<li class="menuBar"><a class="menuBar" href="Menu.jsp">Home</a></li>
		<li class="menuBar"><a class="menuBar" href="https://banweb.plu.edu/pls/pap/twbkwbis.P_GenMenu?name=homepage" target="_blank">Banner</a></li>
		<li class="menuBar"><a class="menuBar" href="https://banweb.plu.edu/pls/pap/hxskschd.P_PLUSchedule" target="_blank">Schedule of courses</a></li>		
		<li class="menuBar"><a class="menuBar" href="logout.jsp">Log out</a></li>	
	</ul>
</nav>
</div>-->
	<table class="menuBar">
	<tr>
		<td class="menuBar"><a class="menuBar" href="Menu.jsp">Home</a></td>
		<td class="menuBar"><a class="menuBar" href="https://banweb.plu.edu/pls/pap/twbkwbis.P_GenMenu?name=homepage" target="_blank">Banner</a></td>
		<td class="menuBar"><a class="menuBar" href="https://banweb.plu.edu/pls/pap/hxskschd.P_PLUSchedule" target="_blank">Schedule of courses</a></td>		
		<td class="menuBar"><a class="menuBar" href="logout.jsp">Log out</a></td>	
	</tr>
	</table>
<div class="header2">
<h2>Profile</h2></div>
	
<% String uname = (String)session.getAttribute("username");
if(uname != null){
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
		out.print("<tr><td>" + "Phone carrier:" + "<td>" + carrier + "</td></td></tr>");
			}
		}%>
		</table>
		
		<div>
		<button class="buttons" onclick="editProfile()">Edit</button>
		<button class="buttons" onclick="confirmDelete()">Delete</button>
		</div>
		
		</fieldset>
		
		<form action = "p_edit_password.jsp" method="post">
		<fieldset>
		<legend>
		Change Password
		</legend>
		<table>
		<tr><td><input id="oldPw" type= "password" name= "oldpassword" size ="15" maxlength= "10" placeholder="Old Password"></td></tr>
		<tr><td><input id="newPw" type= "password" name= "newpassword" size ="15" maxlength= "10" placeholder="New Password"></td></tr>
		</table>
		</fieldset>
		<input type="submit" value="Submit">
		</form>
</div>
</body>
</html>