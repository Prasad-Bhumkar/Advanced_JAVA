package ManyToOne;

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

        Department dept1 = new Department();
        dept1.setName("Computer Science");
        session.persist(dept1);

        Employee emp1 = new Employee();
        emp1.setName("Alice");
        emp1.setDepartment(dept1);
        session.persist(emp1);

        Employee emp2 = new Employee();
        emp2.setName("Bob");
        emp2.setDepartment(dept1);
        session.persist(emp2);

        transaction.commit();
        System.out.println("Department and employees saved successfully.");

        Employee retrievedEmp = session.get(Employee.class, emp1.getId());
        System.out.println("Retrieved Employee: " + retrievedEmp);


        Department retrievedDept = session.get(Department.class, dept1.getId());
        System.out.println("Retrieved department: "+retrievedDept);


        session.close();
        factory.close();
    }
}