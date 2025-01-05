package basic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/UpdateProfile")
public class UpdateProfile extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String email = request.getParameter("email");
            session.setAttribute("email", email); // Store email in session
            response.sendRedirect("SessionInfo"); // Redirect to session info page
        } else {
            response.sendRedirect("Login.html"); // Redirect to login if session is invalid
        }
    }
}