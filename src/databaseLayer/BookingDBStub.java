package databaseLayer;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import modelLayer.Booking;
import modelLayer.Organization;
import modelLayer.Room;
import modelLayer.User;
import modelLayer.User.UserType;

/**
 * This is a stub class used for testing the BookingController that returns
 * dummy data instead of connecting to the database
 * @author Group 1 dmai0920
 */
public class BookingDBStub implements BookingDBIF
{
    Booking booking;

    public BookingDBStub()
    {
        booking = null;
    }

    @Override
    public boolean create(Booking booking) throws SQLException
    {
        this.booking = booking;
        return true;
    }

    @Override
    public ArrayList<Booking> getAllByDateOfOneDay(LocalDate date)
            throws SQLException
    {
        ArrayList<Booking> bookings = new ArrayList<>();
        Room room1 = new Room("Test Number", 15, "Conference Room", 1);
        User user = new User(1, "Bob", "bob@bob.bob", "+4512345678",
                "The builder", UserType.DEFAULT, new Organization(1, "Bob"));
        Booking booking = new Booking("Test title", "Test desc",
                LocalDateTime.of(2021, 5, 5, 16, 0),
                LocalDateTime.of(2021, 5, 5, 21, 0), 15, room1, user);
        bookings.add(booking);

        return bookings;
    }

    @Override
    public ArrayList<Booking> getAllByTimeInterval(LocalDate startDate,
            LocalDate endTime) throws SQLException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Booking buildObject(ResultSet rs) throws SQLException
    {
        // TODO Auto-generated method stub
        return null;
    }

    public Booking getBooking()
    {
        return booking;
    }

}
