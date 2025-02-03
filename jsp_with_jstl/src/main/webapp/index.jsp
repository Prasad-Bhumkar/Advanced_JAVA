<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page errorPage="error.jsp" %>
<%@ include file="header.jsp" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Input Form</title>
</head>
<body>
    <h1>User Input Form</h1>

    <form action="" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required><br><br>
        <label for="pass">Password:</label>
        <input type="password" id="pass" name="pass" required><br><br>
        <input type="submit" value="Login">
    </form>

    <%
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
        boolean isValid = false;

        if (name != null && pass != null) {
            isValid = checkCredentials(name, pass); 
        }

        request.setAttribute("name", name); 
        request.setAttribute("pass", pass);
        request.setAttribute("isValid", isValid); 
    %>

    <c:choose>
        <c:when test="${not empty name and not empty pass}">
            <c:choose>
                <c:when test="${isValid}">
                    <h2>Login was successful!</h2>
                    <p><b>Name:</b> ${name}</p>
                    <p><b>Password:</b> ${pass}</p>

                    <p>Table of squares:</p>
                    <table border="1">
                        <tr>
                            <th>Number</th>
                            <th>Square</th>
                        </tr>
                        <c:forEach var="i" begin="1" end="5">
                            <tr>
                                <td>${i}</td>
                                <td>${i * i}</td>
                            </tr>
                        </c:forEach>
                    </table>

                    <p>Expression Example: 268 + 956 = ${268 + 956}</p>
                </c:when>
                <c:otherwise>
                    <p>Invalid credentials... Please try again!</p>
                </c:otherwise>
            </c:choose>
        </c:when>
        <c:otherwise>
            <p>Please enter your information above...</p>
        </c:otherwise>
    </c:choose>

    <%!
        public boolean checkCredentials(String username, String password) {
            boolean isValid = false;

            try {
                // ... (Your database connection and query logic) ...
            } catch (Exception e) {
                e.printStackTrace();
            }
            return isValid;
        }
    %>
</body>
</html>