package test;

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
import databaseLayer.RoomDB;
import databaseLayer.RoomDBIF;
import databaseLayer.UserDB;
import databaseLayer.UserDBIF;
import modelLayer.Organization;
import modelLayer.Room;
import modelLayer.User;
import modelLayer.User.UserType;

class TestBookingController
{
	BookingController bookingCtr;
	RoomController roomCtr;
	//ArrayList<Room> allRooms;
	
	User user1;
	User user2;
	ArrayList<Room> selectedRooms;
	


	
	@BeforeAll
	static void setUpBeforeClass() throws Exception
	{
	}
	
	@BeforeEach
	void setUp() throws Exception
	{
		bookingCtr = new BookingController();
		roomCtr = new RoomController();;
		//allRooms = roomCtr.getAll();
		
		user1 = new User(1, "Ib", "Ib@gmail.com", "+4512345678", "Marketing Manager", UserType.DEFAULT, new Organization(1, "IKEA"));
		selectedRooms = new ArrayList<>();
	}
	
	@AfterEach
	void tearDown() throws Exception
	{
	}

	// CB1 - Successful booking
	@Test
	void shouldCreateBookingAndSaveToDatabaseWithCorrectInfo() throws SQLException
	{
		LocalDateTime startTime = LocalDateTime.of(2021, 6, 5, 15, 0);
		LocalDateTime endTime = LocalDateTime.of(2021, 6, 5, 23, 0);
		selectedRooms.add(roomCtr.findByID(1));
		bookingCtr.confirmBooking("2nd Semester Exam", "We are all passing :)", null, 15, user1, selectedRooms, startTime, endTime);
	}
	
}
