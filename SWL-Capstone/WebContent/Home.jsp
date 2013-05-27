<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset= UTF-8>
<link rel="stylesheet" type="text/css" href="clearFormatting.css">
<link rel="stylesheet" type="text/css" href="SMAstyler.css">
<title>Home</title>
<script src="scripts.js"></script>
</head>
<body>

<div class="container">
<div class="header">
<h1>Student Major Assistant</h1></div>
<div id="loginform">
<form id="login" name="log_in" action="pLogin.jsp" method="post" onsubmit="validate_login()">
<fieldset>
<legend>Login</legend>
	<label for="name_field">Username</label>
	<br><input type= "text" name= "name_field" size= "20" maxlength="15" required>
	<br><label for="password_field">Password</label>
	<br><input type= "password" name= "password_field" size ="15" maxlength= "10" required>
	<br><label for="createAccount">Not signed up?</label>
	<br><input type="submit" value="Login">
	<button class="buttons" name="createAccount" Style="width:150px;align:left" onclick="createAccount_js()">Sign up</button>
	</fieldset></form></div>
	
	<div>
	<div class="what">
	<p class="what" style="position:relative; top:0; left:200;">What:</p>
	<br>
	<fieldset>
	<p class="what">Student Major Assistant monitors closed courses for you and notifies you
	when an opening is found, so you don't have to!</p></fieldset></div>
	<div>
	<img src="lines2.gif"></div>
	<div class="how">
	<p class="how">How:</p>
	<br>
	<fieldset>
	<button class= "circle">1</button><p class="steps">Sign up</p>
	<button class= "circle">2</button><p class="steps">Add courses</p>
	<button class="circle">3</button><p class="steps">Sit back and relax!</p>
	</fieldset>
	</div>
	</div>
	</div>
	
</body>
</html>