package ContainmentByHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class deleteStudent {

    public static void main(String[] args) {

        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Address.class);

        ServiceRegistry reg = new StandardServiceRegistryBuilder()
                .applySettings(cfg.getProperties())
                .build();

        SessionFactory factory = cfg.buildSessionFactory(reg);

        Session session = factory.openSession();

        Transaction transaction = session.beginTransaction();

        int studentId = 1; // Replace with the ID of the student to delete

        Student student = session.get(Student.class, studentId);

        if (student != null) {
            session.delete(student); 
            transaction.commit();
            System.out.println("Student with ID " + studentId + " deleted successfully!");
        } else {
            System.out.println("Student with ID " + studentId + " not found.");
        }

        session.close();
        factory.close();
    }
}