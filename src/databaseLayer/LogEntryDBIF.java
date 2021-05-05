package databaseLayer;

import java.time.LocalDateTime;

import modelLayer.LogEntry;

public interface LogEntryDBIF
{
	LogEntry create(String action, LocalDateTime time);
}
