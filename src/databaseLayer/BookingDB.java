package databaseLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class BookingDB implements BookingDBIF
{
	private Connection connection;
	
	private static final String INSERT_BOOKING = String.format("INSERT INTO Booking VALUES(?, ?, ?, ?, ?, ?, ?, ?)"); //TODO finish the string format
	private PreparedStatement sqlInsertBooking;

	public BookingDB() throws SQLException
	{
		connection = DBConnection.getInstance().getConnection();
		
		sqlInsertBooking = connection.prepareStatement(INSERT_BOOKING, Statement.RETURN_GENERATED_KEYS);
	}

	@Override
	public boolean create(String title, String description, String contactName, String phoneNumber, String email, LocalDateTime startTime, LocalDateTime endTime, int numberOfParticipants)
	{
		boolean bookingCreated = false;
		
		// TODO write method
		
		return bookingCreated;
	}

}
