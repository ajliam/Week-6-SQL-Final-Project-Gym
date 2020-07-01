package dao;

import java.sql.Connection;

public class ClassesDao {

	private Connection connection;
	
	public ClassesDao() {
		connection = DBConnection.getConnection();

	}
}
