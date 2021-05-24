package query;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.SwingWorker;

import controlLayer.BookingController;
import databaseLayer.BookingDB;
import databaseLayer.LogEntryDB;
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

	public ArrayList<LogEntry> getLogs()
	{
		return logs;
	}
	
	public void startUp() throws SQLException
	{
		SwingWorker sw = new SwingWorker()
		{
			@Override
			protected Object doInBackground() throws Exception {
				BookingDB bookingDB = new BookingDB();
				bookings = bookingDB.getAllByTimeInterval(LocalDate.now().minusDays(7), LocalDate.now().plusDays(11));
				LogEntryDB log = new LogEntryDB();
				log.deleteOldLogs();
				logs = log.getLogs();
				return null;
			}
		};
		sw.execute();
	}
	
	public void queryBookings(LocalDateTime date) throws SQLException
	{
		SwingWorker sw = new SwingWorker()
		{
			@Override
			protected Object doInBackground() throws Exception {
				bookingsForMultipleWeeks.clear();
				
				BookingController bookingControler = new BookingController();
				bookingsForMultipleWeeks.addAll(bookingControler.getAllBookingsForAWeek(date.minusWeeks(1)));
				bookingsForMultipleWeeks.addAll(bookingControler.getAllBookingsForAWeek(date.plusWeeks(1)));
				// Get bookings from neighboring months first weeks
				bookingsForMultipleWeeks.addAll(bookingControler.getAllBookingsForAWeek(date.minusMonths(1)));
				bookingsForMultipleWeeks.addAll(bookingControler.getAllBookingsForAWeek(date.plusMonths(1)));
				return null;
			}
		};
		sw.execute();
	}
	
	public void queryLogs() throws SQLException
	{
		SwingWorker sw = new SwingWorker()
		{
			@Override
			protected Object doInBackground() throws Exception {
				LogEntryDB log = new LogEntryDB();
				logs = log.getLogs();
				return null;
			}
		};
		sw.execute();
	}
}
