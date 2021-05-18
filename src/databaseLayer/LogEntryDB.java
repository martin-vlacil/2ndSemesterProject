package databaseLayer;

import java.sql.*;
import java.time.LocalDateTime;

import modelLayer.LogEntry;

/**
 * @author Group 1 dmai0920
 * This is a database class for LogEntries, the handles its persistence, it is responsible for finding, updating, deleting,
 * and inserting to the database
 */
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
		
		logEntryCreated = sqlInsertLogEntry.execute();
		
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
