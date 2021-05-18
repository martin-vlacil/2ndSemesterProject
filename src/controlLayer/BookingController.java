package controlLayer;

import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import databaseLayer.*;
import modelLayer.*;

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
	private ArrayList<Booking> bookingsOnADay;

	public BookingController() throws SQLException
	{
		roomCtr = new RoomController();
		logEntryDB = new LogEntryDB();
		bookingDB = new BookingDB();
		bookingsOnADay = new ArrayList<>();
	}
	
	
	/**
	 * TODO - finish desc
	 * @param title, description, contactName, contactPhoneNumber, contactEmail, numberOfParticipants, createdBy
	 * @return true/false if the booking(s) was/were successfully created in the database
	 * @throws SQLException
	 */
	public boolean confirmBooking(String title, String description, User contact, int numberOfParticipants, User createdBy, ArrayList<Room> selectedRooms, LocalDateTime selectedStartTime, LocalDateTime selectedEndTime) throws SQLException
	{
		boolean bookingConfirmed = true;
		Booking booking = null;
		
		for(Room room: selectedRooms)
			try
			{	
				DBConnection.getInstance().startTransaction();
				
				if(contact == null)
				{	
					booking = new Booking(title, description, selectedStartTime, selectedEndTime, numberOfParticipants, room, createdBy, contact);
				}
				else
				{
					booking = new Booking(title, description, selectedStartTime, selectedEndTime, numberOfParticipants, room, createdBy);
				}
				
				if(!bookingDB.create(booking))
				{
					bookingConfirmed = false;
				}
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				logEntryDB.create(createdBy.getName() + " from " + createdBy.getOrganization().getName() + " has booked " 
				+ room.getName().substring(0,1).toUpperCase() + room.getName().substring(1).toLowerCase() + " on "
						+ selectedStartTime.format(formatter), LocalDateTime.now()); 
				//TODO specify creation message, We can have a class of static final messages and do Log.CREATE_BOOKING
				
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
	
	/**
	 * This method is used for checking all the user input depending on where it is taken from
	 * @param information
	 * @return true/false whether or not the information is correct
	 */
	public boolean validateInformation(String[] information)
	{
		//TODO maybe we can use smth fancy like hashmap? Also Regex at phonenumber and attendees
		switch(information[0])
		{
			case "title":
				if (information[1].length() > 50)
				{
					return false;
				}
				break;
				
			/*
			case "attendees":
				try {
					Integer.parseInt(information[1]);
				}catch(Exception e)
				{
					return false;
				}
				String[] writtenAndAllowed = information[1].split(" ");
				if (Integer.parseInt(writtenAndAllowed[0]) <= Integer.parseInt(writtenAndAllowed[1]))
				{
					return false;
				}
				break;
				*/
			case "contactName":
				if (information[1].length() > 25 || information[1].length() < 2)
				{
					return false;
				}	
				break;
				
			case "phoneNumber":
				for (int e = information[1].startsWith("+") ? 1 : 0; e < information[1].length(); e++)
				{
					if (!Character.isDigit(information[1].charAt(e))) 
					{
						return false;
					}
				}
				if (information[1].length() > 15 || information[1].length() < 1)
				{
					return false;
				}
				break;
				
			case "email":
				if (!information[1].contains("@") || information[1].length() > 100 || information[1].length() < 1)
				{
					return false;
				}
				break;
				
			default: return true;
		}
		return true;
	}
	
	
	
	//TODO REMOVE
	
	/**
	 * This method checks whether or not the chosen times for a room are available
	 * @param startTime, end time, room
	 * @return an error message if not, nothing if its ok
	 * @throws SQLException
	 */
	/*
	public String checkAvailability(LocalDateTime startTime, LocalDateTime endTime, Room room) throws SQLException
	{
		ArrayList<Booking> interferingBookings = new ArrayList<>();
		if (startTime != null && endTime != null && room != null)
		{
			//interferingBookings = bookingDB.(startTime, endTime, room.getId());
			if (interferingBookings.size() > 0)
			{
				String returnString = "Interfering bookings: ";
				for(Booking booking: interferingBookings)
				{
					returnString += booking.getStartTime() + " - " + booking.getEndTime() + "  "; 
				}
				return returnString;
			}
		}
		else
		{
			//TODO - some exception?
		}
		
		return ""; //Returns nothing. One of the elements is not filled
	}
	*/
	
	/**
	 * A method to check if a certain room is available for booking between start and end time
	 * @param startTime, endTime, room
	 * @return the error message
	 * @throws SQLException
	 */
	public String checkAvailability(LocalDateTime startTime, LocalDateTime endTime, Room room) throws SQLException
	{
		//TODO Fix template "%s" signs
		// what? ^
		String problemTemplate = "Room %s is booked by %s from %s until %s. \n";
		for (Booking booking : bookingsOnADay)
		{
			if (booking.getRoom().equals(room))
			{
				//TODO Separated the first 2 cases, didnt use "or", it is more readable 
				//this way -- COMMENT TO THE OTHERS DELETE LATER ON
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
				String startTimeString = booking.getStartTime().format(formatter);
				String endTimeString = booking.getEndTime().format(formatter);
				String organization = booking.getCreatedBy().getOrganization().getName();
				//If the events starts before and ends meanwhile
				if(booking.getStartTime().isBefore(startTime) && booking.getEndTime().isAfter(startTime)) return String.format(problemTemplate, room.getName(), organization, startTimeString, endTimeString);
				//If the events starts meanwhile and ends after
				else if(booking.getStartTime().isBefore(endTime) && booking.getEndTime().isAfter(endTime)) return String.format(problemTemplate, room.getName(), organization, startTimeString, endTimeString);
				//If the event starts and ends between the two timeslots
				else if(booking.getStartTime().isAfter(startTime) && booking.getEndTime().isBefore(endTime)) return String.format(problemTemplate, room.getName(), organization, startTimeString, endTimeString);
				//If the event starts before the startTime and ends after the endTime
				else if(booking.getStartTime().isBefore(startTime) && booking.getEndTime().isAfter(endTime)) return String.format(problemTemplate, room.getName(), organization, startTimeString, endTimeString);
			}
		}
		return "";
	}
	
	//TODO comment + explain why?
	public void getRoomsOfOneDay(LocalDate date) throws SQLException
	{
		bookingsOnADay = bookingDB.getAllByDateOfOneDay(date);
	}
	
	
	/**
	 * This method gets a list of all the rooms from the database
	 * @return a list of all rooms
	 * @throws SQLException
	 */
	public ArrayList<Room> getAllRooms() throws SQLException
	{
		return roomCtr.getAll();
	}
	
	/**
	 * TODO - write comment
	 * @param currentDate
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Booking> getAllBookingsForAWeek(LocalDateTime currentDate) throws SQLException
	{
		LocalDate startDate = currentDate.with(DayOfWeek.MONDAY).toLocalDate();
		LocalDate endDate = startDate.plusDays(6);
		
		return bookingDB.getAllByTimeInterval(startDate, endDate);	
	}
	
	public void setStub(BookingDBIF bookingDB)
	{
		this.bookingDB = bookingDB;
	}
	
	
}
