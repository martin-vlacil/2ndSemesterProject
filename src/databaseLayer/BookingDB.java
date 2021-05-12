package databaseLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import modelLayer.Booking;
import modelLayer.Room;

public class BookingDB implements BookingDBIF
{
	private Connection connection;
	
	private static final String INSERT_BOOKING = String.format("INSERT INTO Booking VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
	private PreparedStatement sqlInsertBooking;
	
	private static final String SELECT_BOOKINGS_BY_DATE_AND_ROOM = String.format("SELECT * FROM Bookings WHERE CONVERT(DATETIME, FLOOR(CONVERT(FLOAT, start_time))) = ? AND room_id = ?");
	private PreparedStatement sqlSelectBookingsByDateAndRoom;
	
	public BookingDB() throws SQLException
	{
		connection = DBConnection.getInstance().getConnection();
		
		sqlInsertBooking = connection.prepareStatement(INSERT_BOOKING, Statement.RETURN_GENERATED_KEYS);
		sqlSelectBookingsByDateAndRoom = connection.prepareStatement(SELECT_BOOKINGS_BY_DATE_AND_ROOM);
	}

	@Override
	public boolean create(Booking booking) throws SQLException
	{
		sqlInsertBooking.setString(1, booking.getTitle());
		sqlInsertBooking.setString(2, booking.getDescription());
		sqlInsertBooking.setTimestamp(3, Timestamp.valueOf(booking.getStartTime()));
		sqlInsertBooking.setTimestamp(4, Timestamp.valueOf(booking.getEndTime()));
		sqlInsertBooking.setInt(5, booking.getNumberOfParticipants());
		if(booking.getContact() == null)
		{
			sqlInsertBooking.setNull(6, Types.INTEGER);
		}
		else
		{
			sqlInsertBooking.setInt(6, booking.getContact().getId());
		}
		sqlInsertBooking.setInt(7, booking.getCreatedBy().getId());
		sqlInsertBooking.setInt(8, booking.getRoom().getId());
		
		return sqlInsertBooking.execute(INSERT_BOOKING, Statement.RETURN_GENERATED_KEYS);
	}
	
	//TODO finish
	@Override
	public Booking checkAvailability(LocalDateTime startTime, LocalDateTime endTime, int roomID) throws SQLException
	{
		return null;
	}
	
	//TODO finish
	@Override
	public List<Booking> getAllForRoomFromDate(Room room, LocalDate date) throws SQLException
	{
		//sqlSelectBookingsByDateAndRoom.setThing(1, date);
		sqlSelectBookingsByDateAndRoom.setInt(2, room.getId());
		return null;
	}
}
