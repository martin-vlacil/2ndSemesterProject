package modelLayer;

import java.time.LocalDateTime;

/**
 * @author Group1 dmai0920
 * Represents every major action that will take place in the system
 * allowing it to be displayed in the UI as a text message.
 */
public class LogEntry
{
	private String action;
	private LocalDateTime time;

	public LogEntry(String action, LocalDateTime time)
	{
		this.action = action;
		this.time = time;
	}
}
