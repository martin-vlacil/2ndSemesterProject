package databaseLayer;

import java.sql.ResultSet;
import java.sql.SQLException;

import modelLayer.User;

/**
 * This interface defines all the methods for the User Data Access Object
 * 
 * @author Group 1 dmai0920
 */
public interface UserDBIF
{
    /**
     * This method goes in the User relation and finds the User with the given
     * email and checks if the given password matches
     * 
     * @param email, password
     * @return the logged user
     */
    User getUser(String email, String password) throws SQLException;

    /**
     * This method is used to create a User object from the databse
     * 
     * @param id of the user
     * @return the created user
     * @throws SQLException
     */
    User getUserByID(int id) throws SQLException;

    /**
     * A helper method for creating the user
     * 
     * @param rs from the database
     * @return the created user
     * @throws SQLException
     */
    User buildObject(ResultSet rs) throws SQLException;
}
