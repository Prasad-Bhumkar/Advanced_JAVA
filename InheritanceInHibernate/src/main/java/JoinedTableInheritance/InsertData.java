package JoinedTableInheritance; // Keep the package name

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class InsertData {

    public static void main(String[] args) {

        Configuration cfg = new Configuration()
                .configure() // Assuming a hibernate.cfg.xml file exists
                .addAnnotatedClass(Student.class) // Add base class
                .addAnnotatedClass(CSStudent.class)
                .addAnnotatedClass(MathStudent.class);

        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();

        SessionFactory factory = cfg.buildSessionFactory(reg);

        Session session = factory.openSession();

        Transaction transaction = session.beginTransaction();

        // Create Student objects with specific details
        CSStudent csstudent = new CSStudent(1,"Alice", "Computer Science","Data Science");
        MathStudent mathStudent = new MathStudent(2,"Bob","Information Technology",3.9 );

        session.save(csstudent);
        session.save(mathStudent);

        transaction.commit();
        System.out.println("Students saved successfully!");

        session.close();
    }
}