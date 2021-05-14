package databaseLayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelLayer.LogEntry;
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
	//FIXME Martin: Join tables
	private OrganizationDBIF organizationDB = new OrganizationDB();
	
	private static final String SELECT_USER_BY_EMAIL_AND_PASSWORD = String.format("SELECT * FROM User WHERE email = ? AND password = ?");
	private PreparedStatement sqlSelectByEmailAndPassword;
	
	private static final String SELECT_USERTYPE_BY_ID = String.format("SELECT * FROM UserType WHERE id = ?");
	private PreparedStatement sqlSelectUserTypeByID;
	
	public UserDB() throws SQLException
	{
		//TODO REMOVE COMMENTING nad maybe try catch the preparing statements
		//connection = DBConnection.getInstance().getConnection();
		//sqlSelectByEmailAndPassword = connection.prepareStatement(SELECT_USER_BY_EMAIL_AND_PASSWORD);
		//sqlSelectYSerTypeByID = connection.prepareStatement(SELECT_USERTYPE_BY_ID);
	}

	@Override
	public User getUser(String email, String password) throws SQLException
	{
		User user = null;
		
		sqlSelectByEmailAndPassword.setString(1,  email);
		sqlSelectByEmailAndPassword.setString(2,  password);
		
		ResultSet resultSet = sqlSelectByEmailAndPassword.executeQuery();
		
		if(resultSet.next())
		{
			user = buildObject(resultSet);
		}
		
		return user;
	}

	/**
	 * Builds a Java Object from database information
	 * @param resultSet
	 */
	private User buildObject(ResultSet resultSet) throws SQLException
	{
		sqlSelectUserTypeByID.setInt(1, resultSet.getInt("type_id"));
		ResultSet rs = sqlSelectUserTypeByID.executeQuery();
		UserType userType = UserType.valueOf(rs.getString("type"));
		
		Organization organization = organizationDB.getOrganizationByID(resultSet.getInt("organisation_id"));
		
		return new User(resultSet.getInt("id"), (resultSet.getString("first_name") + resultSet.getString("last_name")), resultSet.getString("email"), resultSet.getString("phone"), resultSet.getString("position"), userType, organization);
	}
}
