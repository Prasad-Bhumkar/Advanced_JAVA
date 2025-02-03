// Employee.java
package ManyToOne;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate ID
    private Long id; // Use Long for IDs
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id") // Foreign key column
    private Department department;


    public Employee() {} // Important: Add a no-argument constructor

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department=" + (department != null ? department.getName() : "null") +
                '}';
    }
}
