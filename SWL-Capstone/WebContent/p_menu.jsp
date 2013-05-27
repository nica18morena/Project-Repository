<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean id="user" class="db.DBConnection" scope="session" />
<%@ page import="java.sql.*"%>
<!DOCTYPE>
<html>
<head>
<meta charset=UTF-8>
<title>Menu Choices</title>
</head>
<body>
 <%if(request.getParameter("add_courses") != null){%>
	<jsp:forward page="add.jsp"></jsp:forward>
 <% }
 if(request.getParameter("courses_tracking") !=null){%>
 <jsp:forward page="view_courses.jsp"></jsp:forward>
 <%}
 if(request.getParameter("remove_course") !=null){%>
 <jsp:forward page="remove_course.jsp"></jsp:forward>
 <%}
 if(request.getParameter("edit_profile") !=null){%>
 <jsp:forward page="edit_profile.jsp"></jsp:forward>
 <%} %>
</body>
</html>