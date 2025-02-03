package hibernateAnnotationConfig;

import org.hibernate.Session; // Import the Session class for interacting with the database
import org.hibernate.SessionFactory; // Import the SessionFactory class for creating Session objects
import org.hibernate.Transaction; // Import the Transaction class for managing database transactions
import org.hibernate.boot.registry.StandardServiceRegistryBuilder; // Import for building the ServiceRegistry
import org.hibernate.cfg.Configuration; // Import the Configuration class for hibernate configuration
import org.hibernate.service.ServiceRegistry; // Import the ServiceRegistry interface

public class MainApp {

  public static void main(String[] args) {
    // Hibernate configuration (reads hibernate.cfg.xml)
    Configuration cfg = new Configuration();
    cfg.configure().addAnnotatedClass(Employee.class);

    // Build ServiceRegistry (applies settings from configuration)
    ServiceRegistry reg = new StandardServiceRegistryBuilder()
        .applySettings(cfg.getProperties())
        .build();

    // Create SessionFactory from configuration and ServiceRegistry
    SessionFactory factory = cfg.buildSessionFactory(reg);

    // Open a new Session from the SessionFactory
    Session session = factory.openSession();

    // Begin a transaction
    Transaction transaction = session.beginTransaction();

    // Create a new Employee object with details
    Employee employee = new Employee(5, "sarthak", "Java Developer");

    // Save the employee object to the database
    session.save(employee);

    // Commit the transaction to make changes permanent
    transaction.commit();

    // Close the session
    session.close();

    System.out.println("Employee saved successfully..!");
  }
}