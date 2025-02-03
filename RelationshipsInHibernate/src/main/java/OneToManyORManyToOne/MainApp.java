package OneToManyORManyToOne;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MainApp {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Department.class)
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        // Saving a Department and Employees
        Department dept1 = new Department();
        dept1.setId(1);
        dept1.setName("Computer Science");

        Employee emp1 = new Employee();
        emp1.setId(101);
        emp1.setName("Alice");

        Employee emp2 = new Employee();
        emp2.setId(102);
        emp2.setName("Bob");

        dept1.addEmployee(emp1);
        dept1.addEmployee(emp2);

        session.persist(dept1);

        transaction.commit();
        System.out.println("Operations completed successfully.");

        session.close();
        factory.close();
    }
}