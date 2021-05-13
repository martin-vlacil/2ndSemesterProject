package databaseLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import modelLayer.LogEntry;

public class LogEntryDB implements LogEntryDBIF
{
	private Connection connection;
	
	private static final String INSERT_LOG_ENTRY = String.format("INSERT INTO Log VALUES(?, ?)");
	private PreparedStatement sqlInsertLogEntry;

	private static final String SELECT_LATEST_LOGS = String.format("SELECT TOP ? * FROM Log ORDER BY [date] DESC");
	private PreparedStatement sqlSelectLatestLogs;
	
	private final int SELECT_LATEST_AMOUNT = 25;
	
	public LogEntryDB() throws SQLException
	{
		connection = DBConnection.getInstance().getConnection();
		sqlInsertLogEntry = connection.prepareStatement(INSERT_LOG_ENTRY);
		sqlSelectLatestLogs = connection.prepareStatement(SELECT_LATEST_LOGS);
	}

	@Override
	public boolean create(String action, LocalDateTime time) throws SQLException
	{
		boolean logEntryCreated = false;
		
		sqlInsertLogEntry.setString(1, action);
		sqlInsertLogEntry.setTimestamp(2, Timestamp.valueOf(time));
		
		logEntryCreated = sqlInsertLogEntry.execute(INSERT_LOG_ENTRY);
		
		return logEntryCreated;
	}
	
	@Override
	public LogEntry[] getLogs() throws SQLException
	{
		LogEntry[] logEntries = new LogEntry[SELECT_LATEST_AMOUNT];
		
		sqlSelectLatestLogs.setInt(1, SELECT_LATEST_AMOUNT);
		ResultSet resultSet = sqlSelectLatestLogs.executeQuery();
		
		int index = 0;
		while(resultSet.next())
		{
			logEntries[index] = buildObject(resultSet);
			index++;
		}

		return logEntries;
	}
	
	/**
	 * Builds a Java Object from database information
	 * @param resultSet
	 */
	private LogEntry buildObject(ResultSet resultSet) throws SQLException
	{
		return new LogEntry(resultSet.getString("action"), resultSet.getTimestamp("date").toLocalDateTime());
	}
}



public class DBConnection {

	private DBConnection() throws DataAccessException {
		// Cheat sheet for the printf() method, the format is also used in the
		// String.format() method
		// http://alvinalexander.com/programming/printf-format-cheat-sheet
		String connectionString = String.format("jdbc:sqlserver://%s:%d;databaseName=%s;user=%s;password=%s",
				serverAddress, serverPort, dbName, userName, password);
		try {
			connection = DriverManager.getConnection(connectionString);
		} catch (SQLException e) {
			throw new DataAccessException(String.format("Could not connect to database %s@%s:%d user %s", dbName,
					serverAddress, serverPort, userName), e);
			// System.out.println("Connection string was: " + connectionString.substring(0,
			// connectionString.length() - password.length()) + "....");
			// e.printStackTrace();
		}
	}

	public static synchronized DBConnection getInstance() throws DataAccessException {
		if (dbConnection == null) {
			dbConnection = new DBConnection();
		}
		return dbConnection;
	}

	public void startTransaction() throws DataAccessException {
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException("Could not start transaction.", e);
		}
	}

	public void commitTransaction() throws DataAccessException {
		try {
			try {
				connection.commit();
			} catch (SQLException e) {
				throw e;
				// e.printStackTrace();
			} finally {
				connection.setAutoCommit(true);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not commit transaction", e);
		}
	}

	public void rollbackTransaction() throws DataAccessException {
		try {
			try {
				connection.rollback();
			} catch (SQLException e) {
				throw e;
				// e.printStackTrace();
			} finally {
				connection.setAutoCommit(true);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not rollback transaction", e);
		}
	}

	public int executeInsertWithIdentity(String sql) throws DataAccessException {
		System.out.println("DBConnection, Inserting: " + sql);
		int res = -1;
		try (Statement s = connection.createStatement()) {
			res = s.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			if (res > 0) {
				ResultSet rs = s.getGeneratedKeys();
				rs.next();
				res = rs.getInt(1);
			}
			// s.close(); -- the try block does this for us now

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException("Could not execute insert (" + sql + ").", e);
		}
		return res;
	}

	public int executeInsertWithIdentity(PreparedStatement ps) throws DataAccessException {
		// requires perpared statement to be created with the additional argument PreparedStatement.RETURN_GENERATED_KEYS  
		int res = -1;
		try {
			res = ps.executeUpdate();
			if (res > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				res = rs.getInt(1);
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException("Could not execute insert", e);
		}
		return res;
	}

	public Connection getConnection() {
		return connection;
	}

	public void disconnect() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
