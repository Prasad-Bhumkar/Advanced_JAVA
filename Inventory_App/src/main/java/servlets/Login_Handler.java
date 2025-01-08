package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Login_Handler")
public class Login_Handler extends HttpServlet {

	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		
		PrintWriter out = response.getWriter();
		
		try {
        	// 1. Load and register the driver
			Class.forName("com.mysql.cj.jdbc.Driver");
	        System.out.println("Driver loaded successfully!!");

	        // 2. Establish the connection
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inv_db", "admin", "admin");
	        System.out.println("Connection established");
	        
	        // 3. Create Prepared Statement
	        String query = "select * from users";
	        PreparedStatement pstmt = con.prepareStatement(query);
	        
	        
	        //4.execute query and catch result
	        ResultSet rs=pstmt.executeQuery();
	        
	        //5.validate credentials
	        boolean flag=false;
	        
	        while(rs.next())
	        {
	        	if (user.equals(rs.getString(1))&&pass.equals(rs.getString(2))) {
					flag=true;
				}
	        }
	        
	        if (flag==true) {
	        	out.println("Login successfully");
			} else {
				out.println("Login Failed");
				//redirect user to registration page if not matched credentials
		        RequestDispatcher rd = request.getRequestDispatcher("Register.html");
		        rd.forward(request, response);
			}
	        

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
