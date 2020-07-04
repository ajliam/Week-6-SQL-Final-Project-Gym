package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Gym;

public class GymDao {

	private Connection connection;
	private MembershipDao membershipDao;

	private final String GET_GYM_INFO_QUERY = "SELECT * FROM gym";
	private final String GET_GYM_BY_ID_QUERY = "SELECT * FROM gym WHERE gym_id = ?";
	public GymDao() {
		connection = DBConnection.getConnection();
		membershipDao = new MembershipDao();

	}
	
	public List<Gym> getGymInfo() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_GYM_INFO_QUERY).executeQuery();
		List<Gym> gyms = new ArrayList<Gym>();
		
		while (rs.next()) {
			gyms.add(populateGym(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7)));
		}
		return gyms;
	}
	
	public Gym getGymByID(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_GYM_BY_ID_QUERY);
		ps.setInt(1,  id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return populateGym(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
		}

	private Gym populateGym(int gymId, String gymName, String phoneNumber, String address, String city, String state, int zipCode) throws SQLException {
		return new Gym(gymId, gymName, phoneNumber, address, city, state, zipCode, membershipDao.getMembersByGymId(gymId));
	}
	
	public String getGymNameByID(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_GYM_BY_ID_QUERY);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return rs.getString(2);
	}
}
