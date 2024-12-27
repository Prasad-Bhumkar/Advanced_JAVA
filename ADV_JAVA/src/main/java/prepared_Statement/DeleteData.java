package prepared_Statement;

import java.sql.*;
import java.util.Scanner;

public class DeleteData {

    public static void main(String[] args) {

        try {
            // 1. Load and register the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully!!");

            // 2. Establish the connection
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "admin", "admin");
            System.out.println("Connection established");

            // 3. Create Prepared Statement
            String query = "delete from employee where emp_ID=?";
            PreparedStatement pstmt = con.prepareStatement(query);

            // 4. Get user input for employee ID
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter employee ID: ");
            int id = sc.nextInt();

            // 5. Set parameter for Prepared Statement
            pstmt.setInt(1, id);

            // 6. Execute the Prepared Statement
            int rowsDeleted = pstmt.executeUpdate();

            // 7. Print the result
            if (rowsDeleted > 0) {
                System.out.println("Employee data deleted successfully.");
            } else {
                System.out.println("Employee not found.");
            }

            // 8. Close resources
            pstmt.close();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}