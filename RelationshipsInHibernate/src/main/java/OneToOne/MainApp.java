package OneToOne;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MainApp {

	
	public static void main(String[] args) {
		
		
		//create session factory
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Person.class)
				.addAnnotatedClass(Passport.class)
				.buildSessionFactory();
		
		//create a session
		
		Session session = factory.openSession();
		
		//begin transaction
		
		Transaction transaction = session.beginTransaction();
		
		//create a new passport
		
		Passport passport = new Passport();
		passport.setPassportNumber("PB115245");
		
		//create a new person and associate him with the passport
		
		Person person = new Person();
		person.setName("Prasad Bhumkar");
		person.setPassport(passport);
		
		//save the person (this will also save the associated passport due to cascade type ALL)
		
		session.save(person);
		
		//commit the transaction
		transaction.commit();
		
		System.out.println("Person and Passport saved successfully.......!");
	}
}
