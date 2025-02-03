
package TablePerClassInheritance;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class InserData {

    public static void main(String[] args) {

        Configuration cfg = new Configuration()
                .configure()
                .addAnnotatedClass(Vehicle.class)
                .addAnnotatedClass(Car.class)
                .addAnnotatedClass(Bike.class);

        StandardServiceRegistry reg = new StandardServiceRegistryBuilder()
                .applySettings(cfg.getProperties())
                .build();

        SessionFactory factory = cfg.buildSessionFactory(reg);

        Session session = factory.openSession();

        session.beginTransaction();

        // Create and persist Car object within the session
        Car car1 = new Car();
        
        car1.setBrand("Ferrari");
        car1.setNumberOfDoors(4);
        session.persist(car1);

        Bike bike1 = new Bike();
        
        bike1.setBrand("Royal Enfield");
        bike1.setBikeType("Sports");
        session.persist(bike1);

        session.getTransaction().commit();

        System.out.println("Data Saved Successfully........!");

        session.close();
        factory.close();
    }
}