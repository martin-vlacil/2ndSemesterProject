package databaseLayer;

import java.sql.SQLException;

import modelLayer.User;

/**
 * This interface defines all the methods for the User Data Access Object
 * @author Group 1 dmai0920
 */
public interface UserDBIF
{
	/**
	 * This method goes in the User relation and finds the User with the given email and checks if the given 
	 * password matches
	 * @param email, password
	 * @return the logged user
	 */
	User getUser(String email, String password) throws SQLException;
}
