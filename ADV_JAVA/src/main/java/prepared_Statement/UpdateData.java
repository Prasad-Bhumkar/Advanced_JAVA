package prepared_Statement;

import java.sql.*;
import java.util.Scanner;

public class UpdateData {

    public static void main(String[] args) {

        try {
            // 1. Load and register the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully!!");

            // 2. Establish the connection
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "admin", "admin");
            System.out.println("Connection established");

            // 3. Create Prepared Statement
            String query = "update employee set emp_Name=?, emp_Salary=? where emp_ID=?";
            PreparedStatement pstmt = con.prepareStatement(query);

            // 4. Get user input
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter employee ID: ");
            int id = sc.nextInt();

            System.out.println("Enter new employee name: ");
            sc.nextLine(); // Consume the newline character left by nextInt()
            String name = sc.nextLine();

            System.out.println("Enter new employee salary: ");
            double salary = sc.nextDouble();

            // 5. Set parameters for Prepared Statement
            pstmt.setString(1, name);
            pstmt.setDouble(2, salary);
            pstmt.setInt(3, id);

            // 6. Execute the Prepared Statement
            int rowsUpdated = pstmt.executeUpdate();

            // 7. Print the result
            if (rowsUpdated > 0) {
                System.out.println("Employee data updated successfully.");
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