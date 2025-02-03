package JoinedTableInheritance;

import javax.persistence.Entity;

@Entity
public class MathStudent extends Student {

    private double gpa;

    public MathStudent() {
    }

    public MathStudent(int id, String name, String course, double gpa) {
        super(id, name, course);
        this.gpa = gpa;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "MathStudent [id=" + getId() + ", name=" + getName() + ", course=" + getCourse() + ", gpa=" + gpa
                + "]";
    }
}

