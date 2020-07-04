package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.Trainer;

public class TrainerDao {
	
	private Connection connection;
	private final String GET_TRAINER_QUERY = "SELECT * FROM trainer";
	private final String ADD_NEW_TRAINER_QUERY = "INSERT INTO trainer(first_Name, last_Name, gym_ID) VALUES (?,?,?)";
	private final String DELETE_TRAINER_BY_ID_QUERY = "DELETE FROM trainer WHERE trainer_ID = ?";
	private final String UPDATE_TRAINER_BY_ID_QUERY = "UPDATE trainer SET first_Name = ?, last_Name = ?, gym_ID = ? WHERE trainer_ID = ?";
	private final String GET_TRAINER_NAME_QUERY = "SELECT first_Name, last_Name FROM trainer WHERE trainer_ID = ?";
	public TrainerDao() {
		connection = DBConnection.getConnection();
	}

	public List<Trainer> Trainers() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_TRAINER_QUERY).executeQuery();
		List<Trainer> trainers = new ArrayList<Trainer>();
		
		while (rs.next()) {
			trainers.add(populateTrainer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
			
		}
		rs.close();
		return trainers;
	}

	private Trainer populateTrainer(int trainerID, String firstName, String lastName, int gymID) {
		return new Trainer(trainerID, firstName, lastName, gymID);
		
	}

	

	public void addNewTrainer(String first_Name, String last_Name, int gym_ID) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(ADD_NEW_TRAINER_QUERY);
		ps.setString(1, first_Name);
		ps.setString(2, last_Name);
		ps.setInt(3, gym_ID);
		ps.executeUpdate();
	}
	
	public void deleteTrainerById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_TRAINER_BY_ID_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
	}
	


	public void updateTrainer(int instructorID, String firstName, String lastName, int gymID) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_TRAINER_BY_ID_QUERY);
		ps.setString(1, firstName);
		ps.setString(2,lastName);
		ps.setInt(3, gymID);
		ps.setInt(4, instructorID);
		ps.executeUpdate();
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
