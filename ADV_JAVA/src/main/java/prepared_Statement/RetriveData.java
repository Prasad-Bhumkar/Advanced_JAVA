package prepared_Statement;

import java.sql.*;
import java.util.Scanner;

public class RetriveData {

    public static void main(String[] args) {

        try {
            // 1. Load and register the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully!!");

            // 2. Establish the connection
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "admin", "admin");
            System.out.println("Connection established");

            // 3. Create Prepared Statement
            String query = "select * from employee where emp_ID=?"; 
            PreparedStatement pstmt = con.prepareStatement(query); 

            // 4. Get user input for employee ID
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter employee ID: ");
            int id = sc.nextInt();

            // 5. Set parameter for Prepared Statement
            pstmt.setInt(1, id);

            // 6. Execute the Prepared Statement
            ResultSet rs = pstmt.executeQuery();

            // 7. Process the result set
            if (rs.next()) {
                int employeeId = rs.getInt("emp_ID"); 
                String employeeName = rs.getString("emp_Name");
                double employeeSalary = rs.getDouble("emp_Salary");

                System.out.println("Employee ID: " + employeeId);
                System.out.println("Employee Name: " + employeeName);
                System.out.println("Employee Salary: " + employeeSalary);
            } else {
                System.out.println("Employee not found.");
            }

            // 8. Close resources
            rs.close();
            pstmt.close();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}