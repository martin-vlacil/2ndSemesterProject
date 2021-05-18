package databaseLayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import modelLayer.Booking;

public class BookingDBStub implements BookingDBIF {

	Booking booking; 
	
	public BookingDBStub() {
		booking = null;
	}

	@Override
	public boolean create(Booking booking) throws SQLException {
		this.booking = booking;
		return true;
	}

	@Override
	public ArrayList<Booking> getAllByDateOfOneDay(LocalDate date) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Booking> getAllByTimeInterval(LocalDate startDate, LocalDate endTime) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Booking buildObject(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Booking getBooking()
	{
		return booking;
	}

}
