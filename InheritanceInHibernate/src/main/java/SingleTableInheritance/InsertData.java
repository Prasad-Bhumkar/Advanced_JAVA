package SingleTableInheritance;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class InsertData {
	
	public static void main(String[] args) {
		
		Configuration cfg = new Configuration()
				.configure()
				.addAnnotatedClass(Employee.class)
				.addAnnotatedClass(Developer.class)
				.addAnnotatedClass(Manager.class);
		
		ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
		
		SessionFactory factory = cfg.buildSessionFactory(reg);
		
		Session session = factory.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		Developer e1 = new Developer();
		e1.setId(1);
		e1.setName("prasad");
		e1.setSalary(570000);
		e1.setNightAllowance(5000);
		
		Manager e2 = new Manager();
		e2.setId(2);
		e2.setName("Ravichandra");
		e2.setSalary(600000);
		e2.setInccentive(10000);
		
		session.save(e1);
		session.save(e2);
		
		transaction.commit();
		System.out.println("employee saved successfully....!");
	}

}
