package dao;

import java.sql.Connection;

public class MembershipDao {
	
	private Connection connection;
	
	public MembershipDao() {
		connection = DBConnection.getConnection();

	}

}
