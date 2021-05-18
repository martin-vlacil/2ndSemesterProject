package testLayer;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.*;

import controlLayer.*;

import databaseLayer.BookingDBStub;
import databaseLayer.LogEntryDBStub;
import modelLayer.*;
import modelLayer.User.UserType;

class TestBookingController
{
	BookingController bookingCtr;
	RoomController roomCtr;
	BookingDBStub bookingDBStub;
	LogEntryDBStub logEntryDBStub;
	
	LocalDateTime startTime;
	LocalDateTime endTime;
	
	User shortUser;
	User longUser;
	ArrayList<Room> selectedRooms;
	


	
	@BeforeAll
	static void setUpBeforeClass() throws Exception
	{
	}
	
	@BeforeEach
	void setUp() throws Exception
	{
		//Arrange
		bookingCtr = new BookingController();
		roomCtr = new RoomController();
		bookingDBStub = new BookingDBStub();
		logEntryDBStub = new LogEntryDBStub();
		
		startTime = LocalDateTime.of(2021, 6, 5, 15, 0);
		endTime = LocalDateTime.of(2021, 6, 5, 23, 0);
		
		shortUser = new User(1, "Ib", "Ib@gmail.com", "+4512345678", "Marketing Manager", UserType.DEFAULT, new Organization(1, "IKEA"));
		longUser = new User(2, "ForSomeReasonVeryLongName", "forTestingPurposesWeHaveCreatedThisVeryLongEmailThatContainsExactlyOneHundredCharactersUwU@gmail.com", "+451234567891234", "Managing Marketer", UserType.DEFAULT, new Organization(2, "Bauhaus"));
		selectedRooms = new ArrayList<>();

	}
	
	@AfterEach
	void tearDown() throws Exception
	{
	}

	// CB1 - Successful booking - TC1
	@Test
	void shouldCreateBookingAndSaveToDatabaseWithCorrectShortInfo() throws SQLException
	{
		//Arrange
		selectedRooms.add(roomCtr.findByID(1));
		bookingCtr.setStub(bookingDBStub, logEntryDBStub);
		
		//Act
		boolean isBookingCreated = bookingCtr.confirmBooking("2nd Semester Exam", "We are all passing :)", null, 15, shortUser, selectedRooms, startTime, endTime);
		
		//Assert
		assertTrue(isBookingCreated, "Controller returned False");
		
		Booking booking = bookingDBStub.getBooking();
		
		assertTrue(startTime.isEqual(booking.getStartTime()), "Start time doesn't match");
		assertTrue(endTime.isEqual(booking.getEndTime()), "End time doesn't match");
		assertEquals(shortUser, booking.getCreatedBy(), "User doesn't match");
		assertEquals(selectedRooms.get(0), booking.getRoom(), "Room doesn't match");
		assertEquals(15, booking.getNumberOfParticipants(),"Number of participants doesn't match");
	}
	
	//CB1 - Successful booking - TC2
	@Test
	void shouldCreateBookingAndSaveToDatabaseWithCorrectLongInfo() throws SQLException
	{
		//Arrange
		selectedRooms.add(roomCtr.findByID(1));
		bookingCtr.setStub(bookingDBStub, logEntryDBStub);
		
		//Act
		boolean isBookingCreated = bookingCtr.confirmBooking("2nd Semester Exam", "We are all passing :)", null, 15, longUser, selectedRooms, startTime, endTime);
		
		//Assert
		assertTrue(isBookingCreated, "Controller returned False");
		
		Booking booking = bookingDBStub.getBooking();
		
		assertTrue(startTime.isEqual(booking.getStartTime()), "Start time doesn't match");
		assertTrue(endTime.isEqual(booking.getEndTime()), "End time doesn't match");
		assertEquals(longUser, booking.getCreatedBy(), "User doesn't match");
		assertEquals(selectedRooms.get(0), booking.getRoom(), "Room doesn't match");
		assertEquals(15, booking.getNumberOfParticipants(),"Number of participants doesn't match");
	}
	
	//CB2 - Booking cancelled
	
	
	//CB3 - The room is chosen for illegal time interval
	void shouldReturnANullRoomValueIfChosenInWrongTimeInterval()
	{
		
	}
	
}
