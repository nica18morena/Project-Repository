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
<title>Register</title>
<script src="scripts.js"></script>
</head>
<body onload="get_browser()">
<div class= "container">
<div class="header">
<h1>Student Major Assistant</h1></div>
	<div class="header2"><h2>Register</h2></div>

	<form action="p_registration.jsp" onsubmit="return vRegform()" method="post">
		<div>
			<br><label for="firstname">First name</label>
			<br><input id="firstname" type = "text" name= "firstname" required = "required" size= "20" maxlength="15" placeholder="Jane">
			<br><label for="lastname">Last name</label>
			<br><input id="lastname" type="text" name="lastname" required = "required" size= "20" maxlength="15" placeholder="Doe">
			<br><label for="username">Username</label>
			<br><input id="username" type = "text" name= "username" required = "required" size= "20" maxlength="15" placeholder= "jane.doe">
			<br><label for="password1">Password</label>
			<br><input id="password1" type="password" name="password1" required = "required" size ="15" maxlength= "10" placeholder= "password">
			<br><label for="password2">Re-enter password</label>
			<br><input id="password2" type = "password" name= "password2" required = "required" size ="15" maxlength= "10" placeholder= "confirm password">
			<br><label for="email">Email</label>
			<br><input id="email" type="email" name="email" required = "required"  size = "30" maxlength="25" placeholder="youremail@wherever.com">
			<br><label for="mobile_num">Mobile number</label>
			<br><input id="mobile_num" type ="tel" name= "mobile_num" required = "required" size ="15" maxlength= "10" placeholder= "1234567890">

		</div>
		
		<fieldset>
		<legend>
		Carrier
		<select id="pcarrier" name= "pcarrier" size="1">
		<option value=" " selected = "selected">Choose one</option>
		<option value="Verizon">Verizon</option>
		<option value="At&t">Att</option>
		<option value="T-mobile">T-mobile</option>
		<option value= "Boost">Boost</option>
		<option value= "Sprint">Sprint</option>
		<option value= "Virgin">Virgin</option>
		</select>
		</legend></fieldset>
		<input type="submit" value="Submit">
		
	</form>
	<button class="buttons" onclick="cancelRegister()" style="position:relative; top:0px; left:1175px;">Cancel</button>
	</div>
</body>
</html>