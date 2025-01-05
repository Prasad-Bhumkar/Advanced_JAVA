package calculator_servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Login_Handler")
public class Login_Handler extends HttpServlet {
    
    private static final String USERNAME = "admin";  
    private static final String PASSWORD = "admin";  

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Set the content type to HTML
        
        PrintWriter out = response.getWriter();
        
        // Retrieve username and password from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validate credentials
        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            // Successful login
        	
        	RequestDispatcher rd = request.getRequestDispatcher("arithmatic_operations.html");
        	rd.forward(request, response);
        } else {
            // Unsuccessful login
        	out.println("Login Again......!");
        	RequestDispatcher rd = request.getRequestDispatcher("Login.html");
        	rd.forward(request, response);
        }
    }
}