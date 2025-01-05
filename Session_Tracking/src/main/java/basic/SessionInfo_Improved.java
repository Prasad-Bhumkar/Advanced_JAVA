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
public class SessionInfo_Improved extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        
        StringBuilder htmlResponse = new StringBuilder();
        
        if (session != null) {
            htmlResponse.append("<h1>Session Information</h1>");
            htmlResponse.append("Session ID: ").append(session.getId()).append("<br>");
            htmlResponse.append("Creation Time: ").append(new Date(session.getCreationTime())).append("<br>");
            htmlResponse.append("Last Accessed Time: ").append(new Date(session.getLastAccessedTime())).append("<br>");
            htmlResponse.append("Username: ").append(escapeHtml((String) session.getAttribute("username"))).append("<br>");
            htmlResponse.append("Email: ").append(escapeHtml((String) session.getAttribute("email"))).append("<br>");
            htmlResponse.append("<a href='UserProfile.html'>Edit Profile</a><br>");
            htmlResponse.append("<a href='Logout'>Logout</a>");
        } else {
            htmlResponse.append("<h1>No active session.</h1>");
            htmlResponse.append("<a href='Login.html'>Login</a>");
        }
        
        out.println(htmlResponse.toString());
    }

    private String escapeHtml(String input) {
        if (input == null) return "";
        return input.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#39;");
    }
}
