package ContainmentByHibernate;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

	
	private String street;
	private String City;
	private String State;
	@Override
	public String toString() {
		return "Address [street=" + street + ", City=" + City + ", State=" + State + "]";
	}
	public Address(String street, String city, String state) {
		super();
		this.street = street;
		City = city;
		State = state;
	}
	public Address() {
		super();
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	
	
	
}
