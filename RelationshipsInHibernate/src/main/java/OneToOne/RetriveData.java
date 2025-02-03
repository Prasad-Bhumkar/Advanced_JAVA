package OneToOne;


import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RetriveData {

	
	public static void main(String[] args) {
		
		
		//create sessionfactory
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Person.class)
				.addAnnotatedClass(Passport.class)
				.buildSessionFactory();
		
		//create a session
		
		Session session = factory.openSession();
		
		//create transaction and begin transaction
		
		Transaction transaction = session.beginTransaction();
		
		//use hql to retrieve all person instances
		
		String hql = "SELECT p FROM Person p LEFT JOIN FETCH p.passport";
		
		Query query = session.createQuery(hql,Person.class);
		
		List<Person> persons = query.getResultList();
		
		//display the results
		
		for (Person person : persons) {
			System.out.println("person name : "+person.getName());
			
			if (person.getPassport() != null) {
				System.out.println("Passport Number : "+person.getPassport().getPassportNumber());
			} else {
				System.out.println("No Passport found.....!");
			}
			System.out.println("-----------------------------");
		}
		
		//commit the transaction and close the session
		transaction.commit();
		session.close();
	}
}
