package SingleTableInheritance;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("developer")
public class Developer extends Employee{

	
	private double nightAllowance;

	public double getNightAllowance() {
		return nightAllowance;
	}

	public void setNightAllowance(double nightAllowance) {
		this.nightAllowance = nightAllowance;
	}
	
	
	
}
