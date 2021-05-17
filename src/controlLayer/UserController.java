package controlLayer;

import java.sql.SQLException;

import databaseLayer.*;
import modelLayer.User;

/**
 * @author Group1 dmai0920
 * A controller for the User objects, handling their logic in
 * the system.
 */
public class UserController
{
	private UserDBIF userDB;

	public UserController() throws SQLException
	{
		userDB = new UserDB();
	}
	
	/**
	 * This method takes an email, finds the user in the database and checks if the password matches
	 * @param email, password
	 * @return
	 * @throws SQLException 
	 */
	public User getUser(String email, String password) throws SQLException
	{
		return userDB.getUser(email, password);
	}
}
