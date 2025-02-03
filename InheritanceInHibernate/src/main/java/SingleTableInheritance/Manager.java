package SingleTableInheritance;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("manager")
public class Manager extends Employee{

	
	private double incentive;

	public double getInccentive() {
		return incentive;
	}

	public void setInccentive(double inccentive) {
		this.incentive = inccentive;
	}
	
	
}
