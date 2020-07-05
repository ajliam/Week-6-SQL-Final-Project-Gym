package entity;

public class Classes {
	private int classId;
	private String className;
	private String classDate;
	private int gymId;
	private int trainerId;
	private String startTime;
	private int classLength;
	
	
	public Classes(int classId, String className, String classDate, int gymId, int trainerId, String startTime, int classLength) {
		this.setClassId(classId);
		this.setClassName(className);
		this.setClassDate(classDate);
		this.setGymId(gymId);
		this.setTrainerId(trainerId);
		this.setStartTime(startTime);
		this.setClassLength(classLength);
	}

	public Classes(int classID2) {
		this.setClassId(classID2);
	}

	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}

	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}

	
	public String getClassDate() {
		return classDate;
	}
	public void setClassDate(String classDate) {
		this.classDate = classDate;
	}
	
	
	public int getGymId() {
		return gymId;
	}
	public void setGymId(int gymId) {
		this.gymId = gymId;
	}	
	
	
	public int getTrainerId() {
		return trainerId;
	}
	public void setTrainerId(int trainerId) {
		this.trainerId = trainerId;
	}	
	
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}	
	
	
	public int getClassLegnth() {
		return classLength;
	}
	public void setClassLength(int classLength) {
		this.classLength = classLength;
	}
}
