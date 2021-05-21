package modelLayer;

import java.time.LocalDateTime;

/**
 * @author Group1 dmai0920 
 * Represents every major action that will take place in
 * the system allowing it to be displayed in the UI as a text message.
 */
public class LogEntry
{
    private String action; // Will contain user name and action description
    private LocalDateTime time; // The time at which the log is created

    public LogEntry(String action, LocalDateTime time)
    {
        this.action = action;
        this.time = time;
    }

    /**
     * Getters and Setters for all class fields
     */
    public String getAction()
    {
        return action;
    }

    public void setAction(String action)
    {
        this.action = action;
    }

    public LocalDateTime getTime()
    {
        return time;
    }

    public void setTime(LocalDateTime time)
    {
        this.time = time;
    }
}
