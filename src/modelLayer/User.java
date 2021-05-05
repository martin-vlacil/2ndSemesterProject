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

}
