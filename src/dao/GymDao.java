package dao;

import java.sql.Connection;

public class GymDao {

	private Connection connection;
	
	public GymDao() {
		connection = DBConnection.getConnection();

	}

}
