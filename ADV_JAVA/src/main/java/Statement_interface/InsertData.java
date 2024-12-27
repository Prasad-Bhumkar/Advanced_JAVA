package Statement_interface;

import java.sql.*;
import java.util.Scanner;

public class InsertData {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter employee Id: ");
        int id = sc.nextInt();

        System.out.println("Enter Employee Name: ");
        sc.nextLine(); // Consume the newline character left by nextInt()
        String name = sc.nextLine();

        System.out.println("Enter Employee Salary: ");
        double salary = sc.nextDouble();

        try {
            // 1. Load and register the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully!!");

            // 2. Establish the connection
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "admin", "admin");
            System.out.println("Connection established");

            // 3. Create Statement object
            Statement stmt = con.createStatement();

            // 4. Create the SQL insert statement
            String query = "insert into employee values(" + id + ", '" + name + "', " + salary + ")";

            // 5. Execute the insert statement
            stmt.executeUpdate(query);
            System.out.println("Employee Data added successfully");

            // 6. Close resources
            stmt.close();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}