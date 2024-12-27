package BatchOperations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



public class BatchUpdate {
	
	public static void main(String[] args) {
		
		
		String url = "jdbc:mysql://localhost:3306/jdbc";
		String username = "admin";
		String password = "admin";
		
		Connection conn = null;
		
		Statement stmt = null;
		
		try {
			
			
			//1.establish connection
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "admin", "admin");
			System.out.println("Connection established");
			
			//2.disable auto-commit for batch processing
			conn.setAutoCommit(false);
			
			//3.create a statement object
			stmt = conn.createStatement();
			
			//4.add multiple sql queries to the batch
			stmt.addBatch("insert into student (stud_id,stud_Name,stud_Marks) values (21,'ravi',88)");
			stmt.addBatch("insert into student (stud_id,stud_Name,stud_Marks) values (22,'sanket',98)");
			stmt.addBatch("insert into student (stud_id,stud_Name,stud_Marks) values (23,'sarthak',78)");
			
			//5.execute the batch
			int []  results = stmt.executeBatch();
			
			//6.commit the transaction
			conn.commit();
			
			//7.handle results
			System.out.println("Batch executed successfully!!!!!");
			System.out.println("Rows added : "+results.length);
			
			for(int result : results)
			{
				System.out.println(result);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null) stmt.close();
				if(conn != null) stmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
        
		
	}

}
