package login_handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login_Handler extends HttpServlet {
    
    private static final String USERNAME = "admin";  
    private static final String PASSWORD = "password";  

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
            out.print("<html><body>");
            out.print("<h2>Login successful!</h2>");
            out.print("</body></html>");
        } else {
            // Unsuccessful login
            out.print("<html><body>");
            out.print("<h2>Invalid username or password.</h2>");
            out.print("</body></html>");
        }
    }
}