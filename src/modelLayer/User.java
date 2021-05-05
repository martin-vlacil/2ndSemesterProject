package modelLayer;

/**
 * @author Group1 dmai0920
 * Represents the User in the system that can create a Booking
 * on a Room.
 */
public class User
{
	
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
		DEFUALT, SUPER, ADMIN;
	}
	
	public User()
	{
		// TODO Auto-generated constructor stub
	}

	/**
	 * Getters and Setters for all class fields
	 */
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

