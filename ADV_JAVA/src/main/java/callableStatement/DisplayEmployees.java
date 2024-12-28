package callableStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DisplayEmployees {

    // Step 1: Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/jdbc"; // Your database URL
    private static final String USER = "admin"; // Your database username
    private static final String PASSWORD = "admin"; // Your database password

    public static void main(String[] args) {
        // Step 2: Call the method to display all employee details
        displayAllEmployees();
    }

    // Step 3: Method to display all employees
    public static void displayAllEmployees() {
        Connection connection = null; // Initialize connection
        CallableStatement callableStatement = null; // Initialize callable statement
        ResultSet resultSet = null; // Initialize result set

        try {
            // Step 4: Establish the database connection
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            // Step 5: Prepare the callable statement for the stored procedure
            callableStatement = connection.prepareCall("{CALL displayAllEmployees()}");

            // Step 6: Execute the stored procedure
            resultSet = callableStatement.executeQuery();

            // Step 7: Process the result set
            while (resultSet.next()) {
                int id = resultSet.getInt("emp_id"); // Get employee ID
                String name = resultSet.getString("emp_name"); // Get employee name
                int age = resultSet.getInt("emp_age"); // Get employee age
                double salary = resultSet.getDouble("emp_salary"); // Get employee salary

                // Step 8: Display the employee details
                System.out.println("Employee ID: " + id);
                System.out.println("Employee Name: " + name);
                System.out.println("Employee Age: " + age);
                System.out.println("Employee Salary: " + salary);
                System.out.println("-----------------------------");
            }
        } catch (SQLException e) {
            // Step 9: Handle SQL exceptions
            e.printStackTrace();
        } finally {
            // Step 10: Close resources
            try {
                if (resultSet != null) resultSet.close(); // Close result set
                if (callableStatement != null) callableStatement.close(); // Close callable statement
                if (connection != null) connection.close(); // Close connection
            } catch (SQLException e) {
                e.printStackTrace(); // Handle exceptions during resource closing
            }
        }
    }
}