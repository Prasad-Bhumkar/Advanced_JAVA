package hibernateAnnotationConfig;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class MainApp2 {

  public static void main(String[] args) {
    // Configuration
    Configuration cfg = new Configuration();
    cfg.configure().addAnnotatedClass(Employee.class);

    // Service Registry
    ServiceRegistry reg = new StandardServiceRegistryBuilder()
        .applySettings(cfg.getProperties())
        .build();

    // Session Factory
    SessionFactory factory = cfg.buildSessionFactory(reg);

    // Session and Transaction
    Session session = factory.openSession();
    Transaction transaction = session.beginTransaction();

    // Retrieve Employee Object
    Employee employee = (Employee) session.get(Employee.class, 1); // Assuming you want to retrieve the employee with ID 1

    // Check if employee exists
    if (employee != null) {
      System.out.println("ID: " + employee.getId());
      System.out.println("Name: " + employee.getName());
      System.out.println("Department: " + employee.getDepartment());
    } else {
      System.out.println("Employee not found.");
    }

    // Commit and Close
    transaction.commit();
    session.close();
  }
}