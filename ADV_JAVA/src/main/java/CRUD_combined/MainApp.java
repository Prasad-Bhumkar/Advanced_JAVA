package CRUD_combined;

import java.sql.SQLException;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CRUD crud = new CRUD(); 

        try {
            while (true) {
                System.out.println("\nChoose an option:");
                System.out.println("1. Insert");
                System.out.println("2. Update");
                System.out.println("3. Delete");
                System.out.println("4. Retrieve");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                int choice = sc.nextInt();
                sc.nextLine(); // Consume the newline character

                if (choice == 1) {
                    System.out.print("Enter student ID: ");
                    int id = sc.nextInt();
                    sc.nextLine(); // Consume the newline character
                    System.out.print("Enter student name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter student marks: ");
                    double marks = sc.nextDouble();
                    System.out.println(crud.insertData(id, name, marks));
                } else if (choice == 2) {
                    System.out.print("Enter student ID to update: ");
                    int id = sc.nextInt();
                    sc.nextLine(); // Consume the newline character
                    System.out.print("Enter new student name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter new student marks: ");
                    double marks = sc.nextDouble();
                    System.out.println(crud.updateData(id, name, marks));
                } else if (choice == 3) {
                    System.out.print("Enter student ID to delete: ");
                    int id = sc.nextInt();
                    System.out.println(crud.deleteData(id));
                } else if (choice == 4) {
                    System.out.print("Enter student ID to retrieve: ");
                    int id = sc.nextInt();
                    System.out.println(crud.retrieveData(id));
                } else if (choice == 5) {
                    System.out.println("Exiting...");
                    crud.closeConnection(); // Close the connection in the end
                    System.exit(0);
                } else {
                    System.out.println("Invalid choice.");
                }
            }
        } catch (SQLException e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            // Ensure connection closure even if an exception occurs
            try {
                crud.closeConnection();
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}