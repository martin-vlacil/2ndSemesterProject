package databaseLayer;

import java.sql.SQLException;
import java.time.LocalDateTime;

import modelLayer.LogEntry;

public interface LogEntryDBIF
{
	LogEntry create(String action, LocalDateTime time) throws SQLException;
}
