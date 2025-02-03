// MainApp.java (WITHOUT try-catch-finally - NOT RECOMMENDED)
package ManyToMany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MainApp {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Course course1 = new Course();
        course1.setName("Java Programming");
        session.persist(course1);

        Course course2 = new Course();
        course2.setName("Data Structures");
        session.persist(course2);

        Student student1 = new Student();
        student1.setName("Alice");
        student1.getCourses().add(course1);
        student1.getCourses().add(course2);
        session.persist(student1);

        Student student2 = new Student();
        student2.setName("Bob");
        student2.getCourses().add(course1);
        session.persist(student2);

        course1.getStudents().add(student1);
        course1.getStudents().add(student2);
        course2.getStudents().add(student1);


        transaction.commit();
        System.out.println("ManyToMany bidirectional example is successfully executed");

        Student retrievedStudent = session.get(Student.class, student1.getId());
        System.out.println("Retrieved Student: " + retrievedStudent);
        for (Course c : retrievedStudent.getCourses()) {
            System.out.println("Student is enrolled in course :" + c.getName());
        }

        Course retrievedCourse = session.get(Course.class, course1.getId());
        System.out.println("Retrieved Course: " + retrievedCourse);
        for (Student s : retrievedCourse.getStudents()) {
            System.out.println("Course has student :" + s.getName());
        }

        session.close();
        factory.close();
    }
}