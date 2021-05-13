package modelLayer;

/**
 * @author Group1 dmai0920
 * Represents an Organization in the system that groups their
 * Users
 */
public class Organization
{	
	private String name;

	public Organization(String name)
	{
		this.name = name;
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
}
