package dao;

import java.sql.Connection;

public class TrainerDao {
	
	private Connection connection;
	
	public TrainerDao() {
		connection = DBConnection.getConnection();

	}

}
