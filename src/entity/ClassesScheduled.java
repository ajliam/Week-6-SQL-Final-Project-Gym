package entity;

import java.util.List;

public class ClassesScheduled {
	private int scheduleID;
	private int memberID;
	private int classID;

	public ClassesScheduled(int scheduleID, int memberID, int classID) {
		this.scheduleID = scheduleID;
		this.memberID = memberID;
		this.classID = classID;
	}

	public int getScheduleID() {
		return scheduleID;
	}

	public void setScheduleID(int scheduleID) {
		this.scheduleID = scheduleID;
	}

	public int getMemberID() {
		return memberID;
	}

	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}

	public int getClassID() {
		return classID;
	}

	public void setClassID(int classID) {
		this.classID = classID;
	}
	
	
	
	
	
	
	
	
	
	
/*	
	private int scheduleId;
	private List<Membership> members;
	private List<Classes> fitnessClass;
	//Is a list of classes needed? 
	
	public ClassesScheduled(int scheduleId, List<Membership> members, List<Classes> fitnessClass) {
		this.setScheduleId(scheduleId);
		this.setMembers(members);
		this.setFitnessClass(fitnessClass);
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public List<Membership> getMembers() {
		return members;
	}

	public void setMembers(List<Membership> members) {
		this.members = members;
	}

	public List<Classes> getFitnessClass() {
		return fitnessClass;
	}

	public void setFitnessClass(List<Classes> fitnessClass) {
		this.fitnessClass = fitnessClass;
	}
*/
}
