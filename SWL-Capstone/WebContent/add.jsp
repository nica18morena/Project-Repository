<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="user" class="db.DBConnection" scope="session" />
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE>
<html>
<head>
<meta charset=UTF-8>
<link rel="stylesheet" type="text/css" href="clearFormatting.css">
<link rel="stylesheet" type="text/css" href="SMAstyler.css">
<title>Add Courses</title>
<script src="scripts.js"></script>
</head>
<body>
<div class="container">
<div class="header">
<h1>Student Major Assistant</h1></div>

	<table class="menuBar">
	<tr>
		<td class="menuBar"><a class="menuBar" href="Menu.jsp">Home</a></td>
		<td class="menuBar"><a class="menuBar" href="https://banweb.plu.edu/pls/pap/twbkwbis.P_GenMenu?name=homepage" target="_blank">Banner</a></td>
		<td class="menuBar"><a class="menuBar" href="https://banweb.plu.edu/pls/pap/hxskschd.P_PLUSchedule" target="_blank">Schedule of courses</a></td>		
		<td class="menuBar"><a class="menuBar" href="logout.jsp">Log out</a></td>	
	</tr>
	</table>


<div class="header2">
<h2>Add Course</h2></div>

	<form id="addForm" onsubmit="return validateAdd()" action="p_add.jsp" method="post">
			<%
				if (user.conn == null)
					user.openDB();
			%><%
				Set<String> subj, course, section, crn;
				subj = new TreeSet<String>();
				course = new TreeSet<String>();
				section = new TreeSet<String>();
				crn = new TreeSet<String>();

				ResultSet rs = null;
				rs = user.addCoursesMenu();
			%>
			<div class="tophalfform" >
				<br><label for="courses"> Subject </label>
				<br><input type="text" name="courses" size ="20" maxlength= "4"list="course_list" autofocus="autofocus"> 
				<datalist id="course_list">
					<% while (rs.next()) {
								subj.add(rs.getString(1));
								course.add(rs.getString(2));
								section.add(rs.getString(3));
								crn.add(rs.getString(4));
							} %>
						<%
							Iterator<String> itr = subj.iterator();
							while (itr.hasNext()) {
								out.println("<option value =" + itr.next() + ">");
							} %>
				</datalist>
				
				<br><label for="course_no"> Course Number </label>
				<br><input type="text" name="course_no" size ="20" maxlength= "5"list="courses_no_list">
				<datalist id="courses_no_list">
						<%  Iterator<String> itr2 = course.iterator();
							while (itr2.hasNext()) {
								out.println("<option value=" + itr2.next() + ">");
							} %>
				</datalist>
					
				<br><label for="section_no"> Section Number </label>
				<br><input type="text" name="section_no" size = "20" maxlength="4" list="section_no_list"> 
				<datalist id="section_no_list">
						<%
							Iterator<String> itr3 = section.iterator();
							while (itr3.hasNext()) {
								out.println("<option value=" + itr3.next() + ">");
							} %>
				</datalist>
		
				<br><label for="crn_no"> CRN </label> 
				<br><input id="crn_no" type="text" name="crn_no" size="20" maxlength="5" list="crn_no_list" required>
				<datalist id="crn_no_list">
						<%
							Iterator<String> itr4 = crn.iterator();
							while (itr4.hasNext()) {
								out.println("<option value=" + itr4.next() + ">");
							} %>
				</datalist></div>
				
				<div style="position:relative;top:0;left:0;">
					<img src="banner2.PNG" alt="Sorry image cannot be displayed" width="1000" height="400" style="position:absolute;top:0;left:200;"/>
					<img src="info.png" alt="Sorry image cannot be displayed" width="159" height="400" style="position:absolute;top:0;left:431;"/></div>
		
		<div style="position:relative;top:410;left:-250;">
			<fieldset>
				<legend> How would you like to be notified </legend>
				<br><input type="radio" name="notify" value="E" checked> Email
				<br><input type="radio" name="notify" value="P"> SMS Text
				<br><input type="radio" name="notify" value="B"> Both
			</fieldset></div>
			<input type="submit" name="button" value="Submit" style="position:relative; top:365px; left:-150px;">
	</form>
	<br>
	</div>
</body>
</html>