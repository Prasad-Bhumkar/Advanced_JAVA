package CRUD_combined;

import java.sql.*;

/**
 * This class encapsulates CRUD (Create, Read, Update, Delete) operations for the 'student' table.
 * It uses PreparedStatement for better security and performance.
 */
public class CRUD {

    private Connection con;
    private PreparedStatement pstmt;

    /**
     * Constructor to establish the database connection.
     */
    public CRUD() {
        try {
            // Establish the connection to the database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "admin", "admin"); 
            System.out.println("Connection established");
        } catch (SQLException e) {
            System.err.println("Error establishing connection: " + e.getMessage()); 
        }
    }

    /**
     * Inserts a new student record into the database.
     *
     * @param stud_id   The student ID.
     * @param stud_Name The student name.
     * @param stud_Marks The student's marks.
     * @return A message indicating the success or failure of the insertion.
     * @throws SQLException If an error occurs during the database operation.
     */
    public String insertData(int stud_id, String stud_Name, double stud_Marks) throws SQLException {
        String query = "INSERT INTO student (stud_id, stud_Name, stud_Marks) VALUES (?, ?, ?)";
        pstmt = con.prepareStatement(query);
        pstmt.setInt(1, stud_id);
        pstmt.setString(2, stud_Name);
        pstmt.setDouble(3, stud_Marks);
        int count = pstmt.executeUpdate();
        return count + " Data Inserted Successfully!"; 
    }

    /**
     * Updates an existing student record in the database.
     *
     * @param stud_id   The student ID to update.
     * @param stud_Name The new student name.
     * @param stud_Marks The new student marks.
     * @return A message indicating the success or failure of the update.
     * @throws SQLException If an error occurs during the database operation.
     */
    public String updateData(int stud_id, String stud_Name, double stud_Marks) throws SQLException {
        String query = "UPDATE student SET stud_Name=?, stud_Marks=? WHERE stud_id=?";
        pstmt = con.prepareStatement(query);
        pstmt.setString(1, stud_Name);
        pstmt.setDouble(2, stud_Marks);
        pstmt.setInt(3, stud_id);
        int count = pstmt.executeUpdate();
        return count + " Data Updated Successfully!";
    }

    /**
     * Deletes a student record from the database.
     *
     * @param stud_id The student ID to delete.
     * @return A message indicating the success or failure of the deletion.
     * @throws SQLException If an error occurs during the database operation.
     */
    public String deleteData(int stud_id) throws SQLException {
        String query = "DELETE FROM student WHERE stud_id=?";
        pstmt = con.prepareStatement(query);
        pstmt.setInt(1, stud_id);
        int count = pstmt.executeUpdate();
        return count + " Data Deleted Successfully!";
    }

    /**
     * Retrieves the details of a student from the database.
     *
     * @param stud_id The student ID to retrieve.
     * @return A string containing the student's details or a "Student not found" message.
     * @throws SQLException If an error occurs during the database operation.
     */
    public String retrieveData(int stud_id) throws SQLException {
        String query = "SELECT * FROM student WHERE stud_id=?";
        pstmt = con.prepareStatement(query);
        pstmt.setInt(1, stud_id);
        ResultSet rs = pstmt.executeQuery();
        StringBuilder result = new StringBuilder();
        if (rs.next()) {
            result.append("Student ID: ").append(rs.getInt("stud_id")).append("\n");
            result.append("Student Name: ").append(rs.getString("stud_Name")).append("\n");
            result.append("Student Marks: ").append(rs.getDouble("stud_Marks")).append("\n");
        } else {
            result.append("Student not found.");
        }
        return result.toString();
    }

    /**
     * Closes the database connection and prepared statement.
     *
     * @throws SQLException If an error occurs while closing the connection.
     */
    public void closeConnection() throws SQLException {
        if (pstmt != null) {
            pstmt.close();
        }
        if (con != null) {
            con.close();
        }
    }
}