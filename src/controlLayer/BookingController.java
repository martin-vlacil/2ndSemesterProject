package controlLayer;

import java.time.LocalDateTime;

import modelLayer.Room;
import modelLayer.User;

/**
 * @author Group1 dmai0920
 * This is a controller that handles most of the "business logic"
 * regarding the Bookings that the Users create
 */
public class BookingController
{
	
	private UserController userCtr;

	public BookingController()
	{
		userCtr = new UserController();
	}

	public Room selectRoom(String roomNumber)
	{
		Room room = null;
		
		//TODO - write method
		
		return room;
	}
	
	public boolean selectDate(LocalDateTime date)
	{
		boolean dateSelected = false;
		
		//TODO - write method
		
		return dateSelected;
	}
	
	public boolean selectStartTime(LocalDateTime time)
	{
		boolean startTimeSelected = false;
		
		//TODO - write method
		
		return startTimeSelected;
	}
	
	public boolean selectEndTime(LocalDateTime time)
	{
		boolean endTimeSelected = false;
		
		//TODO - write method
		
		return endTimeSelected;
	}
	
	public User getLoggedUser()
	{
		User user = null;
		
		//TODO - write method
		
		return user;
	}
	
	public boolean confirmBooking(String title, String description, String contactName,
					String phoneNumber, String email, LocalDateTime startTime,
					LocalDateTime endTime, int numberOfParticipants)
	{
		boolean bookingConfirmed = false;
		
		//TODO - write method
		
		return bookingConfirmed;
	}
}
