package databaseLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import modelLayer.LogEntry;

public class LogEntryDB implements LogEntryDBIF
{
	private Connection connection;
	
	private static final String INSERT_LOG_ENTRY = String.format("INSERT INTO Log VALUES(?, ?"); //TODO - write string format
	private PreparedStatement sqlInsertLogEntry;

	public LogEntryDB() throws SQLException
	{
		connection = DBConnection.getInstance().getConnection();
		
		sqlInsertLogEntry = connection.prepareStatement(INSERT_LOG_ENTRY);
	}

	@Override
	public LogEntry create(String action, LocalDateTime time)
	{
		LogEntry logEntry = null;
		
		// TODO write method
		
		return logEntry;
	}

}
