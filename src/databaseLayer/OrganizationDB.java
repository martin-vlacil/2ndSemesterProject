package databaseLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelLayer.Organization;
import modelLayer.User;
import modelLayer.User.UserType;

public class OrganizationDB implements OrganizationDBIF
{
	private Connection connection;
	
	private static final String SELECT_BY_ID = String.format("SELECT * FROM Organisation WHERE id = ?");
	private PreparedStatement sqlSelectByID;

	public OrganizationDB() throws SQLException
	{
		connection = DBConnection.getInstance().getConnection();
		sqlSelectByID = connection.prepareStatement(SELECT_BY_ID);
	}

	@Override
	public Organization getOrganizationByID(int id) throws SQLException
	{
		Organization organization = null;
		
		sqlSelectByID.setInt(1,  id);
		
		ResultSet resultSet = sqlSelectByID.executeQuery();
		
		if(resultSet.next())
		{
			organization = buildObject(resultSet);
		}
		
		return organization;
	}

	/**
	 * Builds a Java Object from database information
	 * @param resultSet
	 */
	private Organization buildObject(ResultSet resultSet) throws SQLException
	{
		return new Organization(resultSet.getInt("id"), resultSet.getString("name"));
	}
}