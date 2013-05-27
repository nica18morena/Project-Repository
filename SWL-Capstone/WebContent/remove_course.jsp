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
<title>Remove Course</title>
<script src="scripts.js"></script>
</head>
<body>
<div class="container">
<%if(user.conn == null)
	user.openDB(); %>
	
	<%String uname = (String)session.getAttribute("username");

	ResultSet rs = null;
	if(uname !=null){
	 rs= user.viewAddCourse(uname);%>
<div>
<div class="header">
<h1>Student Major Assistant</h1></div>
<!-- <div class="menuBar"><ul class="menuBar">	
	<li class="menuBar"><a class="menuBar" href="Menu.jsp">Home</a></li>
	<li class="menuBar"><a class="menuBar" href="https://banweb.plu.edu/pls/pap/twbkwbis.P_GenMenu?name=homepage" target="_blank">Banner</a></li>
	<li class="menuBar"><a class="menuBar" href="https://banweb.plu.edu/pls/pap/hxskschd.P_PLUSchedule" target="_blank">Schedule of courses</a></li>		
	<li class="menuBar"><a class="menuBar" href="logout.jsp">Log out</a></li>	
</ul>
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
<h2> Remove Course</h2></div>
<div class="header3">
<h3>Which courses would you like to remove?</h3></div>
	<fieldset>
	 <form action = "p_remove_course.jsp"  onsubmit="return confirmRemove()" method="post">
	 <div>
	<table id="tableViewCourses">
	<tr><th>CRN Number</th><th>Subject</th><th>Course Number</th><th>Section Number</th><th>Instructor</th></tr>
	 <% String tr = "<tr>";
		String tr_alt="<tr class=\"alt\">";
		int i=0;
	 	while(rs.next()){
		
	 	String crn = rs.getString(1);
	 	String subj = rs.getString(2);
	 	String course_num = rs.getString(3);
	 	String sec = rs.getString(4);
	 	String prof = rs.getString(5);
	  if(i%2==0){
			out.println("<tr>");
		}
		else{
			out.println(tr_alt);
		}%>
	 
		<td> 
		<input id= "to_delete" type="checkbox" name ="to_delete" value="<% out.print(crn); %>"><% out.println(crn +"</td><td>"+ subj +"</td><td>"+ course_num +"</td><td>"+ sec +"</td><td>"+ prof); %>
		</td></tr>	
	 <% i++;
	 }
 } %>
	 </table>
	 <input type="submit" value="Delete">
	 </div>
	 </form></fieldset>

</div></div>
</body>
</html>