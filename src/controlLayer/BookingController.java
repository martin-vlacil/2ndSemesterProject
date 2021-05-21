package controlLayer;

import java.awt.Color;
import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import config.Config;
import databaseLayer.*;
import modelLayer.*;

/**
 * @author Group1 dmai0920 
 * This is a controller that handles most of the
 * "business logic" regarding the Bookings that the Users create
 */
public class BookingController
{
    private RoomController roomCtr;
    private LogEntryDBIF logEntryDB;
    private BookingDBIF bookingDB;
    private ArrayList<Room> selectedRooms;
    Pattern pattern;

    public BookingController() throws SQLException
    {
        selectedRooms = new ArrayList<>();
        roomCtr = new RoomController();
        logEntryDB = new LogEntryDB();
        bookingDB = new BookingDB();
        pattern = Pattern.compile("(.+)@(.+).(.+)");
    }

    /**
     * Is called when the Save button is clicked in the create booking dialog,
     * takes all the information from the text fields and creates the booking objects
     * which are then inserted into the database, also creates a log object that is inserted
     * into the database
     * 
     * @param title, description, contactName, contactPhoneNumber, contactEmail,
     * numberOfParticipants, createdBy
     * @return true/false if the booking(s) was/were successfully created in the database
     * @throws SQLException
     */
    public boolean confirmBooking(String title, String description,
            User contact, int numberOfParticipants, User createdBy,
            ArrayList<Room> selectedRooms, LocalDateTime selectedStartTime,
            LocalDateTime selectedEndTime) throws SQLException
    {
        boolean bookingConfirmed = true;
        Booking booking = null;

        if (title.equals(""))
        {
            title = "Event by: " + createdBy.getOrganization().getName();
        }

        for (Room room : selectedRooms)
            try
            {
                DBConnection.getInstance().startTransaction();

                if (contact == null)
                {
                    booking = new Booking(title, description, selectedStartTime,
                            selectedEndTime, numberOfParticipants, room,
                            createdBy, contact);
                }
                else
                {
                    booking = new Booking(title, description, selectedStartTime,
                            selectedEndTime, numberOfParticipants, room,
                            createdBy);
                }

                if (!bookingDB.create(booking))
                {
                    bookingConfirmed = false;
                }
                DateTimeFormatter formatter = DateTimeFormatter
                        .ofPattern("dd/MM/yyyy");
                logEntryDB.create(createdBy.getName() + " from "
                        + createdBy.getOrganization().getName() + " has booked "
                        + room.getName().substring(0, 1).toUpperCase()
                        + room.getName().substring(1).toLowerCase() + " on "
                        + selectedStartTime.format(formatter),
                        LocalDateTime.now());

                DBConnection.getInstance().commitTransaction();
            }
            catch (SQLException e)
            {
                DBConnection.getInstance().rollbackTransaction();
                bookingConfirmed = false;
                e.printStackTrace();
            }
        return bookingConfirmed;
    }

    /**
     * This method is used for checking all the user input depending on where it
     * is taken from
     * 
     * @param information
     * @return Color of the textField border (black - correct, stays the same color, orange - warning color, red - error color)
     */
    public Color validateInformation(String[] information)
    {
        Config config = new Config();
        Color errorColor = config.getErrorMessageColor();
        Color warningColor = new Color(244, 129, 34);
        Color correctColor = Color.BLACK;
        
        switch (information[0])
        {
        case "title":
            if (information[1].length() > 50)
            {
                return errorColor;
            }
            break;

        case "attendees":
            try
            {
                if (!information[1].equals(""))
                {
                    int attendees = Integer.parseInt(information[1]);
                    if (attendees == 0)
                    {
                        return errorColor;
                    }

                    if (selectedRooms.isEmpty())
                    {
                        return correctColor;
                    }
                    for (Room room : selectedRooms)
                    {
                        attendees -= room.getCapacity();
                    }
                    return attendees <= 0 ? correctColor
                            : warningColor;
                }
                else
                {
                    return errorColor;
                }
            }
            catch (Exception e)
            {
                return errorColor;
            }
        case "contactName":
            if (information[1].length() > 25 || information[1].length() < 2)
            {
                return errorColor;
            }
            break;

        case "phoneNumber":
            for (int e = information[1].startsWith("+") ? 1
                    : 0; e < information[1].length(); e++)
            {
                if (!Character.isDigit(information[1].charAt(e)))
                {
                    return errorColor;
                }
            }
            if (information[1].length() > 15 || information[1].length() < 1)
            {
                return errorColor;
            }
            break;

        case "email":
            Matcher matcher = pattern.matcher(information[1]);
            if (!matcher.find() || information[1].length() > 100 || information[1].length() < 5)
            {
                return errorColor;
            }
            break;

        default:
            return correctColor;
        }
        return correctColor;
    }

