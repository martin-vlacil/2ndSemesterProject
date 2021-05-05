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

}
