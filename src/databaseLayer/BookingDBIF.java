package databaseLayer;

import java.sql.SQLException;
import java.time.LocalDateTime;

import modelLayer.Booking;

public interface BookingDBIF
{
	boolean create(Booking booking) throws SQLException;
	
	Booking checkAvailability(LocalDateTime startTime, LocalDateTime endTime, int roomID);
}
