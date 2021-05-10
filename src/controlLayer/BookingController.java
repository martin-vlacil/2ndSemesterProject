package controlLayer;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import databaseLayer.BookingDB;
import databaseLayer.BookingDBIF;
import databaseLayer.LogEntryDB;
import databaseLayer.LogEntryDBIF;
import modelLayer.Room;
import modelLayer.User;

/**
 * @author Group1 dmai0920
 * This is a controller that handles most of the "business logic"
 * regarding the Bookings that the Users create
 */
public class BookingController
{
	private RoomController roomCtr;
	private LogEntryDBIF logEntryDB;
	private BookingDBIF bookingDB;
	private ArrayList<Room> selectedRooms;
	private LocalDateTime selectedStartTime;
	private LocalDateTime selectedEndTime;

	public BookingController() throws SQLException
	{
		roomCtr = new RoomController();
		logEntryDB = new LogEntryDB();
		bookingDB = new BookingDB();
		selectedRooms = new ArrayList<Room>();
	}

	public Room selectRoom(int roomID) throws SQLException
	{
		Room room = null;
		
		room = roomCtr.findByID(roomID);
		this.selectedRooms.add(room);
		
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
		boolean startTimeSelected = true;
		
		for(Room currentRoom: selectedRooms)
		{
			if(!roomCtr.checkAvailability(currentRoom.getId(), time))
			{
				startTimeSelected = false;
			}
		}
		
		if(startTimeSelected)
		{
			this.selectedStartTime = time;
		}
		
		return startTimeSelected;
	}
	
	public boolean selectEndTime(LocalDateTime time)
	{
		boolean endTimeSelected = false;
		
		for(Room currentRoom: selectedRooms)
		{
			if(!roomCtr.checkAvailability(currentRoom.getId(), time))
			{
				endTimeSelected = false;
			}
		}
		
		if(endTimeSelected)
		{
			this.selectedEndTime = time;
		}
		
		return endTimeSelected;
	}
	
	public User getLoggedUser()
	{
		User user = null;
		
		//TODO - write method
		
		return user;
	}
	
	public boolean confirmBooking(String title, String description, String contactName,
					String phoneNumber, String email, int numberOfParticipants)
	{
		boolean bookingConfirmed = false;
		
		//TODO - write method
		
		return bookingConfirmed;
	}
}
