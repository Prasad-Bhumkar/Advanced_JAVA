<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page errorPage="error.jsp" %>
    <%@ include file="/header.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Form</title>
</head>
<body>

	<form action="" method="post">
		<label for = "name">Name :</label>
		<input type="text" id="name" name="name" required>
		<label for = "pass">Password :</label>
		<input type="password" id="pass" name="pass" required>
		<input type="submit" value="login">
	</form>
	
	
	<%! 
		//declaration tag
		public boolean validateuser(String name, String pass){
		return name.equals("admin")&&pass.equals("admin");
	}
	%>
	
	
	<%
		//scriptlet Tag
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		
		//check if form is submitted 
		if(name!=null && pass!=null){
			if(validateuser(name, pass)){	
		
	%>
	
	<h2>Login was successful......!</h2>
	
	<!--expression tag  -->
	
		<p>Example of expression Tag : 269 + 364 = <%= 269+364 %></p>
		
	<!--mixing scripting elements with html  -->
	
	<table>
		<tr>
			<th>Numbers :</th>
			<th>Square :</th>
		</tr>
		
		<%
			for(int i=0;i<=5;i++){
		%>
		<tr>
		<td><%=i %></td>
		<td><%=i*i %></td>
		</tr>
		<%
			}
		%>
	</table>
	
	<% 
			}else{
				throw new RuntimeException();
			}
		}else{
	%>
	<h2>Please enter your username and password...!</h2>
	<%
	}
		%>
		
	

</body>
</html>