    /**
     * A method to check if a certain room is available for booking between
     * start and end time
     * 
     * @param startTime, endTime, room
     * @return the error message
     * @throws SQLException
     */
    public String checkAvailability(LocalDateTime startTime, LocalDateTime endTime, Room room) throws SQLException
    {
        StringBuilder returnString = new StringBuilder();
        String problemTemplate = "\nInteference: %s room is booked by %s from %s until %s.";
        if(startTime.isEqual(endTime) || endTime.isBefore(startTime))
        {
        	return "The selected start time is invalid!";
        }
        for (Booking booking : getBookingsOfOneDay(startTime.toLocalDate()))
        {
            if (booking.getRoom().getId() == room.getId())
            {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                String startTimeString = booking.getStartTime().format(formatter);
                String endTimeString = booking.getEndTime().format(formatter);
                String organization = booking.getCreatedBy().getOrganization().getName();
                String roomName = room.getName().substring(0, 1).toUpperCase() + room.getName().substring(1).toLowerCase();
                // If the events starts before and ends meanwhile
                if (booking.getStartTime().isBefore(startTime) && booking.getEndTime().isAfter(startTime)) returnString.append(String.format(problemTemplate, roomName, organization, startTimeString, endTimeString));
                // If the events starts meanwhile and ends after
                else if (booking.getStartTime().isBefore(endTime) && booking.getEndTime().isAfter(endTime)) returnString.append(String.format(problemTemplate, roomName, organization, startTimeString, endTimeString));
                // If the event starts and ends between the two timeslots
                else if (booking.getStartTime().isAfter(startTime) && booking.getEndTime().isBefore(endTime)) returnString.append(String.format(problemTemplate, roomName, organization, startTimeString, endTimeString));
                // If the event starts before the startTime and ends after the endTime
                else if (booking.getStartTime().isBefore(startTime) && booking.getEndTime().isAfter(endTime)) returnString.append(String.format(problemTemplate, roomName, organization, startTimeString, endTimeString));
                //If the bookings have the same start and end time
                else if (booking.getStartTime().isEqual(startTime) && (booking.getEndTime().isEqual(endTime) || booking.getEndTime().isBefore(endTime))) returnString.append(String.format(problemTemplate, roomName, organization, startTimeString, endTimeString));
            }
        }
        return returnString.toString();
    }

    /**
     * Gives all bookings on a given day
     * 
     * @param date
     * @return list of all bookings on a given day
     * @throws SQLException
     */
    public ArrayList<Booking> getBookingsOfOneDay(LocalDate date)
            throws SQLException
    {
        return bookingDB.getAllByDateOfOneDay(date);
    }

    /**
     * This method gets a list of all the rooms from the database
     * 
     * @return a list of all rooms
     * @throws SQLException
     */
    public ArrayList<Room> getAllRooms() throws SQLException
    {
        return roomCtr.getAll();
    }

    /**
     * Get all bookings in the week of a given date
     * 
     * @param currentDate
     * @return
     * @throws SQLException
     */
    public ArrayList<Booking> getAllBookingsForAWeek(LocalDateTime currentDate)
            throws SQLException
    {
        LocalDate startDate = currentDate.with(DayOfWeek.MONDAY).toLocalDate();
        LocalDate endDate = startDate.plusDays(6);

        return bookingDB.getAllByTimeInterval(startDate, endDate);
    }

    /**
     * Sets stubs for the Data Access Object Classes to return dummy data instead of connecting to the
     * database, utilized for testing
     * @param bookingDB
     * @param logEntryDB
     */
    public void setStub(BookingDBIF bookingDB, LogEntryDBIF logEntryDB)
    {
        this.bookingDB = bookingDB;
        this.logEntryDB = logEntryDB;
    }

    public void addSelectedRoom(Room room)
    {
        selectedRooms.add(room);
    }

    public void removeSelectedRoom(Room room)
    {
        selectedRooms.remove(room);
    }

}
