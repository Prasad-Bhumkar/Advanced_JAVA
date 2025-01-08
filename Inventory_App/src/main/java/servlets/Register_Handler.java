package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Register_Handler")
public class Register_Handler extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		
		PrintWriter out = response.getWriter();
		out.println("<h1>Welcome "+user+"</h1>");
		
		
        try {
        	// 1. Load and register the driver
			Class.forName("com.mysql.cj.jdbc.Driver");
	        System.out.println("Driver loaded successfully!!");

	        // 2. Establish the connection
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inv_db", "admin", "admin");
	        System.out.println("Connection established");
	        
	        // 3. Create Prepared Statement
	        String query = "insert into users values(?, ?)";
	        PreparedStatement pstmt = con.prepareStatement(query);

	        // 4. Set parameters for Prepared Statement
	        pstmt.setString(1, user);
	        pstmt.setString(2, pass);
	        

	        // 5. Execute the Prepared Statement
	        pstmt.executeUpdate();
	        System.out.println("Registered successfully");
	        
	        //redirect user to login page
	        RequestDispatcher rd = request.getRequestDispatcher("/Login.html");
	        rd.include(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

