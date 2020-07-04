package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Gym;
import entity.Membership;

public class MembershipDao {
	
	private Connection connection;
	private final String GET_MEMBERS_BY_GYM_ID_QUERY = "SELECT * FROM membership WHERE gym_id = ?";
	private final String GET_MEMBERS_QUERY = "SELECT * FROM membership";
	private final String GET_MEMBER_BY_MEMBER_ID_QUERY = "SELECT * FROM membership WHERE member_id = ?";
	private final String CREATE_NEW_MEMBER_QUERY = "INSERT INTO membership(first_name, last_name, phone_Number, birth_Date, gym_id) VALUES(?,?,?,?,?)";
	private final String UPDATE_MEMBER_BY_ID_QUERY = "UPDATE membership SET first_Name = ?, last_Name = ?, phone_Number = ?, birth_Date = ? WHERE member_ID = ?";
	private final String DELETE_MEMBER_BY_ID_QUERY = "DELETE FROM membership WHERE member_id = ?";
	
	public MembershipDao() {
		connection = DBConnection.getConnection();

	}
	
	public List<Membership> getMembersByGymId(int gymId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_MEMBERS_BY_GYM_ID_QUERY);
		ps.setInt(1, gymId);
		ResultSet rs = ps.executeQuery();
		List<Membership> members = new ArrayList<Membership>();
		
		while (rs.next()) {
			members.add(new Membership(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
		}
		return members;
	}
	
	public List<Membership> getAllMembers() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_MEMBERS_QUERY).executeQuery();
//		PreparedStatement ps = connection.prepareStatement(GET_MEMBERS_QUERY);
//		ResultSet rs = ps.executeQuery();
		List<Membership> members = new ArrayList<Membership>();
		
		while (rs.next()) {
			members.add(populateMember(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
		}
		return members;
	}
	
	public void createNewMember(String firstName, String lastName, String phoneNumber, String birthDate, int gymId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_MEMBER_QUERY);
		ps.setString(1, firstName);
		ps.setString(2, lastName);
		ps.setString(3, phoneNumber);
		ps.setString(4, birthDate);
		ps.setInt(5, gymId);
		ps.executeUpdate();
	}
	
	public Membership getMemberByID(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_MEMBER_BY_MEMBER_ID_QUERY);
		ps.setInt(1,  id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return populateMember(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)); 
	}
	
	public void updateMember(String firstName, String lastName, String phoneNumber, String birthDate, int memberId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_MEMBER_BY_ID_QUERY);
		ps.setString(1, firstName);
		ps.setString(2,lastName);
		ps.setString(3, phoneNumber);
		ps.setString(4, birthDate);
		ps.setInt(5, memberId);
		ps.executeUpdate();
	}
	
	public void deleteMemberById(int memberId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_MEMBER_BY_ID_QUERY);
		ps.setInt(1, memberId);
		ps.executeUpdate();
		}
	
	private Membership populateMember(int memberId, String firstName, String lastName, String phoneNumber, String birthDate) throws SQLException {
		return new Membership(memberId, firstName, lastName, phoneNumber, birthDate);
	}
	
}