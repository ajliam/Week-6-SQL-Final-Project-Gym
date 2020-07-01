package entity;

public class Trainer {

	private int trainerId;
	private String firstName;
	private String lastName;
	
	public Trainer(int trainerId, String firstName, String lastName) {
		this.setTrainerId(trainerId);
		this.setFirstName(firstName);
		this.setLastName(lastName);
	}

	public int getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(int trainerId) {
		this.trainerId = trainerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
