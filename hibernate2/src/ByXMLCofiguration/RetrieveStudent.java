package ByXMLCofiguration;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class RetrieveStudent {

    public static void main(String[] args) {
        // Hibernate configuration (reads hibernate.cfg.xml)
        Configuration cfg = new Configuration();
        cfg.configure();

        // Build ServiceRegistry (applies settings from configuration)
        ServiceRegistry reg = new StandardServiceRegistryBuilder()
                .applySettings(cfg.getProperties())
                .build();

        // Create SessionFactory from configuration and ServiceRegistry
        SessionFactory factory = cfg.buildSessionFactory(reg);

        // Open a new Session from the SessionFactory
        Session session = factory.openSession();

        // Begin a transaction (optional for retrieval)
        // Transaction transaction = session.beginTransaction();

        // Retrieve student object with ID 1
        Student student = (Student) session.get(Student.class, 1);

        // Check if student object is found
        if (student != null) {
            System.out.println("Student Details:");
            System.out.println("ID: " + student.getId());
            System.out.println("Name: " + student.getName());
            System.out.println("Department: " + student.getCourse());
        } else {
            System.out.println("Student with ID 1 not found.");
        }

        // Commit the transaction (if started)
        // transaction.commit();

        // Close the session
        session.close();
    }
}