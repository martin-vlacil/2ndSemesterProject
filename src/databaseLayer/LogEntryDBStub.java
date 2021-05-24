package databaseLayer;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import modelLayer.LogEntry;

/**
 * This is a stub class used for testing the BookingController that returns
 * dummy data instead of connecting to the database
 * @author Group 1 dmai0920
 *
 */
public class LogEntryDBStub implements LogEntryDBIF
{
    LogEntry logEntry;

    public LogEntryDBStub()
    {
        logEntry = null;
    }

    @Override
    public boolean create(String action, LocalDateTime time) throws SQLException
    {
        this.logEntry = new LogEntry(action, time);
        return true;
    }

    public LogEntry getLogEntry()
    {
        return logEntry;
    }

    /**
     * Unused methods
     */
    @Override
    public ArrayList<LogEntry> getLogs() throws SQLException
    {
        return null;
    }
    
	@Override
	public void deleteOldLogs() throws SQLException {}

}
