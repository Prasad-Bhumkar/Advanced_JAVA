package Statement_interface;

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

            // 3. Create Statement object
            Statement stmt = con.createStatement();

            // 4. Get user input for employee ID
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter employee ID: ");
            int id = sc.nextInt();

            // 5. Create the SQL delete statement (**Security Risk**)
            String query = "delete from employee where emp_ID=" + id;

            // 6. Execute the delete statement
            int rowsDeleted = stmt.executeUpdate(query);

            // 7. Print the result
            if (rowsDeleted > 0) {
                System.out.println("Employee data deleted successfully.");
            } else {
                System.out.println("Employee not found.");
            }

            // 8. Close resources
            stmt.close();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}