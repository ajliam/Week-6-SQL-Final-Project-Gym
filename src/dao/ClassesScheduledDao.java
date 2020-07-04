package dao;

import java.sql.Connection;

public class ClassesScheduledDao {
	
	private Connection connection;
	
	public ClassesScheduledDao() {
		
		connection = DBConnection.getConnection();

	}

}
