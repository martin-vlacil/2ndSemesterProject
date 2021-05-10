package databaseLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;

import modelLayer.Booking;

public class BookingDB implements BookingDBIF
{
	private Connection connection;
	
	private static final String INSERT_BOOKING = String.format("INSERT INTO Booking VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
	private PreparedStatement sqlInsertBooking;

	public BookingDB() throws SQLException
	{
		connection = DBConnection.getInstance().getConnection();
		
		sqlInsertBooking = connection.prepareStatement(INSERT_BOOKING, Statement.RETURN_GENERATED_KEYS);
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

}
