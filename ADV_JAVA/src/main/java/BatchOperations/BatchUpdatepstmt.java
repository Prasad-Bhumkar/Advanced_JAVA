package BatchOperations;

import java.sql.*;

public class BatchUpdatepstmt {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/jdbc";
        String username = "admin";
        String password = "admin";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // 1. Establish connection
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection established");

            // 2. Disable auto-commit for batch processing
            conn.setAutoCommit(false);

            // 3. Create a prepared statement
            String sql = "INSERT INTO student (stud_id, stud_Name, stud_Marks) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);

            // 4. Add multiple sets of parameters to the batch
            pstmt.setInt(1, 21);
            pstmt.setString(2, "ravi");
            pstmt.setDouble(3, 88);
            pstmt.addBatch();

            pstmt.setInt(1, 22);
            pstmt.setString(2, "sanket");
            pstmt.setDouble(3, 98);
            pstmt.addBatch();

            pstmt.setInt(1, 23);
            pstmt.setString(2, "sarthak");
            pstmt.setDouble(3, 78);
            pstmt.addBatch();

            // 5. Execute the batch
            int[] results = pstmt.executeBatch();

            // 6. Commit the transaction
            conn.commit();

            // 7. Handle results
            System.out.println("Batch executed successfully!!!!!");
            System.out.println("Rows added : " + results.length);

            for (int result : results) {
                System.out.println(result);
            }

        } catch (SQLException e) {
            try {
                // Rollback the transaction in case of an error
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
