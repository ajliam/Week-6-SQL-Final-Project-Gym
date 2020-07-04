package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Classes;

public class ClassesDao {

	private Connection connection;
	private final String GET_CLASSES_QUERY = "SELECT * FROM classes";
	private final String ADD_NEW_CLASS_QUERY = "INSERT INTO classes(class_Name, class_Date, gym_ID, trainer_ID, start_Time, class_Length) VALUES (?,?,?,?,?,?)";
	private final String DELETE_CLASS_BY_ID_QUERY = "DELETE FROM classes WHERE class_ID = ?";
	private final String UPDATE_CLASS_BY_ID_QUERY = "UPDATE classes SET class_Name = ?, class_Date = ?, gym_ID = ?, trainer_ID = ?, start_Time = ?, class_Length = ? WHERE class_ID = ?";
	private final String GET_TRAINER_NAME_QUERY = "SELECT first_Name, last_Name FROM trainer WHERE trainer_ID = ?";
	public ClassesDao() {
		connection = DBConnection.getConnection();
	}
	

	public List<Classes> Classes() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_CLASSES_QUERY).executeQuery();
		List<Classes> classes = new ArrayList<Classes>();
		
		while (rs.next()) {
			classes.add(populateClasses(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getInt(7)));
			
		}
		return classes;
	}

	private Classes populateClasses(int classID, String className, String classDate, int gymID, int trainerID, String startTime, int classLength) {
		return new Classes(classID, className, classDate, gymID, trainerID, startTime, classLength);
		
	}
	
	public String trainerName(int trainerId) throws SQLException {
		String firstName = "";
		String lastName = "";
		PreparedStatement ps = connection.prepareStatement(GET_TRAINER_NAME_QUERY);
		ps.setInt(1, trainerId);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			firstName = rs.getString(1); 
			lastName = rs.getString(2);
		}
		rs.close();
		return (firstName + " " + lastName); 
	}
	
	
}
