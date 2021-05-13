package databaseLayer;

import java.sql.SQLException;
import java.time.LocalDateTime;

import modelLayer.LogEntry;

/**
 * This interface defines all the methods for the LogEntry Data Access Object
 * @author Group 1 dmai0920
 */
public interface LogEntryDBIF
{
	/**
	 * This method inserts a LogEntry in the Log relation in the database
	 * @param action - what exactly was done, time
	 * @return true/false whether the insertion was successful or not
	 * @throws SQLException
	 */
	boolean create(String action, LocalDateTime time) throws SQLException;
	
	/**
	 * This method gets the latest 25 logs of major actions in the system
	 * @return array of 25 log entries
	 * @throws SQLException
	 */
	LogEntry[] getLogs() throws SQLException;
}
