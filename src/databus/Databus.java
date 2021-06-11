package databus;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.SwingWorker;

import controlLayer.BookingController;
import databaseLayer.LogEntryDB;
import modelLayer.Booking;
import modelLayer.LogEntry;

/**
 * A Singleton to handle data from the database by pre-loading it in the background
 * using SwingWorkers. The information stored in the filed is then accessed and displayed to the UI
 * @author group1 dmai0920
 */
public class Databus {
	//Fields to store the loaded information
	private ArrayList<Booking> bookingsForMultipleWeeks;
	private ArrayList<LogEntry> logs;
	//The instance variable used for the singleton using the eager approach
	private static Databus uniqueInstance = new Databus();
	
	/**
	 * Private constructor used for the singleton
	 */
	private Databus() {
		bookingsForMultipleWeeks = new ArrayList<>();
		logs = new ArrayList<>();
	}
	
	/**
	 * A getter for the class instance
	 * @return instance
	 */
	public static Databus getInstance()
	{
		return uniqueInstance;
	}
	
	/**
	 * A method to get all the retrieved bookings
	 * @return the bookings
	 */
	public ArrayList<Booking> getBookings()
	{
		return bookingsForMultipleWeeks;
	}

	/**
	 * A method to get the retrieved logs
	 * @return logs
	 */
	public ArrayList<LogEntry> getLogs()
	{
		return logs;
	}
	
	/**
	 * A method to load the logs, clear old logs, and get bookings on startup
	 * The retried bookings are for the current week, and the 4 other weeks you can access from the calendar:
	 * the next and previous weeks, and the weeks at the start of next and previous months
	 * @throws SQLException
	 */
	public void startUp() throws SQLException
	{
		SwingWorker sw = new SwingWorker()
		{
			@Override
			protected Object doInBackground() throws Exception {
				BookingController bookingControler = new BookingController();
				bookingsForMultipleWeeks = bookingControler.getAllBookingsForAWeek(LocalDateTime.now());
				queryBookings(LocalDateTime.now());
				
				LogEntryDB log = new LogEntryDB();
				log.deleteOldLogs();
				logs = log.getLogs();
				return null;
			}
		};
		sw.execute();
	}
	
	/**
	 * A method to load bookings for the next and previous weeks, and the weeks at the start of next and previous months
	 * based on a given date
	 * @param date
	 * @throws SQLException
	 */
	public void queryBookings(LocalDateTime date) throws SQLException
	{
		SwingWorker sw = new SwingWorker()
		{
			@Override
			protected Object doInBackground() throws Exception {
				BookingController bookingControler = new BookingController();
				bookingsForMultipleWeeks.addAll(bookingControler.getAllBookingsForAWeek(date.minusWeeks(1)));
				bookingsForMultipleWeeks.addAll(bookingControler.getAllBookingsForAWeek(date.plusWeeks(1)));
				// Get bookings from neighboring months first weeks
				bookingsForMultipleWeeks.addAll(bookingControler.getAllBookingsForAWeek(date.minusMonths(1)));
				bookingsForMultipleWeeks.addAll(bookingControler.getAllBookingsForAWeek(date.plusMonths(1).plusDays(7)));
				return null;
			}
		};
		sw.execute();
	}
	
	/**
	 * A method to get the latest logs and store them in logs
	 * @throws SQLException
	 */
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
	
	/**
	 * A method to clear the loaded bookings
	 */
	public void clearBookings()
	{
		bookingsForMultipleWeeks.clear();
	}
}
