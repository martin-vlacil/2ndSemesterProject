package modelLayer;

/**
 * @author Group1 dmai0920
 * Represents the physical Rooms to be booked in the system.
 */
public class Room
{
	
	private String number;
	private int capacity;
	private RoomType type;
	
	/**
	 * @author Group1 dmai0920
	 * Defines the only types of Room that are currently available.
	 */
	public enum RoomType
	{
		CONFERENCE,
		MAIN;
	}

	public Room(String number,int capacity, RoomType type)
	{
		this.number = number;
		this.capacity = capacity;
		this.type = type;
	}
}
