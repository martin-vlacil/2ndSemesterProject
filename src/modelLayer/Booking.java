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
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private int numberOfParticipants;
	private Room room;
	private User createdBy;
	private User contact; //if null, then the creator is the contact
	
	/**
	 * Constructor used to build bookings from the database
	 */
	public Booking(String title, String description, LocalDateTime startTime, LocalDateTime endTime, int numberOfParticipants, Room room, User createdBy, int contactID, String contactName, String contactPhoneNumber,
					String contactEmail)
	{
		this.title = title;
		this.description = description;
		this.startTime = startTime;
		this.endTime = endTime;
		this.numberOfParticipants = numberOfParticipants;
		this.room = room;
		this.createdBy = createdBy;
		this.contact = new User(contactID, contactName, contactPhoneNumber, contactEmail);
	}
	
	public Booking(String title, String description, LocalDateTime startTime, LocalDateTime endTime, int numberOfParticipants, Room room, User createdBy)
	{
		this.title = title;
		this.description = description;
		this.startTime = startTime;
		this.endTime = endTime;
		this.numberOfParticipants = numberOfParticipants;
		this.room = room;
		this.createdBy = createdBy;
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

	public Room getRoom()
	{
		return room;
	}

	public void setRoom(Room room)
	{
		this.room = room;
	}

	public User getCreatedBy()
	{
		return createdBy;
	}

	public void setCreatedBy(User createdBy)
	{
		this.createdBy = createdBy;
	}

	public User getContact()
	{
		return contact;
	}

	public void setContact(User contact)
	{
		this.contact = contact;
	}
	
}
