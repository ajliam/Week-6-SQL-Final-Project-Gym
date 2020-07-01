package entity;

public class Classes {
	
	private int classId;
	private String className;
	private String dateTime;
	//private List<Trainer> trainers;
	
	public Classes(int classId, String className, String dateTime) {
		this.setClassId(classId);
		this.setClassName(className);
		this.setDateTime(dateTime);
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

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

}
