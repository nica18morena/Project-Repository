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
<title>Process profile edit</title>
<script src="scripts.js"></script>
</head>
<body>
<% if(user.conn == null)
	user.openDB();%>
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
	
	<% String uname = (String)session.getAttribute("username");
	if(uname != null){	
	ResultSet rs = null;
	rs= user.view_profile(uname);
	String email, fname, lname, name, pno, carrier, pw;
	while(rs.next()){
		email = rs.getString(1);
		fname= rs.getString(2);
		lname= rs.getString(3);
		name= rs.getString(4);
		pw= rs.getString(5);
		pno= rs.getString(6);
		carrier= rs.getString(7);%>
		<div class="header2">
		<h2>Profile</h2></div>	
		<div>
		<form action= "p_edit_profile2.jsp" onsubmit="return checkCarrier()" method="post">
		<fieldset>
		<legend>
		Personal Information
		</legend>
		<table>
		<tr><td><input type="text" name= "fname" size= "20" maxlength="15" value="<% out.print(fname); %>"></td></tr> 
		<tr><td><input type="text" name= "lname" size= "20" maxlength="15" value="<% out.print(lname); %>"></td></tr> 
		<tr><td><input type="email" name= "e-mail" value="<% out.print(email); %>"></td></tr> 
		<tr><td><input type="tel" name= "phone" size= "15" maxlength="10" value="<% out.print(pno); %>"></td></tr> 
		
		<tr><td><select id= "pcarrier" name= "pcarrier" size="1">
		<option value=" " selected = "selected">Choose one</option>
		<option value="Verizon">Verizon</option>
		<option value="At&t">Att</option>
		<option value="T-mobile">T-mobile</option>
		<option value= "Boost">Boost</option>
		<option value= "Sprint">Sprint</option>
		<option value= "Virgin">Virgin</option>
		</select></td></tr>
		</table>
		</fieldset>
		<input type="submit" value="Update">
		</form>
			<button id="cancel" class="buttons" onclick="sendBack()" style="position: relative; top:0; left: 1210px;">Cancel</button>
		</div>
	<% } 
 } %>
</div>
</body>
</html>