package modelLayer;

/**
 * @author Group1 dmai0920
 * Represents the User in the system that can create a Booking
 * on a Room.
 */
public class User
{
	private int id;
	private String name;
	private String email;
	private String phone;
	private String position;
	private UserType userType;
	private Organization organization;

	/**
	 * @author Group1 dmai0920
	 * Represents the possible types a User can be, different
	 * types have different access levels and see different menus.
	 */
	public enum UserType
	{
		DEFAULT("Default"), SUPER("Super"), ADMIN("Admin");
		
		private String typeName;
		
		UserType(String typeName)
		{
			this.typeName = typeName;
		}

		public String getType()
		{
			return this.typeName;
		}
	}
	
	/**
	 * Constructor 1 needs all fields and is used 
	 * to build objects from the database
	 */
	public User(int id, String name, String email, String phone, String position, UserType userType, Organization organization)
	{
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.position = position;
		this.userType = userType;
		this.organization = organization;
	}
	
	/**
	 * Constructor 2 builds a simplified user object
	 * containing only the relevant contact information
	 * for a booking.
	 */
	public User(String name, String email, String phone)
	{
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	/**
	 * Getters and Setters for all class fields
	 */
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getPosition()
	{
		return position;
	}

	public void setPosition(String position)
	{
		this.position = position;
	}

	public UserType getUserType()
	{
		return userType;
	}

	public void setUserType(UserType userType)
	{
		this.userType = userType;
	}

	public Organization getOrganization()
	{
		return organization;
	}

	public void setOrganization(Organization organization)
	{
		this.organization = organization;
	}
}

