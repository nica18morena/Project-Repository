<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <jsp:useBean id="user" class="db.DBConnection" scope="session" />
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>Courses Tracking</title>
<link rel="stylesheet" type="text/css" href="clearFormatting.css">
<link rel="stylesheet" type="text/css" href="SMAstyler.css">
<title>Courses Tracking</title>
<script src="scripts.js"></script>
</head>
<body>
<div class="container">
<div class="header">
<h1>Student Major Assistant</h1></div>
<!--<div class="menuBar"><nav>
	<ul class="menuBar">
		<li class="menuBar"><a class="menuBar" href="Menu.jsp">Home</a></li>
		<li class="menuBar"><a class="menuBar" href="https://banweb.plu.edu/pls/pap/twbkwbis.P_GenMenu?name=homepage" target="_blank">Banner</a></li>
		<li class="menuBar"><a class="menuBar" href="https://banweb.plu.edu/pls/pap/hxskschd.P_PLUSchedule" target="_blank">Schedule of courses</a></li>		
		<li class="menuBar"><a class="menuBar" href="logout.jsp">Log out</a></li>	
	</ul>
</nav>
</div>-->
<% if(user.conn == null)
	user.openDB();
%>
	<table class="menuBar">
	<tr>
		<td class="menuBar"><a class="menuBar" href="Menu.jsp">Home</a></td>
		<td class="menuBar"><a class="menuBar" href="https://banweb.plu.edu/pls/pap/twbkwbis.P_GenMenu?name=homepage" target="_blank">Banner</a></td>
		<td class="menuBar"><a class="menuBar" href="https://banweb.plu.edu/pls/pap/hxskschd.P_PLUSchedule" target="_blank">Schedule of courses</a></td>		
		<td class="menuBar"><a class="menuBar" href="logout.jsp">Log out</a></td>	
	</tr>
	</table>
<div class="header2">
<h2>Courses Tracking</h2></div>
<fieldset>
<table id="tableViewCourses">
<tr><th>CRN Number</th><th>Subject</th><th>Course Number</th><th>Section Number</th><th>Instructor</th></tr>
<% String uname= (String)session.getAttribute("username"); 
ResultSet rs = null;	
if(uname != null){
	rs= user.viewAddCourse(uname); 
	String tr = "<tr>";
	String tr_alt="<tr class=\"alt\">";
	int i=0;
	while(rs.next()){
		if(i%2==0){
		out.println("<tr>");
		}
		else{
				out.println(tr_alt);
		}
			out.println("<td>" + rs.getString(1) + "</td>");
			out.println("<td>" + rs.getString(2) + "</td>");
			out.println("<td>" + rs.getString(3) + "</td>");
			out.println("<td>" + rs.getString(4) + "</td>");				
			out.println("<td>" + rs.getString(5) + "</td>");
		i++;
	}%>
</table></fieldset>
	<%}
	else { %>  <jsp:forward page="Menu.jsp"></jsp:forward> <%} %>
</div>
</body>
</html>