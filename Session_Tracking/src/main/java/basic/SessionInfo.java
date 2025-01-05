package basic;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

@WebServlet("/SessionInfo")
public class SessionInfo extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        
        if (session != null) {
            out.println("<h1>Session Information</h1>");
            out.println("Session ID: " + session.getId() + "<br>");
            out.println("Creation Time: " + new Date(session.getCreationTime()) + "<br>");
            out.println("Last Accessed Time: " + new Date(session.getLastAccessedTime()) + "<br>");
            out.println("Username: " + session.getAttribute("username") + "<br>");
            out.println("Email: " + session.getAttribute("email") + "<br>"); // Display email
            out.println("<a href='UserProfile.html'>Edit Profile</a><br>");
            out.println("<a href='Logout'>Logout</a>");
        } else {
            out.println("<h1>No active session.</h1>");
            out.println("<a href='Login.html'>Login</a>");
        }
    }
}