package databaseLayer;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import modelLayer.LogEntry;

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

    @Override
    public ArrayList<LogEntry> getLogs() throws SQLException
    {
        // TODO Auto-generated method stub
        return null;
    }

    public LogEntry getLogEntry()
    {
        return logEntry;
    }

}
