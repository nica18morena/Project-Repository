<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <jsp:useBean id="user" class="db.DBConnection" scope="session" />
<%@ page import="java.sql.*"%>
<!DOCTYPE>
<html>
<head>
<meta charset=UTF-8>
<title>Process Remove</title>
</head>
<body>
<% if(user.conn == null)
	user.openDB();%>
	
	<% 
	String remove[] = request.getParameterValues("to_delete");
		String user_name = (String)session.getAttribute("username");
		if( remove != null){
		for(int i = 0; i<remove.length; i++){
			user.deleteCourse(remove[i], user_name); 
			}
		}
			else {%>
				<script>
		alert("Nothing selected"); </script>
			<% }%>
			
			<jsp:forward page="Menu.jsp"></jsp:forward> 
</body>
</html>