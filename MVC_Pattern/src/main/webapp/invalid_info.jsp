<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	

	<% 
	String user = request.getParameter("username"); 
	%>
	 
	<h1>Welcome <%=user %>. You have entered wrong username or password...!</h1>
</body>
</html>