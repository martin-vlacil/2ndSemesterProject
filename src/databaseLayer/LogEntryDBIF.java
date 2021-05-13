package databaseLayer;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import modelLayer.LogEntry;

public interface LogEntryDBIF
{
	boolean create(String action, LocalDateTime time) throws SQLException;
	LogEntry[] getLogs() throws SQLException;
}
