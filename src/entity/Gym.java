package entity;

import java.util.List;

public class Gym {

	private int gymId;
	private String gymName;
	private String phoneNumber;
	private String address;
	private String city;
	private String state;
	private int zipCode;
	private List<Membership> members;
	
	public Gym(int gymId, String gymName, String phoneNumber, String address, String city, String state, int zipCode, List<Membership> members) {
		this.setGymId(gymId);
		this.setGymName(gymName);
		this.setPhoneNumber(phoneNumber);
		this.setAddress(address);
		this.setCity(city);
		this.setState(state);
		this.setZipCode(zipCode);
		this.setMembers(members);
	}

	public int getGymId() {
		return gymId;
	}

	public void setGymId(int gymId) {
		this.gymId = gymId;
	}

	public String getGymName() {
		return gymName;
	}

	public void setGymName(String gymName) {
		this.gymName = gymName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	
	public List<Membership> getMembers() {
		return members;
	}
	
	public void setMembers(List<Membership> members) {
		this.members = members;
	}
}
