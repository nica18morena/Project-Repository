<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<link rel="stylesheet" type="text/css" href="clearFormatting.css">
<link rel="stylesheet" type="text/css" href="SMAstyler.css">
<title>Menu</title>
</head>
<body>
<div class= "container">
<div class="header">
<h1>Student Major Assistant</h1></div>
<div class="menuContainer">
<div class="menuBar">
<table>
	<tr>
	<td class="menuBar"><a class="menuBar" href="https://banweb.plu.edu/pls/pap/twbkwbis.P_GenMenu?name=homepage" target="_blank">Banner</a></td>
	<td class="menuBar"><a class="menuBar" href="https://banweb.plu.edu/pls/pap/hxskschd.P_PLUSchedule" target="_blank">Schedule of courses</a></td>
	<td class="menuBar"><a class="menuBar" href="logout.jsp">Log out</a></td>
	</tr>
</table>
<!--<ul class="menuBar">
	<li class="menuBar"><a class="menuBar" href="https://banweb.plu.edu/pls/pap/twbkwbis.P_GenMenu?name=homepage" target="_blank">Banner</a></li>
	<li class="menuBar"><a class="menuBar" href="https://banweb.plu.edu/pls/pap/hxskschd.P_PLUSchedule" target="_blank">Schedule of courses</a></li>
	<li class="menuBar"><a class="menuBar" href="logout.jsp">Log out</a></li>
</ul>-->
</div>
</div>
<div class="header2">
<h2>Home</h2></div>

<div class="menuButtons">
<fieldset>
<legend>Menu</legend>
	<form action="p_menu.jsp" method="post">
		<div class="menu">
		<button class="menubuttons" type= "submit" name="add_courses">Add Course</button>
		<button class="menubuttons" type= "submit" name="remove_course">Remove Course</button>
		<button class="menubuttons" type= "submit" name="courses_tracking">Courses Tracking</button>
		<button class="menubuttons" type= "submit" name="edit_profile">Edit Profile</button>	
		</div>
	</form></fieldset></div>
	</div>
</body>
</html>