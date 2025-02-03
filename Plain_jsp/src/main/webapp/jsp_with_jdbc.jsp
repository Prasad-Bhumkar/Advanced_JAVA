<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>user input form</h1>
	
	<form action="" method="post">
		<label for = "name">Name :</label>
		<input type="text" id="name" name="name" required>
		<label for = "pass">Password :</label>
		<input type="password" id="pass" name="pass" required>
		<input type="submit" value="login">
	</form>
	
	
	<%
	String name = request.getParameter("name");
	String pass = request.getParameter("pass");
	
	if(name!=null && pass!=null){
		boolean isvaliduser = checkCredentials(name,pass);
		
		if(isvaliduser){
	%>
	
			<h2>login was successful.......!</h2>
			<p><b>Name :</b> <%=name %></p>
			<p><b>Password :</b><%=pass %> </p>
			
			
			<p>Table of squares :</p>
			<table border="1">
				
				<tr>
					<th>Number</th>
					<th>Square</th>
				</tr>
				
				<%
				for (int i=1;i<=5;i++){
				%>
				<tr>
					<td><%=i %></td>
					<td><%=i*i %></td>
				</tr>
				<%
				}
				%>
			</table>
			
			
			<p>Expression Example :268 + 956 =<%=268+956 %></p>
			
			<%
		}else{
			out.println("<p>invalid credentials.....! please try again...!</p>");
		}
	}else{
			%>
			<p>please enter your information above.....!</p>
			<%
			}
			%>
			<%!
			public boolean checkCredentials(String username,String password){
				boolean isValid = false;
				
				try{
					//load driver
					Class.forName("com.mysql.cj.jdbc.Driver");
					//establish connection
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "admin", "admin");
					
					//query
					String query = "select * from credentials where username=? and password=?";
					
					//prepared stmt
					PreparedStatement pstmt = con.prepareStatement(query);
					pstmt.setString(1, username);
					pstmt.setString(2, password);
					
					//execute query
					ResultSet rs = pstmt.executeQuery();
					
					//check validation of user
					if(rs.next()){
						isValid=true;
					}
					
					//closing valuable resourses
					 	rs.close();
		                pstmt.close();
		                con.close();

					
					
				}catch(Exception e){
					e.printStackTrace();
				}
				return isValid;
			}
			%>
</body>
</html>