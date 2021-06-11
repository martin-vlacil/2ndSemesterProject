package modelLayer;

import java.time.LocalDateTime;

/**
 * @author Group1 dmai0920
 * Represents the booking in the system that the user
 * can make for a room.
 */
public class Booking implements Comparable<Booking>
{
	private int numberOfParticipants; 
    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Room room;
    private User createdBy;
    private User contact; // if null, then the createdBy is the contact
    
    private boolean selected;

    /**
     * Constructor used to build bookings from the database
     * It accepts a contact if the contact information has been changed
     */
    public Booking(String title, String description, LocalDateTime startTime, LocalDateTime endTime, int numberOfParticipants, Room room, User createdBy, User contact)
    {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.numberOfParticipants = numberOfParticipants;
        this.room = room;
        this.createdBy = createdBy;
        this.contact = contact;
    }

    /**
     * Constructor used to build bookings from the database
     * It is missing a contact, because this one is used when the contact information in the UI
     * has bee unchanged
     */
    public Booking(String title, String description, LocalDateTime startTime, LocalDateTime endTime, int numberOfParticipants, Room room, User createdBy)
    {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.numberOfParticipants = numberOfParticipants;
        this.room = room;
        this.createdBy = createdBy;
    }

    /**
     * Getters and Setters for all class fields
     */
    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public LocalDateTime getStartTime()
    {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime)
    {

        this.startTime = startTime;
    }

    public LocalDateTime getEndTime()
    {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime)
    {
        this.endTime = endTime;
    }

    public int getNumberOfParticipants()
    {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(int numberOfParticipants)
    {
        this.numberOfParticipants = numberOfParticipants;
    }

    public Room getRoom()
    {
        return room;
    }

    public void setRoom(Room room)
    {
        this.room = room;
    }

    public User getCreatedBy()
    {
        return createdBy;
    }

    public void setCreatedBy(User createdBy)
    {
        this.createdBy = createdBy;
    }

    public User getContact()
    {
        return contact;
    }

    public void setContact(User contact)
    {
        this.contact = contact;
    }

    public boolean isSelected()
    {
        return selected;
    }

    public void setSelected(boolean selected)
    {
        this.selected = selected;
    }

    public Room getType()
    {
        return room;
    }

    /**
     * Is implemented from the Comparable interface, which compares two times
     * returns a positive/negative integer based on the result of the comparison
     * 
     * negative - current booking is less than parameter booking
     * 0 - bookings are equal
     * positive - current booking is more than parameter booking
     */
    @Override
    public int compareTo(final Booking booking)
    {
        final int comp = startTime.compareTo(booking.getStartTime());
        if (comp == 0)
        {
        	return endTime.compareTo(booking.getEndTime());
        }
        return comp;
    }
}