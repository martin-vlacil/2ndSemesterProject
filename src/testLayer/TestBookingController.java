package testLayer;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controlLayer.BookingController;
import controlLayer.RoomController;
import databaseLayer.BookingDBIF;
import databaseLayer.BookingDBStub;
import databaseLayer.RoomDB;
import databaseLayer.RoomDBIF;
import databaseLayer.UserDB;
import databaseLayer.UserDBIF;
import modelLayer.Booking;
import modelLayer.Organization;
import modelLayer.Room;
import modelLayer.User;
import modelLayer.User.UserType;

class TestBookingController
{
	BookingController bookingCtr;
	RoomController roomCtr;
	BookingDBStub bookingDBStub;
	
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
		bookingCtr.setStub(bookingDBStub);
		
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
		bookingCtr.setStub(bookingDBStub);
		
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
	
}
