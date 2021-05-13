package databaseLayer;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modelLayer.User;

/**
 * @author Group 1 dmai0920
 * This is a database class for User, the handles its persistence, it is responsible for finding, updating, deleting,
 * and inserting to the database
 */
public class UserDB implements UserDBIF
{

	private static final String SELECT_USER_BY_EMAIL_AND_PASSWORD = String.format("SELECT * FROM User WHERE email = ? AND password = ?");
	private PreparedStatement sqlSelectByEmailAndPassword;
	
	public UserDB() throws SQLException
	{
		//TODO REMOVE COMMENTING
		//connection = DBConnection.getInstance().getConnection();
		//sqlSelectByEmailAndPassword = connection.prepareStatement(SELECT_USER_BY_EMAIL_AND_PASSWORD);
	}

	@Override
	public User getUser(String email, String password)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
