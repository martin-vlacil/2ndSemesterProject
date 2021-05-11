package controlLayer;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import databaseLayer.BookingDB;
import databaseLayer.BookingDBIF;
import databaseLayer.DBConnection;
import databaseLayer.LogEntryDB;
import databaseLayer.LogEntryDBIF;
import modelLayer.Booking;
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
	
	public boolean confirmBooking(String title, String description, int contactID, String contactName, String contactPhoneNumber, String contactEmail, int numberOfParticipants, User createdBy) throws SQLException
	{
		boolean bookingConfirmed = true;
		
		for(Room room: selectedRooms)
			try
			{	
				DBConnection.getInstance().startTransaction();
				
				Booking booking = new Booking(title, description, this.selectedStartTime, this.selectedEndTime, numberOfParticipants, room, createdBy, contactID, contactName, contactPhoneNumber, contactEmail);
				if(!bookingDB.create(booking))
				{
					bookingConfirmed = false;
				}
				
				logEntryDB.create("", LocalDateTime.now()); //TODO specify creation message, We can have a class of static final messages and do Log.CREATE_BOOKING
				
				DBConnection.getInstance().commitTransaction();
			}
			catch (SQLException e)
			{
				DBConnection.getInstance().rollbackTransaction();
				bookingConfirmed = false;
				e.printStackTrace();
			}
			
		
		return bookingConfirmed;
	}
	 
	public String validateInformation(String[] information)
	{
		switch(information[0])
		{
			case "title":
				if (information[1].length() > 50)
				{
					return "title";
				}
				break;
				
			case "attendees":
				String[] writtenAndAllowed = information[1].split(" ");
				if (Integer.parseInt(writtenAndAllowed[0]) <= Integer.parseInt(writtenAndAllowed[1]))
				{
					return "attendees";
				}
				break;
				
			case "contactName":
				if (information[1].length() > 25 || information[1].length() < 2)
				{
					return "contactName";
				}	
				break;
				
			case "phoneNumber":
				for (int e = 0; e < information[1].length(); e++) if (Character.isDigit(information[1].charAt(e))) return "";
				if (information[1].length() > 15 || information[1].length() < 1)
				{
					return "phoneNumber";
				}
				break;
				
			case "email":
				if (!information[1].contains("@") || information[1].length() > 100 || information[1].length() < 1)
				{
					return "email";
				}
				break;
				
			default: return "";
		}
		
		
		return "";
	}
	
	public void checkRoomAvailability(LocalDateTime startTime, LocalDateTime endTime, Room room)
	{
		
	}
	
}