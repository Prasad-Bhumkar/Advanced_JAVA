package TablePerClassInheritance;

import javax.persistence.Entity;

@Entity
public class Bike extends Vehicle {

    private String bikeType;

    public String getBikeType() {
        return bikeType;
    }

    public void setBikeType(String bikeType) {
        this.bikeType = bikeType;
    }
}