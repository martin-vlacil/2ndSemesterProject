package databaseLayer;

import java.sql.*;

/**
 * This class is the Database Connection class used in the "IHND" System.
 * 
 * Uses Singleton pattern
 * 
 * @author Group1 dmai0920
 */
public class DBConnection
{
    // All fields
    private Connection connection = null;
    private static DBConnection dbConnection;

    // These are the Credentials used to connect to the database
    private static final String DATABASE_NAME = "dmai0920_1086320";
    private static final String SERVER_ADDRESS = "hildur.ucn.dk";
    private static final int SERVER_PORT = 1433;
    private static final String USER_NAME = "dmai0920_1086320";
    private static final String PASSWORD = "Password1!";

    /**
     * Constructor establishing the connection. Throws an exception if the
     * connection fails
     */
    private DBConnection()
    {
        String connectionString = String.format("jdbc:sqlserver://%s:%d;databaseName=%s;user=%s;password=%s",SERVER_ADDRESS, SERVER_PORT, DATABASE_NAME, USER_NAME, PASSWORD);
        try
        {
            connection = DriverManager.getConnection(connectionString);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Getter for the single instance of the connection to the database
     * 
     * @return
     */
    public static DBConnection getInstance()
    {
        if (dbConnection == null)
        {
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }

    /**
     * Methods used for transactions
     * 
     * @throws SQLException
     */
    public void startTransaction() throws SQLException
    {
        connection.setAutoCommit(false);
    }

    public void commitTransaction() throws SQLException
    {
        connection.commit();
        connection.setAutoCommit(true);
    }

    public void rollbackTransaction() throws SQLException
    {
        connection.rollback();
        connection.setAutoCommit(true);
    }

    /**
     * Gather method for the connection
     * 
     * @return
     */
    public Connection getConnection()
    {
        return connection;
    }

    /**
     * A method to close the connection to the database
     */
    public void disconnect()
    {
        try
        {
            connection.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}