package OneToOne;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class AllOperations {

    public static void main(String[] args) {
        // Create SessionFactory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Passport.class)
                .buildSessionFactory();

        Scanner sc = new Scanner(System.in);
        boolean continueLoop = true;

        do {
            System.out.println("\nChoose Following Options :");
            System.out.println("1. Add New Data");
            System.out.println("2. Update Person Name");
            System.out.println("3. Update Passport No.");
            System.out.println("4. Delete Data ");
            System.out.println("5. Display Data");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Enter Name: ");
                String name = sc.next();
                System.out.print("Enter Passport No.: ");
                String passport = sc.next();
                savePersonWithPassport(factory, name, passport);
            } else if (choice == 2) {
                System.out.print("Enter Person Id: ");
                long id = sc.nextLong();
                System.out.print("Enter New Name: ");
                String newName = sc.next();
                updatePersonName(factory, id, newName);
            } else if (choice == 3) {
                System.out.print("Enter Person Id: ");
                long id = sc.nextLong();
                System.out.print("Enter Passport No.: ");
                String newPassportNo = sc.next();
                updatePassportNumber(factory, id, newPassportNo);
            } else if (choice == 4) {
                System.out.print("Enter Person Id: ");
                long id = sc.nextLong();
                deletePersonAndPassport(factory, id); 
            } else if (choice == 5) {
                retrieveAllPersonsWithPassports(factory);
            } else if (choice == 6) {
                System.out.println("Exiting program.");
                continueLoop = false; 
            } else {
                System.out.println("Invalid Choice. Please try again.");
            }

        } while (continueLoop);

        factory.close();
        sc.close();
    }

    // Method to save a person with passport
    private static void savePersonWithPassport(SessionFactory factory, String name, String passportNo) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();

            Passport passport = new Passport();
            passport.setPassportNumber(passportNo);

            Person person = new Person();
            person.setName(name);
            person.setPassport(passport);

            session.save(person); 

            tx.commit();
            System.out.println("Person and Passport saved successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to update person's name based on their ID
    private static void updatePersonName(SessionFactory factory, long personId, String newName) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();

            String hql = "UPDATE Person p SET p.name = :newName WHERE p.id = :personId";
            Query query = session.createQuery(hql);
            query.setParameter("newName", newName);
            query.setParameter("personId", personId);

            int result = query.executeUpdate();

            tx.commit();
            System.out.println("Updated " + result + " person(s) with ID: " + personId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to update passport number based on person's ID
    private static void updatePassportNumber(SessionFactory factory, long personId, String newPassportNumber) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();

            String hql = "UPDATE Passport p SET p.passportNumber = :newPassportNumber WHERE p.id = (SELECT person.passport.id FROM Person person WHERE person.id = :personId)";
            Query query = session.createQuery(hql);
            query.setParameter("newPassportNumber", newPassportNumber);
            query.setParameter("personId", personId);

            int result = query.executeUpdate();

            tx.commit();
            System.out.println("Updated " + result + " passport(s) for Person ID: " + personId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //method to retrieve all data
    private static void retrieveAllPersonsWithPassports(SessionFactory factory) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();

            String hql = "SELECT p FROM Person p LEFT JOIN FETCH p.passport";
            Query<Person> query = session.createQuery(hql, Person.class);
            List<Person> persons = query.getResultList();

            for (Person person : persons) {
                System.out.println("Person Name: " + person.getName());
                if (person.getPassport() != null) {
                    System.out.println("Passport Number: " + person.getPassport().getPassportNumber());
                } else {
                    System.out.println("No Passport found.");
                }
                System.out.println("-------------------------");
            }

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    private static void deletePersonAndPassport(SessionFactory factory, long personId) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();

            // Retrieve the Person object
            Person person = session.get(Person.class, personId);

            if (person != null) {
                // Delete the person 
                session.delete(person); 

                tx.commit();
                System.out.println("Person with ID: " + personId + " and associated Passport deleted successfully.");
            } else {
                System.out.println("Person with ID: " + personId + " not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

    