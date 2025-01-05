package request_diaspatcher;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/First")
public class First extends HttpServlet {
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
			String user = request.getParameter("uname");
			PrintWriter out = response.getWriter();
			
			out.println("<h1>Welcome "+user+"</h1>");
			
			//request dispatcher
			RequestDispatcher rd = request.getRequestDispatcher("/Second");
			
			//to see ouput of servlet first+second
			rd.include(request, response);
			
			//to see output of servlet second only
			//rd.forward(request, response); 
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
