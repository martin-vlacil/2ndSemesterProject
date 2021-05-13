package databaseLayer;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import modelLayer.Booking;
import modelLayer.Room;

public interface BookingDBIF
{
	boolean create(Booking booking) throws SQLException;
	ArrayList<Booking> checkAvailability(LocalDateTime startTime, LocalDateTime endTime, int roomID) throws SQLException;
	ArrayList<Booking> getAllByDateAndRoom(Room room, LocalDate date) throws SQLException;
}
