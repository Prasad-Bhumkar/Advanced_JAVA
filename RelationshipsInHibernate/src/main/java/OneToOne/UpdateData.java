package OneToOne;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class UpdateData {

	
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
				
				//create variables to be used with hql and set values to them
				String newName="Ravi Bade";
				long personID=1;
				
				//use hql to update the person name
				String hql = "UPDATE Person p SET p.name = :newName WHERE p.id = :personID";
				
				Query query = session.createQuery(hql);
				
				query.setParameter("newName", newName);
				query.setParameter("personID", personID);
				
				//result
				int result = query.executeUpdate();
				
				transaction.commit();
				session.close();
				
				System.out.println("Updated "+result+"person(s) with ID : "+personID);
	}
}
