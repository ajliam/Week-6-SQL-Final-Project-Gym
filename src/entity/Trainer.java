package entity;

public class Trainer {

	private int trainerId;
	private String firstName;
	private String lastName;
	private int gym_ID;
	
	public Trainer(int trainerId, String firstName, String lastName, int gym_ID) {
		this.setTrainerId(trainerId);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setGym_ID(gym_ID);
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
	
	public int getGym_ID() {
		return gym_ID;
	}
	
	private void setGym_ID(int gym_ID) {
		this.gym_ID =  gym_ID;	
	}
	
	
}
