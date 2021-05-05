package modelLayer;

import java.time.LocalDateTime;

/**
 * @author Group1 dmai0920
 * Represents the booking in the system that the user can make
 * for a room.
 */
public class Booking
{
	
	private String title;
	private String description;
	private String contactName;
	private String phoneNumber;
	private String email;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private int numberOfParticipants;

	public Booking(String title, String description, String contactName, String phoneNumber,
					String email, LocalDateTime startTime, LocalDateTime endTime, int numberOfParticipants)
	{
		this.title = title;
		this.description = description;
		this.contactName = contactName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.startTime = startTime;
		this.endTime = endTime;
		this.numberOfParticipants = numberOfParticipants;
	}

	/**
	 * Getters and Setters for all class fields
	 */
	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getContactName()
	{
		return contactName;
	}

	public void setContactName(String contactName)
	{
		this.contactName = contactName;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public LocalDateTime getStartTime()
	{
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime)
	{
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime()
	{
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime)
	{
		this.endTime = endTime;
	}

	public int getNumberOfParticipants()
	{
		return numberOfParticipants;
	}

	public void setNumberOfParticipants(int numberOfParticipants)
	{
		this.numberOfParticipants = numberOfParticipants;
	}
}
