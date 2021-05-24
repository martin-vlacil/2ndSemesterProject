package databaseLayer;

import java.sql.*;

import modelLayer.Organization;

/**
 * @author Group 1 dmai0920 
 * This is a database class for Organizations, 
 * it is responsible for finding, updating, deleting,
 * and inserting to the database
 */
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

    /**
     * Methods from the interface
     */
    @Override
    public Organization getOrganizationByID(int id) throws SQLException
    {
        Organization organization = null;

        sqlSelectByID.setInt(1, id);

        ResultSet resultSet = sqlSelectByID.executeQuery();

        if (resultSet.next())
        {
            organization = buildObject(resultSet);
        }

        return organization;
    }

    @Override
    public Organization buildObject(ResultSet resultSet) throws SQLException
    {
        return new Organization(resultSet.getInt("id"), resultSet.getString("name"));
    }
}