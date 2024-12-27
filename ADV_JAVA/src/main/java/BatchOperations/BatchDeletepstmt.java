package BatchOperations;

import java.sql.*;

public class BatchDeletepstmt {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/jdbc";
        String username = "admin";
        String password = "admin";
        String sql = "DELETE FROM student WHERE stud_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // 1. Establish connection
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection established");

            // 2. Disable auto-commit for batch processing
            conn.setAutoCommit(false);

            // 3. Create a prepared statement object
            pstmt = conn.prepareStatement(sql);

            // 4. Add multiple sets of parameters to the batch
            pstmt.setInt(1, 21);
            pstmt.addBatch();

            pstmt.setInt(1, 22);
            pstmt.addBatch();

            pstmt.setInt(1, 23);
            pstmt.addBatch();

            // 5. Execute the batch
            int[] results = pstmt.executeBatch();

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
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}