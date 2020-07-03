package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	//make static because it will belong to the class instead of the instance; there will only be one instance of this 
	//connection that will be shared throughout the whole application
	private final static String URL = "jdbc:mysql://localhost:3306/gym";
	private final static String USERNAME = "root";
	private final static String PASSWORD = "MyS_20-Db";
	private static Connection connection;
	private static DBConnection instance;
	
	//make this private so there's only one instance of this class
	private DBConnection(Connection connection) {
		this.connection = connection;
	}
	
	//create method that allows you to get the method you want to use
	public static Connection getConnection() {
		if (instance == null) {
			try {
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				instance = new DBConnection(connection);
				System.out.println("Connection successful!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
		return DBConnection.connection;
}
}
