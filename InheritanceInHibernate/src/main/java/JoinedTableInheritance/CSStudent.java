package JoinedTableInheritance;

import javax.persistence.Entity;

@Entity
public class CSStudent extends Student {

    private String specialization;

    public CSStudent() {
    }

    public CSStudent(int id, String name, String course, String specialization) {
        super(id, name, course);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "CSStudent [id=" + getId() + ", name=" + getName() + ", course=" + getCourse() + ", specialization="
                + specialization + "]";
    }
}

