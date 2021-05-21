package databaseLayer;

import java.sql.ResultSet;
import java.sql.SQLException;

import modelLayer.Organization;

/**
 * This interface defines all the methods for the Organization Data Access Object
 * @author Group 1 dmai0920
 */
public interface OrganizationDBIF
{
    /**
     * This method goes in the Organization relation and finds the Organization
     * with the given id
     * 
     * @param id - of the organization
     * @return organization
     */
    Organization getOrganizationByID(int id) throws SQLException;

    /**
     * Builds a Java Object from database information
     * @param resultSet
     */
    Organization buildObject(ResultSet resultSet) throws SQLException;
}
