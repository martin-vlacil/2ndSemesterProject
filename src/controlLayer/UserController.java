package controlLayer;

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

	public UserController()
	{
		userDB = new UserDB();
	}
	
	/**
	 * This method takes an email, finds the user in the database and checks if the password matches
	 * @param email, password
	 * @return
	 */
	public User getUser(String email, String password)
	{
		User user = null;
		
		//TODO - write method
		
		return user;
	}
}
