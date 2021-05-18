package databaseLayer;

import java.sql.*;
import java.time.*;
import java.util.*;

import modelLayer.*;

/**
 * This interface defines all the methods for the Booking Data Access Object
 * @author Group 1 dmai0920
 */
public interface BookingDBIF
{
	/**
	 * This method inserts a Booking in the Booking relation in the database
	 * @param booking
	 * @return true/false whether the insertion was successful or not
	 * @throws SQLException
	 */
	boolean create(Booking booking) throws SQLException;
	
	//TODO Remove?
	/**
	 * This method checks if the chosen time for a booking is correct - no other bookings for the specified room are made in that time
	 * @param startTime, endTime, roomID
	 * @return a list of bookings that interfere with the chosen booking time
	 * @throws SQLException
	 */
	//ArrayList<Booking> checkAvailability(LocalDateTime startTime, LocalDateTime endTime, int roomID) throws SQLException;
	
	
	/**
	 * This method returns a list of all the bookings made for a specific room and a specific day, it is used for later comparing
	 * times of those bookings to check availability
	 * @param room, date
	 * @return a list of bookings for that day on that room
	 * @throws SQLException
	 */
	ArrayList<Booking> getAllByDateOfOneDay(LocalDate date) throws SQLException;
	
	/**
	 * TODO - comment
	 * @param startTime, endTime
	 * @return 
	 * @throws SQLException
	 */
	ArrayList<Booking> getAllByTimeInterval(LocalDate startDate, LocalDate endTime) throws SQLException;
	
	/**
	 * This method is used for creating the Booking objects from the Result set
	 * to return them to the controller
	 * @param rs - result set of the found rows
	 * @return the created booking object
	 * @throws SQLException 
	 */
	Booking buildObject(ResultSet rs) throws SQLException;
	
	
}
