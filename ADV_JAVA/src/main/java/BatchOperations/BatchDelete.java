package BatchOperations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchDelete {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/jdbc";
        String username = "admin";
        String password = "admin";

        Connection conn = null;
        Statement stmt = null;

        try {
            // 1. Establish connection
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection established");

            // 2. Disable auto-commit for batch processing
            conn.setAutoCommit(false);

            // 3. Create a statement object
            stmt = conn.createStatement();

            // 4. Add multiple SQL queries to the batch
            stmt.addBatch("DELETE FROM student WHERE stud_id = 21");
            stmt.addBatch("DELETE FROM student WHERE stud_id = 22");
            stmt.addBatch("DELETE FROM student WHERE stud_id = 23");

            // 5. Execute the batch
            int[] results = stmt.executeBatch();

            // 6. Commit the transaction
            conn.commit();

            // 7. Handle results
            System.out.println("Batch executed successfully!!!!!");
            System.out.println("Rows deleted : " + results.length);

            for (int result : results) {
                System.out.println(result); // Each element in 'results' will be 0 for successful DELETE operations
            }

        } catch (SQLException e) {
            try {
                // Rollback the transaction in case of an error
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}