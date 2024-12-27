package prepared_Statement;

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

            // 3. Create Prepared Statement
            String query = "insert into employee values(?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);

            // 4. Set parameters for Prepared Statement
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setDouble(3, salary);

            // 5. Execute the Prepared Statement
            pstmt.executeUpdate();
            System.out.println("employee Data added successfully");

            // 6. Close resources
            pstmt.close();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}