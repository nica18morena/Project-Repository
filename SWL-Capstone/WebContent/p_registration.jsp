<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean id="user" class="db.DBConnection" scope="session" />
<%@ page import="java.sql.*"%>
<!DOCTYPE>
<html>
<head>
<meta charset=UTF-8>
<title>Process Register</title>
</head>
<body>
<% if(user.conn == null)
user.openDB(); %>

	<% String fname = request.getParameter("firstname");
		String lname = request.getParameter("lastname");
		String uname = request.getParameter("username");
		String pword1 = request.getParameter("password1");
		String pword2 = request.getParameter("password2");
		String email = request.getParameter("email");
		String num = request.getParameter("mobile_num");
		String carrier = request.getParameter("pcarrier");%>
		
		<%
		ResultSet rs;
		rs=user.user(uname);
		boolean good = true;
		
		while(rs.next()){
			good = false;
			%><script> alert("User already exist, please login");
			window.location = "Home.jsp";</script><%
		}

		if(!pword1.equals(pword2)){
			good = false;
		%><script> alert("Passwords do not match");
		window.location="register.jsp";</script><%
		}
		
		if(carrier.equals("Verizon"))
			carrier ="vtext.com";
		else if(carrier.equals("At&t"))
			carrier = "txt.att.net";
		else if(carrier.equals("T-mobile"))
			carrier= "tmomail.net";
		else if(carrier.equals("Boost"))
			carrier= "myboostmobile.com";
		else if(carrier.equals("Sprint"))
			carrier= "messaging.sprintpcs.com";
		else if(carrier.equals("Virgin"))
			carrier= "vmobl.com";
		System.out.println(good);
		if(good){
		user.adduser(email, fname, lname, uname, pword1, num, carrier);
		if(uname != null || uname !=""){
			session.setAttribute("username", uname);%>
			<jsp:forward page="confirm.jsp"></jsp:forward>
		<% }
		}
			System.out.printf("%s, %s\n", pword1,pword2);
		System.out.printf("%s,  %s,  %s,  %s,   %s,  %s\n", fname, lname, uname, email, num, carrier);%>

</body>
</html>