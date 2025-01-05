package basic;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Login_Handler")
public class Login_Handler extends HttpServlet {
    
    private static final String USERNAME = "admin";  
    private static final String PASSWORD = "admin";  

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Retrieve username and password from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        HttpSession session = request.getSession(true); // Create a new session
        
        // Validate credentials
        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            // Successful login
            session.setAttribute("username", username);
            response.sendRedirect("SessionInfo"); // Redirect to session info page
        } else {
            // Unsuccessful login
            session.setAttribute("errorMessage", "Invalid username or password. Please try again.");
            response.sendRedirect("Login.html"); // Redirect back to login page
        }
    }
}