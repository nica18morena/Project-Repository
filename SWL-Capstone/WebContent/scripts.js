function validate_login(){
	var un = document.forms["login"]["name_field"].value;
	var pw = document.forms["login"]["password_field"].value;
	if(un == null || un =="" || pw== null || pw == ""){
		alert("Username and password needed");
		return false;
	}
}

function incorrect_credentials(){
	alert("Incorrect username or password");

}

function get_browser(){
	var browser = navigator.appName;
	var version = navigator.appVersion;

	if(browser == "Microsoft Internet Explorer" && version.substring(0) <= "9" ){
		alert("Your browser does not support functions used on this page");
	}
}

function confirms(){
	alert("You have successfully created an account");
}

function sendHome(){
	window.location = "Menu.jsp";
}

function confirmDelete(){
	var deletes;
	deletes = confirm("Are you sure you want to delete your account?");

	if(deletes == true){
		window.location = "p_delete.jsp";
	}
}

function confirmRemove(){
	var empty= true;
	var remove;
	var selected = document.forms[0].to_delete;
	for(var i = 0; i < selected.length; i++){
		if(selected[i].checked){
			empty = false;
			break;
		}
	}
	if(empty){
		alert("Select a course to remove");
		return false;
	}
	remove = confirm("Are you sure you want to remove this course?");

	if(remove != true){
		return false;
	}

}

function cancelRegister(){
	var con;
	con= confirm("Are you sure you want to leave this page?");
	if(con==true){
		window.location ="Home.jsp";
	}
}

function editProfile(){
	window.location ="p_edit_profile.jsp";
}

function createAccount_js(){
	window.location="register.jsp";
}

function validateAdd(notify){
	var crn= document.getElementById("crn_no").value.match(/^\d{5}$/);

	if(crn== null){
		alert("CRN value must contain numeric values");
		document.getElementById("crn_no").focus();
		return false;
	}
	return true;
}

function checkCarrier(){
	var c = document.getElementById("pcarrier");
	var carrier = c.options[c.selectedIndex].text;

	if(carrier === "Choose one"){
		alert("Select a carrier");
		return false;
	}
	return true;
}

function vRegform() {
	"use strict";
	var fname = document.getElementById("firstname").value.match(/[a-zA-Z]{1,15}/);
	var lname = document.getElementById("lastname").value.match(/[a-zA-Z]{1,15}/);
	var username = document.getElementById("username").value.match(/.{1,15}/);
	var password1 = document.getElementById("password1").value.match(/.{6,10}/);
	var email = document.getElementById("email").value.match(/.{1,15}@.{2,10}\.[a-zA-Z]{3}/);
	var phone = document.getElementById("mobile_num").value.match(/[0-9]{10}/);
	var c = document.getElementById("pcarrier");
	var carrier = c.options[c.selectedIndex].text;

	if (fname === null) {
		alert("Enter a valid name");
		document.getElementById("fistname").focus();
		return false;
	}
	if (lname === null) {
		alert("Enter a valid last name");
		document.getElementById("lastname").focus();
		return false;
	}
	if (username === null) {
		alert("Enter a valid username");
		document.getElementById("username").focus();
		return false;
	}
	if (password1 === null) {
		alert("Enter a password 6 characters or more");
		document.getElementById("password1").focus();
		return false;
	}

	if (document.getElementById("password1").value != document.getElementById("password2").value) {
		alert("Passwords must match");
		document.getElementById("password1").focus();
		document.getElementById("password2").focus();
		return false;
	}
	if (email === null) {
		alert("Enter a valid email");
		document.getElementById("email").focus();
		return false;
	}
	if (phone === null){
		alert("Enter a 10 digit phone number\n without spaces");
		document.getElementById("mobile_num").focus();
		return false;
	}
	if(carrier === "Choose one"){
		alert("Select a carrier");
		return false;
	}
	alert("You have sucessfully created a profile");
	return true;
}

function sendBack(){
	window.location="edit_profile.jsp";
}

