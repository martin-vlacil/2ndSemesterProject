package databaseLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelLayer.Organization;
import modelLayer.User;
import modelLayer.User.UserType;

/**
 * @author Group 1 dmai0920
 * This is a database class for User, the handles its persistence, it is responsible for finding, updating, deleting,
 * and inserting to the database
 */
public class UserDB implements UserDBIF
{
	private Connection connection;

	private OrganizationDBIF organizationDB = new OrganizationDB();
	
	private static final String SELECT_USER_BY_EMAIL_AND_PASSWORD = String.format("SELECT * FROM [User] WHERE email = ? AND password = ?");
	private PreparedStatement sqlSelectByEmailAndPassword;
	
	private static final String SELECT_USERTYPE_BY_ID = String.format("SELECT * FROM UserType WHERE id = ?");
	private PreparedStatement sqlSelectUserTypeByID;
	
	private static final String SELECT_USER_BY_ID = String.format("SELECT * FROM [User] WHERE id = ?");
	private PreparedStatement sqlSelectUserByID;
	
	public UserDB() throws SQLException
	{
		connection = DBConnection.getInstance().getConnection();
		sqlSelectByEmailAndPassword = connection.prepareStatement(SELECT_USER_BY_EMAIL_AND_PASSWORD);
		sqlSelectUserTypeByID = connection.prepareStatement(SELECT_USERTYPE_BY_ID);
		sqlSelectUserByID = connection.prepareStatement(SELECT_USER_BY_ID);
	}

	@Override
	public User getUser(String email, String password) throws SQLException
	{
		User user = null;
		
		sqlSelectByEmailAndPassword.setString(1, email);
		sqlSelectByEmailAndPassword.setString(2, password);
		
		ResultSet resultSet = sqlSelectByEmailAndPassword.executeQuery();
		
		if(resultSet.next())
		{
			user = buildObject(resultSet);
		}
		
		return user;
	}
	
	public User getUserByID(int id) throws SQLException
	{
		User user = null;
		
		sqlSelectUserByID.setInt(1, id);
		
		ResultSet rs = sqlSelectUserByID.executeQuery();
		
		if(rs.next())
		{
			user = buildObject(rs);
		}
		
		return user;
	}

	public User buildObject(ResultSet rs) throws SQLException
	{
		sqlSelectUserTypeByID.setInt(1, rs.getInt("type_id"));
		ResultSet userTypeRs = sqlSelectUserTypeByID.executeQuery();
		UserType userType = UserType.DEFAULT;
		if(userTypeRs.next())
		{
			userType = UserType.valueOf(userTypeRs.getString("type").toUpperCase());
		}
		
		Organization organization = organizationDB.getOrganizationByID(rs.getInt("organisation_id"));
		
		return new User(rs.getInt("id"), (rs.getString("first_name") + " " +  rs.getString("last_name")), rs.getString("email"), rs.getString("phone"), rs.getString("position"), userType, organization);
	}
}
