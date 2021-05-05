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
		CONFERENCE("Conference Room"), MAIN("Main Room");
		
		private String type;
		
		private RoomType(String type)
		{
			this.type = type;
		}
		
		public String getType()
		{
			return this.type;
		}
	}

	public Room(String number, int capacity, RoomType type)
	{
		this.number = number;
		this.capacity = capacity;
		this.type = type;
	}

	/**
	 * Getters and Setters for all class fields
	 */
	public String getNumber()
	{
		return number;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	public int getCapacity()
	{
		return capacity;
	}

	public void setCapacity(int capacity)
	{
		this.capacity = capacity;
	}

	public RoomType getType()
	{
		return type;
	}

	public void setType(RoomType type)
	{
		this.type = type;
	}
}
