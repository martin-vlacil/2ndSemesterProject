package controlLayer;

import databaseLayer.UserDB;
import databaseLayer.UserDBIF;
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
	
	public User getUser(String email, String password)
	{
		User user = null;
		
		//TODO - write method
		
		return user;
	}
}
