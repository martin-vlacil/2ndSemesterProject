package databaseLayer;

import java.sql.SQLException;
import java.time.LocalDateTime;

import modelLayer.LogEntry;

public interface LogEntryDBIF
{
	boolean create(String action, LocalDateTime time) throws SQLException;
}
