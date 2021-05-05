package modelLayer;

/**
 * @author Group1 dmai0920
 * Represents the physical Rooms to be booked in the system.
 */
public class Room
{
	
	private int number;
	private String name;
	private int capacity;
	private RoomType type;
	
	/**
	 * @author Group1 dmai0920
	 * Defines the only types of Room that are currently available.
	 */
	public enum RoomType
	{
		CONFERENCE_ROOM,
		MAIN_ROOM;
	}

	public Room()
	{
		// TODO Auto-generated constructor stub
	}

}
