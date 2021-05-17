package databaseLayer;

import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

import controlLayer.RoomController;
import modelLayer.*;

/**
 * @author Group 1 dmai0920
 * This is a database class for Booking, the handles its persistence, it is responsible for finding, updating, deleting,
 * and inserting to the database
 */
public class BookingDB implements BookingDBIF
{
	RoomController roomCtr;
	UserDBIF userDB;
	
	private Connection connection;
	
	private static final String INSERT_BOOKING = String.format("INSERT INTO Booking VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
	private PreparedStatement sqlInsertBooking;
	
	private static final String SELECT_BOOKINGS_BY_DATE = String.format("SELECT title, description, start_time, end_time, number_of_participants, contact_id, [user_id], room_id, [User].first_name AS user_first_name, [User].last_name AS user_last_name, [User].email AS user_email, [User].phone AS user_phone, [User].position AS user_position, ContactPerson.[name] AS contact_name, ContactPerson.email AS contact_email, ContactPerson.phone AS contact_phone FROM Booking  \r\n"
					+ "LEFT JOIN [User] ON Booking.user_id = [User].id \r\n"
					+ "LEFT JOIN ContactPerson ON Booking.contact_id = ContactPerson.id\r\n"
					+ "WHERE CONVERT(DATETIME, FLOOR(CONVERT(FLOAT, start_time))) = ?");
	private PreparedStatement sqlSelectBookingsByDate;
	
	public BookingDB() throws SQLException
	{
		connection = DBConnection.getInstance().getConnection();
		sqlInsertBooking = connection.prepareStatement(INSERT_BOOKING, Statement.RETURN_GENERATED_KEYS);
		sqlSelectBookingsByDate = connection.prepareStatement(SELECT_BOOKINGS_BY_DATE);
	}

	/*
	 * Booking interface methods
	 */
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
	
	@Override
	public ArrayList<Booking> getAllByDateOfOneDay(LocalDate date) throws SQLException
	{
		ArrayList<Booking> bookingsOfTheDay = new ArrayList<>();
		String sqlDate = date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

		sqlSelectBookingsByDate.setString(1, sqlDate);
		
		ResultSet rs = sqlSelectBookingsByDate.executeQuery();
		
		while(rs.next())
		{
			Booking booking = buildObject(rs);
			bookingsOfTheDay.add(booking);
		}
		
		return bookingsOfTheDay;
	}
	
	@Override
	public Booking buildObject(ResultSet rs) throws SQLException
	{
		User user = userDB.getUserByID(rs.getInt("user_id"));
		
		String contactEmail = rs.getString("contact_email");
		
		if(rs.wasNull())
		{
			return new Booking(rs.getString("title"), rs.getString("description"), rs.getTimestamp("start_time").toLocalDateTime(),
							rs.getTimestamp("end_time").toLocalDateTime(), rs.getInt("number_of_participants"), roomCtr.findByID(rs.getInt("room_id")),
							user, rs.getString("contact_name"), contactEmail, rs.getString("contact_phone"));
		}
		else
		{
			return new Booking(rs.getString("title"), rs.getString("description"), rs.getTimestamp("start_time").toLocalDateTime(),
							rs.getTimestamp("end_time").toLocalDateTime(), rs.getInt("number_of_participants"), roomCtr.findByID(rs.getInt("room_id")),
							user);
		}
	}
}