package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Classes;
import entity.ClassesScheduled;


public class ClassesScheduledDao {
	
	private Connection connection;
	private final String GET_CLASSES_SCHEDULED_QUERY = "SELECT * FROM classes_scheduled";
	private final String GET_CLASSES_SCHEDULED_BY_ID_QUERY = "SELECT * FROM classes_scheduled WHERE class_ID = ?";
	private final String GET_CLASSES_SCHEDULED_BY_MEMBER_ID_QUERY = "Select * FROM classes_scheduled WHERE member_ID = ?";
	private final String ADD_NEW_SCHEDULE_QUERY = "INSERT INTO classes_scheduled(member_ID, class_ID) VALUES (?,?)";
	private final String DELETE_SCHEDULE_BY_ID_QUERY = "DELETE FROM classes_scheduled WHERE schedule_ID = ?";
	private final String DELETE_SCHEDULE_BY_CLASS_ID_QUERY = "DELETE FROM classes_scheduled WHERE class_ID = ?";
	private final String DELETE_SCHEDULE_BY_MEMBER_ID_QUERY = "DELETE FROM classes_scheduled WHERE member_ID = ?";
	
	public ClassesScheduledDao() {	
		connection = DBConnection.getConnection();
	}
	
	public List<ClassesScheduled> ClassScheduled() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_CLASSES_SCHEDULED_QUERY).executeQuery();
		List<ClassesScheduled> schedule = new ArrayList<ClassesScheduled>();
		
		while (rs.next()) {
			schedule.add(populateSchedule(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
		}
		return schedule;
	}
	
	public List<ClassesScheduled> ClassScheduledByID(int classID) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_CLASSES_SCHEDULED_BY_ID_QUERY);
		ps.setInt(1, classID);
		ResultSet rs = ps.executeQuery();
		List<ClassesScheduled> scheduleID = new ArrayList<ClassesScheduled>();
		
		while (rs.next()) {
			scheduleID.add(populateSchedule(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
		}
		return scheduleID;
	}
	
	public List<ClassesScheduled> ClassScheduledByMemberID(int memberID) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_CLASSES_SCHEDULED_BY_MEMBER_ID_QUERY);
		ps.setInt(1, memberID);
		ResultSet rs = ps.executeQuery();
		List<ClassesScheduled> scheduleID = new ArrayList<ClassesScheduled>();
		
		while (rs.next()) {
			scheduleID.add(populateSchedule(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
		}
		return scheduleID;
	}
	private ClassesScheduled populateSchedule(int scheduleID, int memberID, int classID) {
		return new ClassesScheduled(scheduleID, memberID, classID);
	}
	
	public void addNewSchedule(int memberID, int classID) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(ADD_NEW_SCHEDULE_QUERY);
		ps.setInt(1, memberID);
		ps.setInt(2,classID);
		ps.executeUpdate();
	}
	
	public void deleteScheduleById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_SCHEDULE_BY_ID_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
	}
	
	public void deleteScheduleByClassId(Integer id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_SCHEDULE_BY_CLASS_ID_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
	}
	
	public void deleteScheduleByMemberId(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_SCHEDULE_BY_MEMBER_ID_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
	}


	

}
