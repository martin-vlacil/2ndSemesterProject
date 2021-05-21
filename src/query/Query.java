package query;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.SwingWorker;

import databaseLayer.BookingDB;
import modelLayer.Booking;
import modelLayer.LogEntry;

public class Query {

	
	private ArrayList<Booking> bookings;
	private ArrayList<Booking> bookingsForMultipleWeeks;
	private ArrayList<LogEntry> logs;
	private static Query uniqueInstance = new Query();
	
	private Query() {
		bookings = new ArrayList<>();
		bookingsForMultipleWeeks = new ArrayList<>();
		logs = new ArrayList<>();
	}
	
	public static Query getInstance()
	{
		return uniqueInstance;
	}
	
	public ArrayList<Booking> getBookings()
	{
		return bookings;
	}
	
	public void queryBookings()
	{
		
	}
	
	public void startUp() throws SQLException
	{
		SwingWorker sw = new SwingWorker()
		{

				@Override
				protected Object doInBackground() throws Exception {
					BookingDB bookingDB = new BookingDB();
					bookings = bookingDB.getAllByTimeInterval(LocalDate.now().minusDays(7), LocalDate.now().plusDays(11));
					return null;
				}
			
		};
		sw.execute();
;
		
	}
	
	
	
}
