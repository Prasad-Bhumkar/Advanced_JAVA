package ContainmentByHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class UpdateStudent {

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
        
        int studID = 1;
        Student student = session.get(Student.class, studID); // Get the student with ID 1

        if (student != null) {
        	
        	System.out.println("updating student.....");
            
        	student.setName("Ravi");
        	
        	Address add = student.getAddress();
        	add.setStreet("Vaiduwadi");
        	add.setCity("pune");
        	add.setState("MH");

            session.update(student); 
            transaction.commit();

            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Student with ID 1 not found.");
        }

        session.close();
        factory.close();
    }
}