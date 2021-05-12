package databaseLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import modelLayer.LogEntry;

public class LogEntryDB implements LogEntryDBIF
{
	private Connection connection;
	
	private static final String INSERT_LOG_ENTRY = String.format("INSERT INTO Log VALUES(?, ?)");
	private PreparedStatement sqlInsertLogEntry;

	public LogEntryDB() throws SQLException
	{
		connection = DBConnection.getInstance().getConnection();
		
		sqlInsertLogEntry = connection.prepareStatement(INSERT_LOG_ENTRY);
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

}
