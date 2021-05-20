package testLayer;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
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

    @BeforeAll
    static void setUpBeforeClass() throws Exception
    {
    }

    @BeforeEach
    void setUp() throws Exception
    {
        // Arrange
        bookingCtr = new BookingController();
        roomCtr = new RoomController();
        bookingDBStub = new BookingDBStub();
        logEntryDBStub = new LogEntryDBStub();

        bookingCtr.setStub(bookingDBStub, logEntryDBStub);
    }

    @AfterEach
    void tearDown() throws Exception
    {
    }

    // CB1 - Successful booking - TC1
    @Test
    void shouldCreateBookingAndSaveToDatabaseWithCorrectShortInfo()
            throws SQLException
    {
        // Arrange
        ArrayList<Room> selectedRooms = new ArrayList<>();
        selectedRooms.add(roomCtr.findByID(1));
        User shortUser = new User(1, "Ib", "Ib@gmail.com", "+4512345678",
                "Marketing Manager", UserType.DEFAULT,
                new Organization(1, "IKEA"));
        LocalDateTime startTime = LocalDateTime.of(2021, 6, 5, 15, 0);
        LocalDateTime endTime = LocalDateTime.of(2021, 6, 5, 23, 0);

        // Act
        boolean isBookingCreated = bookingCtr.confirmBooking(
                "2nd Semester Exam", "We are all passing :)", null, 15,
                shortUser, selectedRooms, startTime, endTime);

        // Assert
        assertTrue(isBookingCreated, "Controller returned False");

        Booking booking = bookingDBStub.getBooking();

        assertTrue(startTime.isEqual(booking.getStartTime()),
                "Start time doesn't match");
        assertTrue(endTime.isEqual(booking.getEndTime()),
                "End time doesn't match");
        assertEquals(shortUser, booking.getCreatedBy(), "User doesn't match");
        assertEquals(selectedRooms.get(0), booking.getRoom(),
                "Room doesn't match");
        assertEquals(15, booking.getNumberOfParticipants(),
                "Number of participants doesn't match");
    }

    // CB1 - Successful booking - TC2
    @Test
    void shouldCreateBookingAndSaveToDatabaseWithCorrectLongInfo()
            throws SQLException
    {
        // Arrange
        ArrayList<Room> selectedRooms = new ArrayList<>();
        selectedRooms.add(roomCtr.findByID(1));
        User longUser = new User(2, "ForSomeReasonVeryLongName",
                "forTestingPurposesWeHaveCreatedThisVeryLongEmailThatContainsExactlyOneHundredCharactersUwU@gmail.com",
                "+451234567891234", "Managing Marketer", UserType.DEFAULT,
                new Organization(2, "Bauhaus"));
        LocalDateTime startTime = LocalDateTime.of(2021, 6, 5, 15, 0);
        LocalDateTime endTime = LocalDateTime.of(2021, 6, 5, 23, 0);

        // Act
        boolean isBookingCreated = bookingCtr.confirmBooking(
                "2nd Semester Exam", "We are all passing :)", null, 15,
                longUser, selectedRooms, startTime, endTime);

        // Assert
        assertTrue(isBookingCreated, "Controller returned False");

        Booking booking = bookingDBStub.getBooking();

        assertTrue(startTime.isEqual(booking.getStartTime()),
                "Start time doesn't match");
        assertTrue(endTime.isEqual(booking.getEndTime()),
                "End time doesn't match");
        assertEquals(longUser, booking.getCreatedBy(), "User doesn't match");
        assertEquals(selectedRooms.get(0), booking.getRoom(),
                "Room doesn't match");
        assertEquals(15, booking.getNumberOfParticipants(),
                "Number of participants doesn't match");
    }

    // CB2 - Booking cancelled - Done Ad hoc instead

    // CB3 - TC1 The entire booking interferes with a another booking
    @Test
    void anEntireInterferingBookingShouldReturnAStringContainingTheBookingInfo()
            throws SQLException
    {
        // Arrange
        Room room1 = new Room("Test Number", 15, "Conference Room", 1);
        LocalDateTime startTime = LocalDateTime.of(2021, 5, 5, 15, 0);
        LocalDateTime endTime = LocalDateTime.of(2021, 5, 5, 23, 0);

        // Act
        String problemTemplate = bookingCtr.checkAvailability(startTime,
                endTime, room1);
        int problemTemplateLength = problemTemplate.length();

        // Assert
        assertTrue(problemTemplateLength > 0);
    }

    // CB3 - TC2 The endTime is inside another event
    @Test
    void endTimeOfBookingisInsideOtherBookingShouldReturnAStringContainingTheBookingInfo()
            throws SQLException
    {
        // Arrange
        Room room1 = new Room("Test Number", 15, "Conference Room", 1);
        LocalDateTime startTime = LocalDateTime.of(2021, 5, 5, 15, 0);
        LocalDateTime endTime = LocalDateTime.of(2021, 5, 5, 17, 0);

        // Act
        String problemTemplate = bookingCtr.checkAvailability(startTime,
                endTime, room1);
        int problemTemplateLength = problemTemplate.length();

        // Assert
        assertTrue(problemTemplateLength > 0);
    }

    // CB3 - TC3 The startTime is inside another event
    @Test
    void startTimeOfBookingisInsideOtherBookingShouldReturnAStringContainingTheBookingInfo()
            throws SQLException // TODO - can be changed probably uwu
    {
        // Arrange
        Room room1 = new Room("Test Number", 15, "Conference Room", 1);
        LocalDateTime startTime = LocalDateTime.of(2021, 5, 5, 17, 0);
        LocalDateTime endTime = LocalDateTime.of(2021, 5, 5, 23, 0);

        // Act
        String problemTemplate = bookingCtr.checkAvailability(startTime,
                endTime, room1);
        int problemTemplateLength = problemTemplate.length();

        // Assert
        assertTrue(problemTemplateLength > 0);
    }

    // CB4 - TC1 Event title is too long
    @Test
    void eventTitleLongerThan50CharactersShouldReturnFalse() throws SQLException
    {
        // Arrange
        String[] eventDetails = new String[2];
        eventDetails[0] = "title";
        eventDetails[1] = "asseocarnisanguineoviscericartilaginonervomedullary";

        // Act
        boolean checkValidity = bookingCtr
                .validateInformation(eventDetails) == Color.RED;

        // Assert
        assertTrue(checkValidity);
    }

    // CB4 - TC2 contact name too long
    @Test
    void contactNameLongerThan25CharactersShouldReturnFalse()
            throws SQLException
    {
        // Arrange
        String[] eventDetails = new String[2];
        eventDetails[0] = "contactName";
        eventDetails[1] = "Mette Juul Thorhauge Søren";

        // Act
        boolean checkValidity = bookingCtr
                .validateInformation(eventDetails) == Color.RED;

        // Assert
        assertTrue(checkValidity);
    }

    // CB4 - TC3 contact name too short
    @Test
    void contactNameShorterThan2CharactersShouldReturnFalse()
            throws SQLException
    {
        // Arrange
        String[] eventDetails = new String[2];
        eventDetails[0] = "contactName";
        eventDetails[1] = "M";

        // Act
        boolean checkValidity = bookingCtr
                .validateInformation(eventDetails) == Color.RED;

        // Assert
        assertTrue(checkValidity);
    }

    // CB4 - TC4 phone number too long
    @Test
    void phoneNumberLongerThan15CharactersShouldReturnFalse()
            throws SQLException
    {
        // Arrange
        String[] eventDetails = new String[2];
        eventDetails[0] = "phoneNumber";
        eventDetails[1] = "+231343578493011";

        // Act
        boolean checkValidity = bookingCtr
                .validateInformation(eventDetails) == Color.RED;

        // Assert
        assertTrue(checkValidity);
    }

    // CB4 - TC5 phone number shorter than 1
    @Test
    void phoneNumberShorterThan1ShouldReturnFalse() throws SQLException
    {
        // Arrange
        String[] eventDetails = new String[2];
        eventDetails[0] = "phoneNumber";
        eventDetails[1] = "";

        // Act
        boolean checkValidity = bookingCtr
                .validateInformation(eventDetails) == Color.RED;

        // Assert
        assertTrue(checkValidity);
    }

    // CB4 - TC6 email does not contain '@'
    @Test
    void emailWithoutAtSymbolShouldReturnFalse() throws SQLException
    {
        // Arrange
        String[] eventDetails = new String[2];
        eventDetails[0] = "email";
        eventDetails[1] = "homeaddress.dk";

        // Act
        boolean checkValidity = bookingCtr
                .validateInformation(eventDetails) == Color.RED;

        // Assert
        assertTrue(checkValidity);
    }

    // CB4 - TC7 email longer than 100 characters
    @Test
    void emailLongerThan100CharactersShouldReturnFalse() throws SQLException
    {
        // Arrange
        String[] eventDetails = new String[2];
        eventDetails[0] = "email";
        eventDetails[1] = "asseocarnisanguineoviscericartilaginonervomedullar@asseocarnisanguineoviscericartilaginonervomedullar";

        // Act
        boolean checkValidity = bookingCtr
                .validateInformation(eventDetails) == Color.RED;

        // Assert
        assertTrue(checkValidity);
    }

    // CB4 - TC8 email shorter than 2 characters
    @Test
    void emailShorterThan2CharactersShouldReturnFalse() throws SQLException
    {
        // Arrange
        String[] eventDetails = new String[2];
        eventDetails[0] = "email";
        eventDetails[1] = "a";

        // Act
        boolean checkValidity = bookingCtr
                .validateInformation(eventDetails) == Color.RED;

        // Assert
        assertTrue(checkValidity);
    }
}
