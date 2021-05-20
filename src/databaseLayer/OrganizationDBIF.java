package databaseLayer;

import java.sql.ResultSet;
import java.sql.SQLException;

import modelLayer.Organization;

public interface OrganizationDBIF
{
    /**
     * This method goes in the Organization relation and finds the Organization
     * with the given id
     * 
     * @param id
     * @return organization
     */
    Organization getOrganizationByID(int id) throws SQLException;

    /**
     * Builds a Java Object from database information
     * 
     * @param resultSet
     */
    Organization buildObject(ResultSet resultSet) throws SQLException;
}
