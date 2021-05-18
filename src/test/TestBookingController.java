package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controlLayer.BookingController;
import modelLayer.Organization;
import modelLayer.Room;
import modelLayer.User;
import modelLayer.User.UserType;

class TestBookingController
{
	BookingController bookingCtr;
	
	User user1;
	ArrayList<Room> selectedRooms;
	
	Room room1;
	Room room2;

	
	@BeforeAll
	static void setUpBeforeClass() throws Exception
	{
	}
	
	@BeforeEach
	void setUp() throws Exception
	{
		bookingCtr = new BookingController();
		
		user1 = new User(1, "Ib", "Ib@gmail.com", "+4512345678", "Marketing Manager", UserType.DEFAULT, new Organization(1, "IKEA"));
		selectedRooms = new ArrayList<>();
	}
	
	@AfterEach
	void tearDown() throws Exception
	{
	}

	// CB1 - Successful booking
	@Test
	void shouldCreateBookingAndSaveToDatabaseWithCorrectInfo()
	{
		//bookingCtr.confirmBooking("2nd Semester Exam", "We are all passing :)", user1)
	}
	
}
