package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoLayer {

	PreparedStatement pstmt;
	Connection con;
	
	public DaoLayer() {
		
		
		try {
			//driver loading
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "admin", "admin");
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	public boolean regDetails(String user , String pass) {
		
		String query = "insert into credentials values(?,?)";
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, user);
			pstmt.setString(2, pass);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}

	}
	
	public boolean loginDetails(String user, String pass) {
		
		String query = "select * from credentials";
		
		try {
			pstmt=con.prepareStatement(query);
			
			ResultSet rs=pstmt.executeQuery();
			
			boolean flag=false;
			
			while (rs.next()) {
				
				if (user.equals(rs.getString(1))&&pass.equals(rs.getString(2))) {
					flag=true;
				}
			}
			
			if (flag==true) {
				return true;
			}else {
				return false;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
		
	}
		
	
}
