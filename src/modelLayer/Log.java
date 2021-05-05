package modelLayer;

import java.util.List;

public class Log
{
	
	private static Log instance;
	private List<LogEntry> logEntries; //TODO - decide on the type of the List

	private Log()
	{
		
	}
	
	private static Log getInstance()
	{
		return instance;
	}
	
	public void addLogEntry(LogEntry logEntry)
	{
		//TODO write method
	}

}
